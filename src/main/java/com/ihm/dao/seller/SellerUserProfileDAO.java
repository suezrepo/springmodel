package com.ihm.dao.seller;
import com.ihm.model.seller.SellerUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerUserProfileDAO extends JpaRepository<SellerUserProfile, Long>, JpaSpecificationExecutor<SellerUserProfile> {
}
