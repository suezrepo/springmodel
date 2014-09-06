package com.ihm.dao.seller;
import com.ihm.model.seller.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoDAO extends JpaSpecificationExecutor<SellerInfo>, JpaRepository<SellerInfo, Long> {
}
