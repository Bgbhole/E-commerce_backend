package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entity.Card;
import com.ecommerce.ecommerce.entity.Upi;
import com.ecommerce.ecommerce.repository.CardRepository;
import com.ecommerce.ecommerce.repository.UpiRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UpiRepository upiRepository;

    // Save Card
    @PostMapping("/saveCard")
    public Card saveCard(@RequestBody Card card){

        System.out.println(card.getCardHolderName());
        System.out.println(card.getCardNumber());
        System.out.println(card.getExpiryDate());
        System.out.println(card.getCvv());

        return cardRepository.save(card);
    }

    // Get Cards
    @GetMapping("/cards/{customerId}")
    public List<Card> getCards(@PathVariable Long customerId){

        return cardRepository.findByCustomerId(customerId);
    }

    // Save UPI
    @PostMapping("/saveUpi")
    public Upi saveUpi(@RequestBody Upi upi){

        return upiRepository.save(upi);
    }

    // Get UPI
    @GetMapping("/upis/{customerId}")
    public List<Upi> getUpis(@PathVariable Long customerId){

        return upiRepository.findByCustomerId(customerId);
    }
    
    @PutMapping("/updateCard/{cardId}")
    public Card updateCard(@PathVariable Long cardId,
                           @RequestBody Card updatedCard){

        Card card = cardRepository.findById(cardId).orElseThrow();

        card.setCardHolderName(updatedCard.getCardHolderName());
        card.setCardNumber(updatedCard.getCardNumber());
        card.setExpiryDate(updatedCard.getExpiryDate());
        card.setCvv(updatedCard.getCvv());

        return cardRepository.save(card);
    }

    @DeleteMapping("/deleteCard/{cardId}")
    public void deleteCard(@PathVariable Long cardId){

        cardRepository.deleteById(cardId);

    }
    
    @PutMapping("/updateUpi/{upiId}")
    public Upi updateUpi(@PathVariable Long upiId,
                         @RequestBody Upi updatedUpi){

        Upi upi = upiRepository.findById(upiId).orElseThrow();

        upi.setUpiNumber(updatedUpi.getUpiNumber());

        return upiRepository.save(upi);
    }

    @DeleteMapping("/deleteUpi/{upiId}")
    public void deleteUpi(@PathVariable Long upiId){

        upiRepository.deleteById(upiId);

    }

}
