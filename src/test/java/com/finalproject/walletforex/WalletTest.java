package com.finalproject.walletforex;


import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.exception.UserAlreadyException;
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
public class WalletTest {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CustomerDao customerDao;

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Before
    public void setDao() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername("agzwi");
        customer.setPassword("1234567");
        customer.setFirstName("agzwibawa");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("ag-01");

        WalletDto walletDto = new WalletDto();
        walletDto.setCustomer(customer1);
        walletDto.setWalletName("istri1");
        walletDao.addWallet(walletDto);
    }

    @Test
    public void testAdd() throws UserAlreadyException {
        CustomerDto customer = new CustomerDto();
        customer.setUsername("nofita");
        customer.setPassword("12345");
        customer.setFirstName("nofi");
        customerDao.registerCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setCif("no-11");

        WalletDto walletDto = new WalletDto();
        walletDto.setCustomer(customer1);
        walletDto.setWalletName("istri");
        assertEquals("istri", walletDao.addWallet(walletDto).getWalletName());
    }

    @Test
    public void getByCif() {
        assertEquals("istri1", walletDao.findByCif("ag-01").get(0).getWalletName());
    }


}
