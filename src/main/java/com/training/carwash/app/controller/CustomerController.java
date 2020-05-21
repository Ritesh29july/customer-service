package com.training.carwash.app.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.training.carwash.app.model.Customer;
import com.training.carwash.app.service.CustomerService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {


  private Logger log = LoggerFactory.getLogger(this.getClass().getName());

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }


  @GetMapping("customer/")
  public ResponseEntity<List<Customer>> getCustomerDetails() {

    List<Customer> list = customerService.getCustomerDetails();
    // Customer list = bookingService.getCustomerDetails();

    log.info("inside method " + list);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping("customer/")
  public ResponseEntity<HttpStatus> receiveEvent(@RequestBody Customer customer) {
    boolean processed = true;
    customerService.saveCustomerDetails(customer);
    return new ResponseEntity<>(processed ? HttpStatus.OK : HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("customer/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
    boolean processed = true;
    customerService.deleteCustomerById(id);
    return new ResponseEntity<>(processed ? HttpStatus.OK : HttpStatus.NO_CONTENT);
  }

  @PutMapping("/customer/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,
      @RequestBody Customer customer) {
    Optional<Customer> customerData = customerService.findById(id);

    if (customerData.isPresent()) {
      Customer _customer = customerData.get();
      _customer.setActive(customer.getActive());
      _customer.setAddress(customer.getAddress());
      _customer.setCity(customer.getCity());
      _customer.setName(customer.getName());
      _customer.setPhoneNumber(customer.getPhoneNumber());

      return new ResponseEntity<>(customerService.updateCustomer(_customer), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
