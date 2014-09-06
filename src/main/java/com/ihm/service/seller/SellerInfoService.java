package com.ihm.service.seller;
import com.ihm.model.seller.SellerInfo;
import java.util.List;

public interface SellerInfoService {

	public abstract long countAllSellerInfoes();


	public abstract void deleteSellerInfo(SellerInfo sellerInfo);


	public abstract SellerInfo findSellerInfo(Long id);


	public abstract List<SellerInfo> findAllSellerInfoes();


	public abstract List<SellerInfo> findSellerInfoEntries(int firstResult, int maxResults);


	public abstract void saveSellerInfo(SellerInfo sellerInfo);


	public abstract SellerInfo updateSellerInfo(SellerInfo sellerInfo);

}
