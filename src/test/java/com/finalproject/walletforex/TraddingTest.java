package com.finalproject.walletforex;


import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.dto.KursDto;
import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.exception.WalletNotFoundException;
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
public class TraddingTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TraddingDao traddingDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private KursDao kursDao;
    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Before
    public void setKurs(){
        KursDto kursDto = new KursDto();
        kursDto.setCcy1("IDR");
        kursDto.setCcy2("USD");
        kursDto.setBuy(15000);
        kursDto.setSell(14500);
        kursDao.addNew(kursDto);
    }

    @Test
    public void buyTrad1() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomer(customerDao.registerCustomer(customerDto));
        accountDto.setCurrencyType("IDR");
        accountDto.setBalance(2000000000);
        accountDto.setName("tradding");
        TraddingDto traddingDto = new TraddingDto();
        traddingDto.setCcy("USD");
        traddingDto.setAccount(accountDao.addAccount(accountDto).getAccountNumber());
        traddingDto.setCustomer(accountDto.getCustomer());
        traddingDto.setAmount((double) 10);
        traddingDto.setRate((double) 14500);
        assertEquals(traddingDto.getRate(), traddingDao.buy(traddingDto).getRate());
    }
    @Test
    public void buyTrad2() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException, WalletNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomer(customerDao.registerCustomer(customerDto));
        accountDto.setCurrencyType("IDR");
        accountDto.setBalance(2000000000);
        accountDto.setName("traddingggg");
        TraddingDto traddingDto = new TraddingDto();
        traddingDto.setCcy("USD");
        traddingDto.setAccount(accountDao.addAccount(accountDto).getAccountNumber());
        traddingDto.setCustomer(accountDto.getCustomer());
        traddingDto.setAmount((double) 10);
        traddingDto.setRate((double) 14500);
        traddingDao.buy(traddingDto);
        assertEquals(traddingDto.getRate(), traddingDao.getByCif(accountDto.getCustomer().getCif()).get(0).getRate());
    }
    @Test
    public void buyTrad3() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException, WalletNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomer(customerDao.registerCustomer(customerDto));
        accountDto.setCurrencyType("IDR");
        accountDto.setBalance(2000000000);
        accountDto.setName("traddingggg");
        TraddingDto traddingDto = new TraddingDto();
        traddingDto.setCcy("USD");
        traddingDto.setAccount(accountDao.addAccount(accountDto).getAccountNumber());
        traddingDto.setCustomer(accountDto.getCustomer());
        traddingDto.setAmount((double) 10);
        traddingDto.setRate((double) 15000);
        traddingDao.buy(traddingDto);
        assertEquals(traddingDto.getAmount(), traddingDao.getByCif(accountDto.getCustomer().getCif()).get(0).getAmount());
    }
    @Test
    public void buyTrad4() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException, WalletNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomer(customerDao.registerCustomer(customerDto));
        accountDto.setCurrencyType("IDR");
        accountDto.setBalance(20000);
        accountDto.setName("traddingggg");
        TraddingDto traddingDto = new TraddingDto();
        traddingDto.setCcy("USD");
        traddingDto.setAccount(accountDao.addAccount(accountDto).getAccountNumber());
        traddingDto.setCustomer(accountDto.getCustomer());
        traddingDto.setAmount((double) 1);
        traddingDto.setRate((double) 15000);
        traddingDao.buy(traddingDto);
        assertEquals(String.valueOf(5500.0), String.valueOf(accountDao.findById(traddingDto.getAccount()).getBalance()));
    }
    @Test
    public void negativeBuyTrad() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException {
        rule.expect(BalanceNotEnoughException.class);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomer(customerDao.registerCustomer(customerDto));
        accountDto.setCurrencyType("IDR");
        accountDto.setBalance(200000000);
        accountDto.setName("tradding");
        TraddingDto traddingDto = new TraddingDto();
        traddingDto.setCcy("USD");
        traddingDto.setAccount(accountDao.addAccount(accountDto).getAccountNumber());
        traddingDto.setCustomer(accountDto.getCustomer());
        traddingDto.setAmount((double) 100000000);
        traddingDto.setRate((double) 15000);
        assertEquals(traddingDto.getRate(), traddingDao.buy(traddingDto).getRate());
    }
}
