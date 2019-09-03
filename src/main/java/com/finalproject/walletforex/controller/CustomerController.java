package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private static final String CUSTOMER_LOGIN = "/login"; //path login
    private static final String CUSTOMER_REGISTER = "/register"; //path register
    private static final String CUSTOMER_PROFILE = "/customer/{id}";

    @Autowired
    private CustomerDao customerDao;



    @PostMapping(path = CUSTOMER_LOGIN)
    public CommonResponse<Customer> login(@RequestBody CustomerDto dto) throws InvalidUsernameOrPasswordException {
        CommonResponse<Customer> response = new CommonResponse<>();
        Customer customer = customerDao.login(dto);
        response.setData(customer);
        return response;
    }

    @PostMapping(path = CUSTOMER_REGISTER)
    public CommonResponse<Customer> register(@RequestBody CustomerDto dto) throws UserAlreadyException {
        CommonResponse<Customer> response = new CommonResponse<>();
        Customer customer = customerDao.registerCustomer(dto);
        response.setData(customer);
        return response;
    }

    @GetMapping(path = CUSTOMER_PROFILE)
    public CommonResponse<Customer> profile(@PathVariable(value = "id") String cif) throws AccountNotFoundException, InterruptedException {
        CommonResponse<Customer> response = new CommonResponse<>();
        Customer customer = customerDao.findById(cif);
        response.setData(customer);
        return response;
    }
}
