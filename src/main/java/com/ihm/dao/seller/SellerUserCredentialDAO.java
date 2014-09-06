package com.ihm.dao.seller;
import com.ihm.model.seller.SellerUserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerUserCredentialDAO extends JpaSpecificationExecutor<SellerUserCredential>, JpaRepository<SellerUserCredential, Long> {
}
