package app.controller;

import app.domain.Customer;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }
    public Customer save(String name) throws CustomerSaveException {
        Customer customer = new Customer(name);
        return service.save(customer);
    }
    private List<Customer> getAllActiveCustomers(){
        return service.getAllActiveCustomers();
    }
    private Customer getActiveCustomerById(Long id) throws CustomerNotFoundException{
        return service.getActiveCustomerById(id);
    }
    private void updateActiveCustomer(Long id, String name) throws CustomerUpdateException, CustomerNotFoundException{
         Customer customer = new Customer(id, name);
         service.updateActiveCustomer(customer);
    }
    private void deleteById(Long id) throws CustomerNotFoundException{
         service.deleteById(id);
    }
    private void deleteByName(String name){
         service.deleteByName(name);
    }
    private void restoreById(Long id){
         service.restoreById(id);
    }
    private int getActiveCustomersNumber(){
         return service.getActiveCustomersNumber();
    }
    private double getCustomersCartTotalPrice(Long id) throws CustomerNotFoundException{
         return service.getCustomersCartTotalPrice(id);
    }
    private double getCustomersCartAveragePrice(Long id){
         return service.getCustomersCartAveragePrice(id);
    }
    private void addProductToCustomersCart(Long customerId, Long productId){
         service.addProductToCustomersCart(customerId, productId);
    }
    private void deleteProductFromCustomersCart(Long customerId, Long productId){
         service.deleteProductFromCustomersCart(customerId, productId);
    }
    private void clearCustomersCart(Long id){
         service.clearCustomersCart(id);
}

}
