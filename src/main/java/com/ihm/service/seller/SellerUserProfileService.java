package com.ihm.service.seller;
import com.ihm.model.seller.SellerUserProfile;
import java.util.List;

public interface SellerUserProfileService {

	public abstract long countAllSellerUserProfiles();


	public abstract void deleteSellerUserProfile(SellerUserProfile sellerUserProfile);


	public abstract SellerUserProfile findSellerUserProfile(Long id);


	public abstract List<SellerUserProfile> findAllSellerUserProfiles();


	public abstract List<SellerUserProfile> findSellerUserProfileEntries(int firstResult, int maxResults);


	public abstract void saveSellerUserProfile(SellerUserProfile sellerUserProfile);


	public abstract SellerUserProfile updateSellerUserProfile(SellerUserProfile sellerUserProfile);

}
