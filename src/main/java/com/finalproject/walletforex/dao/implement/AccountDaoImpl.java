package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.repository.AccountRepository;

public class AccountDaoImpl implements AccountDao {

    private AccountRepository accountRepository;

    @Override
    public Account addAccount(AccountDto dto) {
        Account account = setAccount(dto);
        account.setAccountNumber("123435");
        return accountRepository.save(account);
    }

    @Override
    public Account findByCif(String cif) {
        Account account = accountRepository.findByCustomer_Cif(cif);
        if (account!=null){
            return account;
        }
        return null;
    }

    @Override
    public Account updateBalance(Account account) throws AccountNotFoundException {
        Account account1 = accountRepository.findById(account.getAccountNumber())
                .map(ent ->{
                    ent.setBalance(account.getBalance());
                    return accountRepository.save(ent);
                }).orElseGet(()->{
                    account.setAccountNumber(account.getAccountNumber());
                    return accountRepository.save(account);
                });
        if (account1==null){
            throw new AccountNotFoundException(02, String.format("Id %s Not found", account.getAccountNumber()));
        }
        return account1;
    }

    private Account setAccount(AccountDto dto){
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        account.setName(dto.getName());
        account.setCustomer(dto.getCustomer());
        return account;
    }
}
