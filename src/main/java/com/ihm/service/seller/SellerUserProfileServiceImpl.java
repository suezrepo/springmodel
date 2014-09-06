package com.ihm.service.seller;

import com.ihm.dao.seller.SellerUserProfileDAO;
import com.ihm.model.seller.SellerUserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerUserProfileServiceImpl implements SellerUserProfileService {

	@Autowired
    SellerUserProfileDAO sellerUserProfileDAO;

	public long countAllSellerUserProfiles() {
        return sellerUserProfileDAO.count();
    }

	public void deleteSellerUserProfile(SellerUserProfile sellerUserProfile) {
        sellerUserProfileDAO.delete(sellerUserProfile);
    }

	public SellerUserProfile findSellerUserProfile(Long id) {
        return sellerUserProfileDAO.findOne(id);
    }

	public List<SellerUserProfile> findAllSellerUserProfiles() {
        return sellerUserProfileDAO.findAll();
    }

	public List<SellerUserProfile> findSellerUserProfileEntries(int firstResult, int maxResults) {
        return sellerUserProfileDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveSellerUserProfile(SellerUserProfile sellerUserProfile) {
        sellerUserProfileDAO.save(sellerUserProfile);
    }

	public SellerUserProfile updateSellerUserProfile(SellerUserProfile sellerUserProfile) {
        return sellerUserProfileDAO.save(sellerUserProfile);
    }
}
