package com.ihm.service.seller;

import com.ihm.dao.seller.SellerUserCredentialDAO;
import com.ihm.model.seller.SellerUserCredential;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerUserCredentialServiceImpl implements SellerUserCredentialService {

	@Autowired
    SellerUserCredentialDAO sellerUserCredentialDAO;

	public long countAllSellerUserCredentials() {
        return sellerUserCredentialDAO.count();
    }

	public void deleteSellerUserCredential(SellerUserCredential sellerUserCredential) {
        sellerUserCredentialDAO.delete(sellerUserCredential);
    }

	public SellerUserCredential findSellerUserCredential(Long id) {
        return sellerUserCredentialDAO.findOne(id);
    }

	public List<SellerUserCredential> findAllSellerUserCredentials() {
        return sellerUserCredentialDAO.findAll();
    }

	public List<SellerUserCredential> findSellerUserCredentialEntries(int firstResult, int maxResults) {
        return sellerUserCredentialDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveSellerUserCredential(SellerUserCredential sellerUserCredential) {
        sellerUserCredentialDAO.save(sellerUserCredential);
    }

	public SellerUserCredential updateSellerUserCredential(SellerUserCredential sellerUserCredential) {
        return sellerUserCredentialDAO.save(sellerUserCredential);
    }
}
