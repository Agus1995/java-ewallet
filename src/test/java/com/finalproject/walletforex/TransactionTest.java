package com.finalproject.walletforex;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.TransactionDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.dto.TransactionDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Customer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionTest {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AccountDao accountDao;
    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Test
    public void transactionTest() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException {
        CustomerDto customerDto = new CustomerDto();
        AccountDto accountDto = new AccountDto();
        TransactionDto transactionDto = new TransactionDto();
        List<AccountDto> accountDtoList = new ArrayList<>();

        customerDto.setUsername("paijo");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        Customer customer = customerDao.registerCustomer(customerDto);

        for (int i = 0; i<2; i++){
            accountDto.setName("agus");
            accountDto.setBalance(100000);
            accountDto.setCurrencyType("IDR");
            accountDto.setCustomer(customer);
            accountDtoList.add(accountDto);
        }

        transactionDto.setAccCredit(accountDao.addAccount(accountDtoList.get(0)).getAccountNumber());
        transactionDto.setAccDebet(accountDao.addAccount(accountDtoList.get(1)).getAccountNumber());
        transactionDto.setAmount(50000);
        assertEquals(String.valueOf(transactionDto.getAmount()), String.valueOf(transactionDao.transaction(transactionDto).getAmount()));
    }

    @Test
    public void transactionTestException() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException {
        rule.expect(BalanceNotEnoughException.class);
        CustomerDto customerDto = new CustomerDto();
        AccountDto accountDto = new AccountDto();
        TransactionDto transactionDto = new TransactionDto();
        List<AccountDto> accountDtoList = new ArrayList<>();
        customerDto.setUsername("paijo");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        Customer customer = customerDao.registerCustomer(customerDto);
        for (int i = 0; i<3; i++){
            accountDto.setName("danis");
            accountDto.setBalance(1000000);
            accountDto.setCurrencyType("IDR");
            accountDto.setCustomer(customer);
            accountDtoList.add(accountDto);
        }
        transactionDto.setAccCredit(accountDao.addAccount(accountDtoList.get(0)).getAccountNumber());
        transactionDto.setAccDebet(accountDao.addAccount(accountDtoList.get(1)).getAccountNumber());
        transactionDto.setAmount(5000000);
        assertEquals(String.valueOf(transactionDto.getAmount()), String.valueOf(transactionDao.transaction(transactionDto).getAmount()));
    }

    @Test
    public void transactionTestAccount() throws UserAlreadyException, AccountNotFoundException, BalanceNotEnoughException {
        rule.expect(AccountNotFoundException.class);
        CustomerDto customerDto = new CustomerDto();
        AccountDto accountDto = new AccountDto();
        TransactionDto transactionDto = new TransactionDto();
        List<AccountDto> accountDtoList = new ArrayList<>();
        customerDto.setUsername("paijo");
        customerDto.setFirstName("agus");
        customerDto.setPassword("1234");
        Customer customer = customerDao.registerCustomer(customerDto);
        for (int i = 0; i<3; i++){
            accountDto.setName("citra");
            accountDto.setBalance(100000);
            accountDto.setCurrencyType("IDR");
            accountDto.setCustomer(customer);
            accountDtoList.add(accountDto);
        }
        transactionDto.setAccCredit("123");
        transactionDto.setAccDebet(accountDao.addAccount(accountDtoList.get(1)).getAccountNumber());
        transactionDto.setAmount(5000000);
        assertEquals(String.valueOf(transactionDto.getAmount()), String.valueOf(transactionDao.transaction(transactionDto).getAmount()));
    }
}
