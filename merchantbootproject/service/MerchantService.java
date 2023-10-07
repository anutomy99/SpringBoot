package org.jsp.merchantbootproject.service;

import java.util.Optional;

import org.jsp.merchantbootproject.dao.MerchantDao;
import org.jsp.merchantbootproject.dto.Merchant;
import org.jsp.merchantbootproject.dto.ResponseStructure;
import org.jsp.merchantbootproject.exception.IdNotFoundException;
import org.jsp.merchantbootproject.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
public class MerchantService {
		@Autowired
		private MerchantDao dao;

		public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			merchant = dao.saveMerchant(merchant);
			structure.setData(merchant);
			structure.setMessage("Merchant Registered Successfully " + merchant.getId());
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.CREATED);
		}
		
		public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			merchant = dao.updateMerchant(merchant);
			structure.setData(merchant);
			structure.setMessage("Merchant updated Successfully " + merchant.getId());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);
		}

		public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			Optional<Merchant> recMerchant = dao.findById(id);
			if (recMerchant.isPresent()) {
				structure.setData(recMerchant.get());
			    structure.setMessage("Merchant Found");
			    structure.setStatusCode(HttpStatus.OK.value());
			    return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
			}   
//			structure.setData(null);
//		    structure.setMessage("Invalid Merchant Id");
//		    structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//		    return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
			
			throw new IdNotFoundException();
		}

		
		public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			boolean deleted = dao.deleteById(id);
			if (deleted) {
				structure.setData("Merchant deleted");
			    structure.setMessage("Merchant Found");
			    structure.setStatusCode(HttpStatus.OK.value());
			    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
			}
			structure.setData("Merchant not deleted");
		    structure.setMessage("Invalid Merchant id");
		    structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);

		    
		}

		public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone,String password) {
			Optional<Merchant> recMerchant = dao.verifyMerchant(phone, password);
			ResponseStructure<Merchant> structure = new ResponseStructure<>();

			if (recMerchant.isPresent()) {
				structure.setData(recMerchant.get());
			    structure.setMessage("Merchant Verified");
			    structure.setStatusCode(HttpStatus.OK.value());
			    return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);

			}
//			structure.setData(null);
//		    structure.setMessage("Invalid phone number or password");
//		    structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//		    return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
			
			throw new InvalidCredentialsException();
			
		}
}
