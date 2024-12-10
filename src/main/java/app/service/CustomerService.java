package app.service;

import app.domain.Customer;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer) throws CustomerSaveException;
    List<Customer> getAllActiveCustomers();
    Customer getActiveCustomerById(Long id) throws CustomerNotFoundException;
    void updateActiveCustomer(Customer customer) throws CustomerUpdateException, CustomerNotFoundException;
    void deleteById(Long id) throws CustomerNotFoundException;
    void deleteByName(String name);
    void restoreById(Long id);
    int getActiveCustomersNumber();
    double getCustomersCartTotalPrice(Long id) throws CustomerNotFoundException;
    double getCustomersCartAveragePrice(Long id);
    void addProductToCustomersCart(Long customerId, Long productId);
    void deleteProductFromCustomersCart(Long customerId, Long productId);
    void clearCustomersCart(Long id);
}
