package com.ihm.service.customer;

import com.ihm.dao.customer.CustomerAddressDAO;
import com.ihm.model.customer.CustomerAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerAddressServiceImpl implements CustomerAddressService {

	@Autowired
    CustomerAddressDAO customerAddressDAO;

	public long countAllCustomerAddresses() {
        return customerAddressDAO.count();
    }

	public void deleteCustomerAddress(CustomerAddress customerAddress) {
        customerAddressDAO.delete(customerAddress);
    }

	public CustomerAddress findCustomerAddress(Long id) {
        return customerAddressDAO.findOne(id);
    }

	public List<CustomerAddress> findAllCustomerAddresses() {
        return customerAddressDAO.findAll();
    }

	public List<CustomerAddress> findCustomerAddressEntries(int firstResult, int maxResults) {
        return customerAddressDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCustomerAddress(CustomerAddress customerAddress) {
        customerAddressDAO.save(customerAddress);
    }

	public CustomerAddress updateCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressDAO.save(customerAddress);
    }
}
