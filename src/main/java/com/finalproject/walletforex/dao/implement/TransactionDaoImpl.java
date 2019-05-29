package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.TransactionDao;
import com.finalproject.walletforex.dto.TransactionDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.Transaction;
import com.finalproject.walletforex.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private KursDao kursDao;

    @Override
    public Transaction transaction(TransactionDto dto) throws AccountNotFoundException, BalanceNotEnoughException {
        Transaction transaction = setTransaction(dto);
        Account accountDebet    = accountDao.findById(transaction.getAccDebet());
        Account accountCredit   = accountDao.findById(transaction.getAccCredit());
        if (accountDebet.getCurencyType().equals(accountCredit.getCurencyType())){
            accountDebet.setBalance(accountDebet.getBalance() - transaction.getAmount());
            if (accountDebet.getBalance() < 0){
                throw new BalanceNotEnoughException(03, "Balance not enough");
            }
            accountCredit.setBalance(accountCredit.getBalance() + transaction.getAmount());
            accountDao.updateBalance(accountCredit);
            accountDao.updateBalance(accountDebet);
            transactionRepository.save(transaction);
            return transaction;
        } else {
            if(accountCredit.getCurencyType().equals("IDR")){
                double kurs = kursDao.sell(accountDebet.getCurencyType(), transaction.getAmount());
                accountCredit.setBalance(accountCredit.getBalance() + kurs);
                accountDebet.setBalance(accountDebet.getBalance() - transaction.getAmount());
                accountDao.updateBalance(accountCredit);
                accountDao.updateBalance(accountDebet);
                transactionRepository.save(transaction);
                return transaction;
            }else {
                double kurs = kursDao.buy(accountCredit.getCurencyType(), transaction.getAmount());
                accountCredit.setBalance(accountCredit.getBalance() + kurs);
                accountDebet.setBalance(accountDebet.getBalance() - transaction.getAmount());
                accountDao.updateBalance(accountCredit);
                accountDao.updateBalance(accountDebet);
                transactionRepository.save(transaction);
                return transaction;
            }
        }
    }

    @Override
    public List<Transaction> getList(String accNumber) {
        List<Transaction> transaction = transactionRepository.findByAccCreditOrAccDebet(accNumber, accNumber);
        if (transaction.isEmpty()){
            return null;
        }
        return transaction;
    }

    private Transaction setTransaction(TransactionDto dto){
        Transaction transaction = new Transaction();
        transaction.setAccCredit(dto.getAccCredit());
        transaction.setAccDebet(dto.getAccDebet());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setId(dto.getId());
        transaction.setTransactionType(dto.getTransactionTypeDto());
        return transaction;
    }
}
