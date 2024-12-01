package com.microserviceunittestingapp.customerservice.service;

import com.microserviceunittestingapp.customerservice.dto.CustomerDTO;
import com.microserviceunittestingapp.customerservice.exceptions.CustomerNotFoundException;
import com.microserviceunittestingapp.customerservice.exceptions.EmailAlreadyExistException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException;
    List<CustomerDTO> getAllCustomers();
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> searchCustomers(String keyword);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO)throws CustomerNotFoundException;
    void deleteCustomer(Long id)throws CustomerNotFoundException;
}
