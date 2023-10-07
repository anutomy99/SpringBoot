package org.jsp.merchantbootproject.dao;

import java.util.Optional;

import org.jsp.merchantbootproject.dto.Merchant;
import org.jsp.merchantbootproject.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;

	public Merchant saveMerchant(Merchant merchant) {
		return repository.save(merchant);
	}

	public Merchant updateMerchant(Merchant merchant) {
		return repository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteById(int id) {
		Optional<Merchant> recMerchant = findById(id);
		if (recMerchant.isPresent()) {
//			repository.delete(recMerchant.get());
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	public  Optional<Merchant> verifyMerchant(long phone, String password){
		return repository.verifyMerchant(phone,password);
		
		
	}
 }
