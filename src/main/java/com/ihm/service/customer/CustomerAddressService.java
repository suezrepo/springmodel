package com.ihm.service.customer;
import com.ihm.model.customer.CustomerAddress;
import java.util.List;

public interface CustomerAddressService {

	public abstract long countAllCustomerAddresses();


	public abstract void deleteCustomerAddress(CustomerAddress customerAddress);


	public abstract CustomerAddress findCustomerAddress(Long id);


	public abstract List<CustomerAddress> findAllCustomerAddresses();


	public abstract List<CustomerAddress> findCustomerAddressEntries(int firstResult, int maxResults);


	public abstract void saveCustomerAddress(CustomerAddress customerAddress);


	public abstract CustomerAddress updateCustomerAddress(CustomerAddress customerAddress);

}
