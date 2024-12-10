package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.repository.CustomerRepository;
import app.repository.ProductRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ProductRepository productService;

    public CustomerServiceImpl(CustomerRepository repository, ProductRepository productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public Customer save(Customer customer) throws CustomerSaveException {
        if (customer == null){
            throw new CustomerSaveException("Customer cannot be null");
        }
        if (customer.getName() == null || customer.getName().length() < 3){
            throw new CustomerSaveException("Customers name is incorrect");
        }

        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(Customer::isActive)
                .toList();
    }

    @Override
    public Customer getActiveCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = repository.findById(id);
        if (customer == null || !customer.isActive()){
            throw new CustomerNotFoundException(String.format(
                    "Customer with ID %d does not exist", id
            ));
        }
        return  customer;
    }

    @Override
    public void updateActiveCustomer(Customer customer) throws CustomerUpdateException, CustomerNotFoundException {
        if (customer == null){
            throw new CustomerUpdateException("Customer cannot be null");
        }
        if (customer.getName() == null || customer.getName().length() < 3){
            throw new CustomerUpdateException("Customers name is incorrect");
        }

        Customer existedCustomer = getActiveCustomerById(customer.getId());
        if (existedCustomer == null){
            throw new CustomerUpdateException("Customer inactive or does not exist");
        }
        repository.update(customer);
    }

    @Override
    public void deleteById(Long id) throws CustomerNotFoundException {
        Customer customer = getActiveCustomerById(id);
        if (customer != null){
            customer.setActive(false);
        }
    }

    @Override
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }

    @Override
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
        if (customer != null){
            customer.setActive(true);
        }
    }

    @Override
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCustomersCartTotalPrice(Long id) throws CustomerNotFoundException {
        return getActiveCustomerById(id).getAllActiveProductsTotalCost();

    }

    @Override
    public double getCustomersCartAveragePrice(Long id) {
        return 0;
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) {
//        Customer customer = getActiveCustomerById(customerId);
//        Product product = productService.

    }

    @Override
    public void deleteProductFromCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCustomersCart(Long id) {

    }

    public ProductRepository getProductService() {
        return productService;
    }
}
