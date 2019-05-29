package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private static final String CUSTOMER_LOGIN = "/login"; //path login
    private static final String CUSTOMER_REGISTER = "/register"; //path register

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
    public CommonResponse<Customer> Register(@RequestBody CustomerDto dto) throws InvalidUsernameOrPasswordException, UserAlreadyException {
        CommonResponse<Customer> response = new CommonResponse<>();
        Customer customer = customerDao.registerCustomer(dto);
        response.setData(customer);
        return response;
    }




}
