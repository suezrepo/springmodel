package com.ihm.dao.customer;
import com.ihm.model.customer.CustomerUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerUserProfileDAO extends JpaRepository<CustomerUserProfile, Long>, JpaSpecificationExecutor<CustomerUserProfile> {
}
