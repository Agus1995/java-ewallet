package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dto.CustomerDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import com.finalproject.walletforex.model.Customer;
import com.finalproject.walletforex.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@EnableAsync
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
//        if (customer == null){
//            throw new InvalidUsernameOrPasswordException(03, "Invalid Username or Password");
//        }else if (customer.getPassword().equals(dto.getPassword())){
//            return customer;
//        }else {
//            throw new InvalidUsernameOrPasswordException(03, "Invalid Username or Password");
//        }
        return customer;
    }


    @Override
    public Customer findById(String id) throws AccountNotFoundException, InterruptedException {
        Customer customer = new Customer();
        customer.setCif("50");
        customer = customerRepository.findById(id).orElse(null);

//        if (customer == null){
//            throw new AccountNotFoundException(2, "Customer Not Found");
//        }
       Thread.sleep(2000);
       return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    private Customer findByUsername(String username){
        return customerRepository.findByUsername(username);
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
