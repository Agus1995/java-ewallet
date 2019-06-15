package com.finalproject.walletforex;


import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.WalletAccountDao;
import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.dto.WalletAccountDto;
import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.Customer;
import com.finalproject.walletforex.model.Wallet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletAccountTest {

    @Autowired
    private WalletAccountDao walletAccountDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WalletDao walletDao;

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Before
    public void setDao() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername("danis");
        customer.setPassword("1234567");
        customer.setFirstName("danis");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("da-01");

        AccountDto accountDto = new AccountDto();
        WalletDto walletDto = new WalletDto();

        accountDto.setCustomer(customer1);
        accountDto.setName("bojo");
        accountDto.setCurrencyType("USD");
        accountDto.setBalance(100);


        walletDto.setCustomer(customer1);
        walletDto.setWalletName("ovo");


        Account account = new Account();
        Wallet wallet = new Wallet();
        account.setAccountNumber("bo-01");
        wallet.setWalletId("ov-01");

        WalletAccountDto walletAccountDto = new WalletAccountDto();
        walletAccountDto.setAccount(accountDao.addAccount(accountDto));
        walletAccountDto.setWallet(walletDao.addWallet(walletDto));

        walletAccountDao.register(walletAccountDto);
   }

    @Test
    public void getRegisteredTest() throws AccountNotFoundException, UserAlreadyException {
        assertEquals(1, walletAccountDao.getRegistered("da-01").size());
    }

    @Test
    public void registerTest() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername("daniss");
        customer.setPassword("1234567");
        customer.setFirstName("danis");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("da-01");

        AccountDto accountDto = new AccountDto();
        WalletDto walletDto = new WalletDto();

        accountDto.setCustomer(customer1);
        accountDto.setName("bojo");
        accountDto.setCurrencyType("USD");
        accountDto.setBalance(100);


        walletDto.setCustomer(customer1);
        walletDto.setWalletName("ovo");


        Account account = new Account();
        Wallet wallet = new Wallet();
        account.setAccountNumber("bo-01");
        wallet.setWalletId("ov-01");

        WalletAccountDto walletAccountDto = new WalletAccountDto();
        walletAccountDto.setAccount(accountDao.addAccount(accountDto));
        walletAccountDto.setWallet(walletDao.addWallet(walletDto));
//        walletAccountDao.register(walletAccountDto);
        assertEquals(walletAccountDto.getAccount(), walletAccountDao.register(walletAccountDto).getAccount());
    }

    @Test
    public void negativeAdd() throws UserAlreadyException {
        rule.expect(UserAlreadyException.class);
        CustomerDto customer = new CustomerDto();
        customer.setUsername("danis");
        customer.setPassword("1234567");
        customer.setFirstName("danis");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("da-01");

        AccountDto accountDto = new AccountDto();
        WalletDto walletDto = new WalletDto();

        accountDto.setCustomer(customer1);
        accountDto.setName("bojo");
        accountDto.setCurrencyType("USD");
        accountDto.setBalance(100);


        walletDto.setCustomer(customer1);
        walletDto.setWalletName("ovo");


        Account account = new Account();
        Wallet wallet = new Wallet();
        account.setAccountNumber("bo-01");
        wallet.setWalletId("ov-01");

        WalletAccountDto walletAccountDto = new WalletAccountDto();
        walletAccountDto.setAccount(accountDao.addAccount(accountDto));
        walletAccountDto.setWallet(walletDao.addWallet(walletDto));

        walletAccountDao.register(walletAccountDto);
    }

}
