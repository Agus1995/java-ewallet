package com.finalproject.walletforex;


import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
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
public class CustomerTest {

    @Autowired
    private CustomerDao customerDao;

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Before
    public void setDao() throws UserAlreadyException {
        for (int i = 0; i < 5; i++){
            int row = i+1;
            CustomerDto customer = new CustomerDto();
            customer.setUsername(String.format("agus%s",i));
            customer.setPassword("1234");
            customer.setFirstName("pay");
            customerDao.registerCustomer(customer);
        }
    }

    @Test
    public void getByIdCustomer() throws AccountNotFoundException, InterruptedException {
        assertEquals("pay", customerDao.findById("pa-01").getFirstName());
    }

    @Test
    public void getByIdCustomerNegative() throws AccountNotFoundException, InterruptedException {
		rule.expect(AccountNotFoundException.class);
        assertEquals("1", customerDao.findById("1").getCif());
    }

    @Test
    public void loginTest() throws InvalidUsernameOrPasswordException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus1");
        customerDto.setPassword("1234");
        assertEquals(customerDto.getUsername(), customerDao.login(customerDto).getUsername());
    }

    @Test
    public void negativeLoginTest() throws InvalidUsernameOrPasswordException {
        rule.expect(InvalidUsernameOrPasswordException.class);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("agus1");
        customerDto.setPassword("12345");
        assertEquals(customerDto.getUsername(), customerDao.login(customerDto).getUsername());
    }

    @Test
    public void registerTest() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername(String.format("aguswibawa"));
        customer.setPassword("1234");
        customer.setFirstName("pay");
        assertEquals(customer.getUsername(), customerDao.registerCustomer(customer).getUsername());
    }

    @Test
    public void registerNegativeTest() throws UserAlreadyException {
        rule.expect(UserAlreadyException.class);
        CustomerDto customer = new CustomerDto();
        customer.setUsername(String.format("agus1"));
        customer.setPassword("1234");
        customer.setFirstName("pay");
        assertEquals(customer.getUsername(), customerDao.registerCustomer(customer).getUsername());
    }
}
