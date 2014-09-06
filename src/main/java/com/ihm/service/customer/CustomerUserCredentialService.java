package com.ihm.service.customer;
import com.ihm.model.customer.CustomerUserCredential;
import java.util.List;

public interface CustomerUserCredentialService {

	public abstract long countAllCustomerUserCredentials();


	public abstract void deleteCustomerUserCredential(CustomerUserCredential customerUserCredential);


	public abstract CustomerUserCredential findCustomerUserCredential(Long id);


	public abstract List<CustomerUserCredential> findAllCustomerUserCredentials();


	public abstract List<CustomerUserCredential> findCustomerUserCredentialEntries(int firstResult, int maxResults);


	public abstract void saveCustomerUserCredential(CustomerUserCredential customerUserCredential);


	public abstract CustomerUserCredential updateCustomerUserCredential(CustomerUserCredential customerUserCredential);

}
