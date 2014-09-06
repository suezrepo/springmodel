package com.ihm.service.seller;
import com.ihm.model.seller.SellerUserCredential;
import java.util.List;

public interface SellerUserCredentialService {

	public abstract long countAllSellerUserCredentials();


	public abstract void deleteSellerUserCredential(SellerUserCredential sellerUserCredential);


	public abstract SellerUserCredential findSellerUserCredential(Long id);


	public abstract List<SellerUserCredential> findAllSellerUserCredentials();


	public abstract List<SellerUserCredential> findSellerUserCredentialEntries(int firstResult, int maxResults);


	public abstract void saveSellerUserCredential(SellerUserCredential sellerUserCredential);


	public abstract SellerUserCredential updateSellerUserCredential(SellerUserCredential sellerUserCredential);

}
