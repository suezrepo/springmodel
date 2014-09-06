package com.ihm.service.seller;

import com.ihm.dao.seller.SellerAddressDAO;
import com.ihm.model.seller.SellerAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerAddressServiceImpl implements SellerAddressService {

	@Autowired
    SellerAddressDAO sellerAddressDAO;

	public long countAllSellerAddresses() {
        return sellerAddressDAO.count();
    }

	public void deleteSellerAddress(SellerAddress sellerAddress) {
        sellerAddressDAO.delete(sellerAddress);
    }

	public SellerAddress findSellerAddress(Long id) {
        return sellerAddressDAO.findOne(id);
    }

	public List<SellerAddress> findAllSellerAddresses() {
        return sellerAddressDAO.findAll();
    }

	public List<SellerAddress> findSellerAddressEntries(int firstResult, int maxResults) {
        return sellerAddressDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveSellerAddress(SellerAddress sellerAddress) {
        sellerAddressDAO.save(sellerAddress);
    }

	public SellerAddress updateSellerAddress(SellerAddress sellerAddress) {
        return sellerAddressDAO.save(sellerAddress);
    }
}
