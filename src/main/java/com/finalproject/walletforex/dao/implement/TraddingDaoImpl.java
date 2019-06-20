package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.WalletNotFoundException;
import com.finalproject.walletforex.model.*;
import com.finalproject.walletforex.repository.TraddingReportRepository;
import com.finalproject.walletforex.repository.TraddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TraddingReportRepository traddingReportRepository;

    @Override
    public ForexTradding buy(TraddingDto traddingDto) throws AccountNotFoundException, BalanceNotEnoughException {
        ForexTradding forexTradding = set(traddingDto);
        forexTradding.setDescription("B");
        forexTradding.setRestOfMoney(forexTradding.getAmount());
        String accNumb = traddingDto.getAccount();
         Account account = accountDao.findById(accNumb);
        if (account.getCurencyType().equals(forexTradding.getCcy())){
            forexTradding.setRate((double) 1);
            if (account.getBalance() < forexTradding.getAmount())
                throw new BalanceNotEnoughException(3, "Your Balance Not Enough");
            account.setBalance(account.getBalance() - forexTradding.getAmount());
            accountDao.updateBalance(account);
            return traddingRepository.save(forexTradding);
        }else {
            forexTradding.setRate(getNewRate(traddingDto).getSell());
            double kurs = kursDao.buyMoney(account.getCurencyType(), forexTradding.getCcy(), forexTradding.getAmount());
            if (account.getBalance() < kurs)
                throw new BalanceNotEnoughException(3, "Your Balance Not enough");
            account.setBalance(account.getBalance() - kurs);
            accountDao.updateBalance(account);
            return traddingRepository.save(forexTradding);
        }
    }

    @Override
    public ForexTradding sell(TraddingDto traddingDto) throws BalanceNotEnoughException, AccountNotFoundException {
        String accNumb = traddingDto.getAccount();
        Account acc = accountDao.findById(accNumb);
        ForexTradding forexTradding = set(traddingDto);
        forexTradding.setRate(getNewRate(traddingDto).getSell());
        if (forexTradding.getAmount() <= traddingRepository.getSum(acc.getCustomer().getCif())){
            ForexTradding forexTradding1 = get(acc.getCustomer().getCif());
            forexTradding.setDescription("S");
            if (forexTradding.getAmount() <= forexTradding1.getRestOfMoney()){
                forexTradding.setProvitLost(forexTradding.getAmount() * (forexTradding.getRate() - forexTradding1.getRate()));
                forexTradding1.setRestOfMoney(forexTradding1.getRestOfMoney() - forexTradding.getAmount());
                updateRestMoney(forexTradding1);
                addToAccount(accNumb, traddingDto.getCcy(), traddingDto.getAmount());
                return traddingRepository.save(forexTradding);
            }else {
                double leftovers = forexTradding.getAmount() - forexTradding1.getRestOfMoney();
                double provLost = forexTradding1.getRestOfMoney() * (forexTradding.getRate() - forexTradding1.getRate());

                forexTradding1.setRestOfMoney((double)0);
                updateRestMoney(forexTradding1);

                do {
                    forexTradding1 = get(acc.getCustomer().getCif());
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
                addToAccount(accNumb, traddingDto.getCcy(), traddingDto.getAmount());
                return traddingRepository.save(forexTradding);
            }
        }else {
            throw new BalanceNotEnoughException(31, "Your Balance not enough");
        }
    }
    @Override
    public List<ForexTradding> getByCif(String cif) throws WalletNotFoundException {
        return traddingRepository.findByCiff(cif);
    }

    @Override
    public List<ForexTradding> getWithFif(String cif) throws AccountNotFoundException {
        List<ForexTradding> forexTraddings = traddingRepository.findByCustomer_Cif(cif);
        if (forexTraddings.isEmpty()){
            throw new AccountNotFoundException(2, "you have not yet traded");
        }
        return forexTraddings;
    }

    @Override
    public double checksum(String cif) {
        return traddingRepository.getSum(cif);
    }

    private ForexTradding get(String cif){
        List<ForexTradding> forexTraddings = traddingRepository.findByCiff(cif);
        return forexTraddings.get(0);
    }

    private void updateRestMoney(ForexTradding forexTradding){
        traddingRepository.findById(forexTradding.getId())
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

    private Kurs getNewRate(TraddingDto dto) throws AccountNotFoundException {
        Account account = accountDao.findById(dto.getAccount());
        return kursDao.findByCcy(account.getCurencyType(), dto.getCcy());
    }

    private void addToAccount(String acc, String ccy, double value) throws AccountNotFoundException {
        Account account = accountDao.findById(acc);
        if (account.getCurencyType().equals(ccy)){
            account.setBalance(account.getBalance() + value);
            accountDao.updateBalance(account);
        }else {
            Kurs kurs = kursDao.findByCcy(account.getCurencyType(), ccy);
            if (kurs==null){
                kurs = kursDao.findByCcy(ccy, account.getCurencyType());
                double val = value / kurs.getBuy();
                account.setBalance(account.getBalance() + val);
            }else {
                double val = kurs.getBuy() * value;
                account.setBalance(account.getBalance() + val);
            }
            accountDao.updateBalance(account);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void reportTradding() {
        TraddingReport traddingReport = new TraddingReport();
        List<Customer> customers = customerDao.findAll();
        for (Customer cs : customers) {
            traddingReport.setCustomer(cs);
            traddingReport.setRate(traddingRepository.getSum(cs.getCif()));
            traddingReportRepository.save(traddingReport);
        }
    }
}
