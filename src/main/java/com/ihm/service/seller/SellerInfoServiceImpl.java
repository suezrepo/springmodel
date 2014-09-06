package com.ihm.service.seller;

import com.ihm.dao.seller.SellerInfoDAO;
import com.ihm.model.seller.SellerInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerInfoServiceImpl implements SellerInfoService {

	@Autowired
    SellerInfoDAO sellerInfoDAO;

	public long countAllSellerInfoes() {
        return sellerInfoDAO.count();
    }

	public void deleteSellerInfo(SellerInfo sellerInfo) {
        sellerInfoDAO.delete(sellerInfo);
    }

	public SellerInfo findSellerInfo(Long id) {
        return sellerInfoDAO.findOne(id);
    }

	public List<SellerInfo> findAllSellerInfoes() {
        return sellerInfoDAO.findAll();
    }

	public List<SellerInfo> findSellerInfoEntries(int firstResult, int maxResults) {
        return sellerInfoDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveSellerInfo(SellerInfo sellerInfo) {
        sellerInfoDAO.save(sellerInfo);
    }

	public SellerInfo updateSellerInfo(SellerInfo sellerInfo) {
        return sellerInfoDAO.save(sellerInfo);
    }
}
