package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.WalletNotFoundException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.ForexTradding;
import com.finalproject.walletforex.repository.TraddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraddingDaoImpl implements TraddingDao {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraddingRepository traddingRepository;

    @Autowired
    private KursDao kursDao;

    @Override
    public ForexTradding buy(TraddingDto traddingDto) throws AccountNotFoundException, BalanceNotEnoughException {
        ForexTradding forexTradding = set(traddingDto);
        forexTradding.setDescription("B");
        forexTradding.setRestOfMoney(forexTradding.getAmount());
        String accNumb = traddingDto.getAccount();
        Account account = accountDao.findById(accNumb);
        if (account.getCurencyType().equals(forexTradding.getCcy())){
            if (account.getBalance() < forexTradding.getAmount())
                throw new BalanceNotEnoughException(03, "Your Balance Not Enough");
            account.setBalance(account.getBalance() - forexTradding.getAmount());
            accountDao.updateBalance(account);
            return traddingRepository.save(forexTradding);
        }else {
            double kurs = kursDao.buyMoney(account.getCurencyType(), forexTradding.getCcy(), forexTradding.getAmount());
            if (account.getBalance() < kurs)
                throw new BalanceNotEnoughException(03, "Your Balance Not enough");
            account.setBalance(account.getBalance() - kurs);
            accountDao.updateBalance(account);
            return traddingRepository.save(forexTradding);
        }
    }

    @Override
    public ForexTradding sell(TraddingDto traddingDto) throws BalanceNotEnoughException {
        ForexTradding forexTradding = set(traddingDto);
        String accNumb = traddingDto.getAccount();
        if (forexTradding.getAmount() <= traddingRepository.getSum(forexTradding.getCustomer().getCif())){
            ForexTradding forexTradding1 = get(forexTradding.getCustomer().getCif(), 0);
            forexTradding.setDescription("S");
            if (forexTradding.getAmount() <= forexTradding1.getRestOfMoney()){
                forexTradding.setProvitLost(forexTradding.getAmount() * (forexTradding.getRate() - forexTradding1.getRate()));
                forexTradding1.setRestOfMoney(forexTradding1.getRestOfMoney() - forexTradding.getAmount());
                updateRestMoney(forexTradding1);
                return traddingRepository.save(forexTradding);
            }else {

                double leftovers = forexTradding.getAmount() - forexTradding1.getRestOfMoney();
                double provLost = forexTradding1.getRestOfMoney() * (forexTradding.getRate() - forexTradding1.getRate());

                forexTradding1.setRestOfMoney((double)0);
                updateRestMoney(forexTradding1);

                do {
                    forexTradding1 = get(forexTradding.getCustomer().getCif(), 0);
                    if (forexTradding1.getRestOfMoney() >= leftovers){
                        provLost += leftovers * (forexTradding.getRate() - forexTradding1.getRate());
                        forexTradding1.setRestOfMoney(forexTradding1.getRestOfMoney() - leftovers);
                        updateRestMoney(forexTradding1);
                        leftovers=0;
                    } else {
                        provLost += forexTradding1.getRestOfMoney() * (forexTradding.getRate() - forexTradding1.getRate());
                        forexTradding1.setRestOfMoney((double) 0);
                        updateRestMoney(forexTradding1);
                        leftovers = forexTradding1.getRestOfMoney() - leftovers;
                    }
                }while (leftovers > 0);

                forexTradding.setProvitLost(provLost);
                traddingRepository.save(forexTradding);
            }
            return forexTradding;
        }else {
            throw new BalanceNotEnoughException(31, "Your Balance not enough");
        }
    }
    @Override
    public ForexTradding check(String cif) throws WalletNotFoundException {
        List<ForexTradding> forexTraddings = traddingRepository.findByCif(cif);
        if (forexTraddings.isEmpty()){
            throw new WalletNotFoundException( 9, "tidak ketemu");
        }
        double a = traddingRepository.getSum(cif);
        System.out.println(a);
        return forexTraddings.get(0);
    }

    @Override
    public double checksum(String cif) {
        return traddingRepository.getSum(cif);
    }

    private ForexTradding get(String cif, int i){
        List<ForexTradding> forexTraddings = traddingRepository.findByCif(cif);
        return forexTraddings.get(0);
    }

    private void updateRestMoney(ForexTradding forexTradding){
        ForexTradding forexTradding1 = traddingRepository.findById(forexTradding.getId())
                .map(ent -> {
                    ent.setRestOfMoney(forexTradding.getRestOfMoney());
                    return traddingRepository.save(ent);
                }).orElseGet(()->{
                    forexTradding.setId(forexTradding.getId());
                    return traddingRepository.save(forexTradding);
                });
    }

    private ForexTradding set (TraddingDto traddingDto){
        ForexTradding forexTradding = new ForexTradding();
        forexTradding.setAmount(traddingDto.getAmount());
        forexTradding.setRestOfMoney(traddingDto.getRestOfMoney());
        forexTradding.setProvitLost(traddingDto.getProvitLost());
        forexTradding.setDescription(traddingDto.getDescription());
        forexTradding.setRate(traddingDto.getRate());
        forexTradding.setCcy(traddingDto.getCcy());
        forexTradding.setCustomer(traddingDto.getCustomer());
        return forexTradding;
    }
}
