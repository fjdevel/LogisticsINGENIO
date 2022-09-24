package com.ingenio.logisticIngenio.services;

import com.ingenio.logisticIngenio.dtos.CustomerResponseDTO;
import com.ingenio.logisticIngenio.models.AppUser;
import com.ingenio.logisticIngenio.models.Customer;
import com.ingenio.logisticIngenio.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);

    }

    public Customer findByUser(AppUser user){
        return customerRepository.findByUser(user);
    }

    public Customer updateCustomer(Customer customer){
        Customer customerOld = customerRepository.findByNumDoc(customer.getNumDoc());
        customerOld.setAddress(customer.getAddress());
        customerOld.setName(customer.getName());
        customerOld.setNumDoc(customer.getNumDoc());
        return customerRepository.save(customerOld);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    public Customer findCustomerDoc(String doc){
        return  customerRepository.findByNumDoc(doc);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

}
