package com.ihm.service.customer;

import com.ihm.dao.customer.CustomerUserProfileDAO;
import com.ihm.model.customer.CustomerUserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerUserProfileServiceImpl implements CustomerUserProfileService {

	@Autowired
    CustomerUserProfileDAO customerUserProfileDAO;

	public long countAllCustomerUserProfiles() {
        return customerUserProfileDAO.count();
    }

	public void deleteCustomerUserProfile(CustomerUserProfile customerUserProfile) {
        customerUserProfileDAO.delete(customerUserProfile);
    }

	public CustomerUserProfile findCustomerUserProfile(Long id) {
        return customerUserProfileDAO.findOne(id);
    }

	public List<CustomerUserProfile> findAllCustomerUserProfiles() {
        return customerUserProfileDAO.findAll();
    }

	public List<CustomerUserProfile> findCustomerUserProfileEntries(int firstResult, int maxResults) {
        return customerUserProfileDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCustomerUserProfile(CustomerUserProfile customerUserProfile) {
        customerUserProfileDAO.save(customerUserProfile);
    }

	public CustomerUserProfile updateCustomerUserProfile(CustomerUserProfile customerUserProfile) {
        return customerUserProfileDAO.save(customerUserProfile);
    }
}
