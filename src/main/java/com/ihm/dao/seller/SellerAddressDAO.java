package com.ihm.dao.seller;
import com.ihm.model.seller.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddressDAO extends JpaRepository<SellerAddress, Long>, JpaSpecificationExecutor<SellerAddress> {
}
