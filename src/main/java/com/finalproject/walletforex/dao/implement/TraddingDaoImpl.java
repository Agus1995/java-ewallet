package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.ForexTradding;
import com.finalproject.walletforex.repository.TraddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraddingDaoImpl implements TraddingDao {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraddingRepository traddingRepository;

    @Autowired
    private KursDao kursDao;

    @Override
    public ForexTradding buy(ForexTradding forexTradding, Account account) throws AccountNotFoundException, BalanceNotEnoughException {
        Account account1 = accountDao.findById(account.getAccountNumber());
        if (account1.getCurencyType().equals(forexTradding.getCcy())){
            forexTradding.setRate(kursDao.getByCode(forexTradding.getDescription()).getBuy());
            forexTradding.setDescription("Buy");
            account1.setBalance(account1.getBalance() - forexTradding.getAmount());
            if (account1.getBalance() < 0){
                throw new BalanceNotEnoughException(03, "Your Balance Not Enough");
            }
            traddingRepository.save(forexTradding);
            return forexTradding;
        }else {
            double rest = kursDao.buy(forexTradding.getCcy(), forexTradding.getAmount());
            if (account1.getBalance() <  rest){
                throw new BalanceNotEnoughException(03, "Your Balance Not Enough");
            }
            account1.setBalance(account1.getBalance() - rest);
            accountDao.updateBalance(account1);
            traddingRepository.save(forexTradding);
            return forexTradding;
        }
    }

    @Override
    public ForexTradding sell(ForexTradding forexTradding, Account account) {
        return null;
    }
}
