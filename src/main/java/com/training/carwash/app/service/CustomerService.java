package com.training.carwash.app.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.carwash.app.model.Customer;
import com.training.carwash.app.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {


  private Logger log = LoggerFactory.getLogger(this.getClass().getName());


  private CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getCustomerDetails() {
    return customerRepository.findAll();
  }

  public void saveCustomerDetails(Customer customer) {
    customerRepository.save(customer);
  }

  public void deleteCustomerById(long id) {
    customerRepository.deleteById(id);
  }

  public Customer updateCustomer(Customer _customer) {

    return customerRepository.save(_customer);
  }

  public Optional<Customer> findById(long id) {
    return customerRepository.findById(id);
  }
}
