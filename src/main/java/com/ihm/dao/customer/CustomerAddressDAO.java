package com.ihm.dao.customer;
import com.ihm.model.customer.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressDAO extends JpaRepository<CustomerAddress, Long>, JpaSpecificationExecutor<CustomerAddress> {
}
