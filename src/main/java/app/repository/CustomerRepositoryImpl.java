package app.repository;

import app.domain.Customer;
import app.exceptions.CustomerSaveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {

    // Это имитация базы данных в виде map,
    // где ключ - это идентификатор покупателя,
    // значение - это сам объект покупателя.
    private final Map<Long, Customer> database = new HashMap<>();
    private long currentId = 0;

    @Override
    public Customer save(Customer customer) {
        customer.setId(++currentId);
        database.put(currentId, customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
//        return database.values().stream().toList();
        return new ArrayList<>(database.values());
    }

    @Override
    public Customer findById(Long id) {
        return database.get(id);
    }

    @Override
    public void update(Customer customer) {
        Long id = customer.getId();
        String newName = customer.getName();

        Customer existedCustomer = findById(id);
        if (existedCustomer != null) {
            existedCustomer.setName(newName);
        }
    }

    @Override
    public void removeById(Long id) {
        database.remove(id);
    }

    // Временный метод для ручного тестирования репозитория покупателей.
//    public static void main(String[] args) {
//
//        // Создаём объект репозитория
//        CustomerRepository repository = new CustomerRepositoryImpl();
//
//        // Тестируем метод save
//        repository.save(new Customer("John", true));
//        repository.save(new Customer("Jack", true));
//        repository.save(new Customer("Jenna", true));
//
//        // Тестируем метод findAll
////        repository.findAll().forEach(x -> System.out.println(x));
//
//        // Тестируем метод findById
////        System.out.println(repository.findById(3L));
//
//        // Тестируем метод update
////        System.out.println(repository.findById(1L));
////        repository.update(new Customer(1L, "John Doe"));
////        System.out.println(repository.findById(1L));
//
//        // Тестируем метод removeById
//        repository.removeById(1L);
//        repository.findAll().forEach(x -> System.out.println(x));
//    }
}
