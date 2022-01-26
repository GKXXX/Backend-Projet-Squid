package repositories;

import entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;

public interface UserRepository extends CrudRepository<Customer,Integer> {

    public HashMap<Integer,Customer> getAllClients();

    public HashMap<Integer,Customer> getClientById(int id);

    public HashMap<Integer,Customer> createClient(Customer newCustomer);
}
