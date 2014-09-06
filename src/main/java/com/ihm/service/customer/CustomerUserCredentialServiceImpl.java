package com.ihm.service.customer;

import com.ihm.dao.customer.CustomerUserCredentialDAO;
import com.ihm.model.customer.CustomerUserCredential;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerUserCredentialServiceImpl implements CustomerUserCredentialService {

	@Autowired
    CustomerUserCredentialDAO customerUserCredentialDAO;

	public long countAllCustomerUserCredentials() {
        return customerUserCredentialDAO.count();
    }

	public void deleteCustomerUserCredential(CustomerUserCredential customerUserCredential) {
        customerUserCredentialDAO.delete(customerUserCredential);
    }

	public CustomerUserCredential findCustomerUserCredential(Long id) {
        return customerUserCredentialDAO.findOne(id);
    }

	public List<CustomerUserCredential> findAllCustomerUserCredentials() {
        return customerUserCredentialDAO.findAll();
    }

	public List<CustomerUserCredential> findCustomerUserCredentialEntries(int firstResult, int maxResults) {
        return customerUserCredentialDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCustomerUserCredential(CustomerUserCredential customerUserCredential) {
        customerUserCredentialDAO.save(customerUserCredential);
    }

	public CustomerUserCredential updateCustomerUserCredential(CustomerUserCredential customerUserCredential) {
        return customerUserCredentialDAO.save(customerUserCredential);
    }
}
