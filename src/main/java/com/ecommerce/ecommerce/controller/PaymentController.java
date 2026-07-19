package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.ecommerce.repository.CardRepository;
import com.ecommerce.ecommerce.repository.UpiRepository;

import com.ecommerce.ecommerce.entity.Card;
import com.ecommerce.ecommerce.entity.Upi;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PaymentController {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UpiRepository upiRepository;
    // ================= SAVE CARD =================

	@PostMapping("/saveCard")
	public Card saveCard(@RequestBody Card card) {

	    return cardRepository.save(card);

	}

    // ================= GET ALL CARDS =================

	@GetMapping("/cards/{userId}")
	public List<Card> getCards(
	        @PathVariable Long userId) {

	    return cardRepository.findByCustomerId(userId);

	}

    // ================= DELETE CARD =================

	@DeleteMapping("/cards/{cardId}")
	public String deleteCard(
	        @PathVariable Long cardId) {

	    cardRepository.deleteById(cardId);

	    return "Card Deleted Successfully";

	}

    // ================= SAVE UPI =================

	@PostMapping("/saveUpi")
	public Upi saveUpi(@RequestBody Upi upi) {

	    return upiRepository.save(upi);

	}

    // ================= GET ALL UPI =================

	@GetMapping("/upi/{userId}")
	public List<Upi> getUpi(
	        @PathVariable Long userId) {

	    return upiRepository.findByCustomerId(userId);

	}

    // ================= DELETE UPI =================

	@DeleteMapping("/upi/{upiId}")
	public String deleteUpi(
	        @PathVariable Long upiId) {

	    upiRepository.deleteById(upiId);

	    return "UPI Deleted Successfully";

	}
	
	@PutMapping("/upi/update/{upiId}")
	public Upi updateUpi(@PathVariable Long upiId,
	                     @RequestBody Upi upi) {

	    Upi existing = upiRepository.findById(upiId).orElseThrow();

	    existing.setUpiNumber(upi.getUpiNumber());

	    return upiRepository.save(existing);
	}

	
	
}