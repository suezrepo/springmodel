package com.ihm.service.seller;
import com.ihm.model.seller.SellerAddress;
import java.util.List;

public interface SellerAddressService {

	public abstract long countAllSellerAddresses();


	public abstract void deleteSellerAddress(SellerAddress sellerAddress);


	public abstract SellerAddress findSellerAddress(Long id);


	public abstract List<SellerAddress> findAllSellerAddresses();


	public abstract List<SellerAddress> findSellerAddressEntries(int firstResult, int maxResults);


	public abstract void saveSellerAddress(SellerAddress sellerAddress);


	public abstract SellerAddress updateSellerAddress(SellerAddress sellerAddress);

}
