package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Customer;
import com.finalproject.walletforex.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(CustomerDto dto) throws UserAlreadyException {
        Customer customer = setCustomer(dto);
        String head = customer.getFirstName().substring(0,2);
        customer.setCif(String.valueOf(head + "-" + count()+1));
        Customer customer1 = findByUsername(dto.getUsername());
        if (customer1 != null){
            throw new UserAlreadyException(02, String.format("User %s already exist", dto.getUsername()));
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer login(CustomerDto dto) throws InvalidUsernameOrPasswordException {
        Customer customer = findByUsername(dto.getUsername());
        if (customer == null){
            throw new InvalidUsernameOrPasswordException(03, "Invalid Username or Password");
        }else if (customer.getPassword().equals(dto.getPassword())){
            return customer;
        }else {
            throw new InvalidUsernameOrPasswordException(03, "Invalid Username or Password");
        }
    }

    @Override
    public Customer findById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }


    private Customer findByUsername(String username){
        Customer customer = new Customer();
        customer = customerRepository.findByUsername(username);
        return customer;
    }

    private Customer setCustomer(CustomerDto dto){
        Customer customer = new Customer();
        customer.setCif(dto.getCif());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setNik(dto.getNik());
        customer.setBirthDate(dto.getBirthDate());
        customer.setMothersName(dto.getMothersName());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setUsername(dto.getUsername());
        customer.setPassword(dto.getPassword());
        return customer;
    }

    private long count(){
        return customerRepository.count();
    }
}
