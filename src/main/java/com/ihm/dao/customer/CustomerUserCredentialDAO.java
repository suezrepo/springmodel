package com.ihm.dao.customer;
import com.ihm.model.customer.CustomerUserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerUserCredentialDAO extends JpaRepository<CustomerUserCredential, Long>, JpaSpecificationExecutor<CustomerUserCredential> {
}
