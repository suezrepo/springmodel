package com.ihm.service.customer;
import com.ihm.model.customer.CustomerUserProfile;
import java.util.List;

public interface CustomerUserProfileService {

	public abstract long countAllCustomerUserProfiles();


	public abstract void deleteCustomerUserProfile(CustomerUserProfile customerUserProfile);


	public abstract CustomerUserProfile findCustomerUserProfile(Long id);


	public abstract List<CustomerUserProfile> findAllCustomerUserProfiles();


	public abstract List<CustomerUserProfile> findCustomerUserProfileEntries(int firstResult, int maxResults);


	public abstract void saveCustomerUserProfile(CustomerUserProfile customerUserProfile);


	public abstract CustomerUserProfile updateCustomerUserProfile(CustomerUserProfile customerUserProfile);

}
