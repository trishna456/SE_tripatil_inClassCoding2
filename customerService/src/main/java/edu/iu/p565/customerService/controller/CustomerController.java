package edu.iu.p565.customerService.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import edu.iu.p565.customerService.model.Customer;
import edu.iu.p565.customerService.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Customer> findAll(){
        return repository.findAll();
    }


    @PostMapping
    public int create(@Valid @RequestBody Customer customer){
        Customer addedCustomer =  repository.save(customer);
        return addedCustomer.getId();
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Customer customer = new Customer ();
        customer.setId(id);
        repository.delete(customer);
    }
}