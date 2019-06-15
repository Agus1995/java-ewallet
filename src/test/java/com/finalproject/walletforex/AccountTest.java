package com.finalproject.walletforex;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.Customer;
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
public class AccountTest {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CustomerDao customerDao;

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Before
    public void setDao() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername("agzw");
        customer.setPassword("1234");
        customer.setFirstName("agz");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("ag-01");

        AccountDto accountDto = new AccountDto();
        accountDto.setName("istri");
        accountDto.setBalance(10000);
        accountDto.setCurrencyType("IDR");
        accountDto.setCustomer(customer1);
        accountDao.addAccount(accountDto);
    }

    @Test
    public void getByIdAccount() throws AccountNotFoundException {
        assertEquals("istri", accountDao.findById("is-01").getName());
    }

    @Test
    public void negativeGetByIdAccount() throws AccountNotFoundException {
        rule.expect(AccountNotFoundException.class);
        assertEquals("istri", accountDao.findById("is-011").getName());
    }

    @Test
    public void findByCif(){
        assertEquals(1, accountDao.findByCif("ag-01").size());
    }

    @Test
    public void updateBalanceTest() throws AccountNotFoundException {
        Account account = new Account();
        account.setAccountNumber("is-01");
        account.setBalance(10000);
        account.setCurencyType("IDR");
        assertEquals(String.valueOf(10000.0), String.valueOf(accountDao.updateBalance(account).getBalance()));
    }
}
