package academy.digitallab.store.servicecustomer.service;

import java.util.List;

import academy.digitallab.store.servicecustomer.entity.Customer;
import academy.digitallab.store.servicecustomer.entity.Region;

public interface CustomerService {

	public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);
	
}
