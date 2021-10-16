package com.serendevity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Convert(converter = CipherConverter.class)
    private String cardNumber; // nnnn-nnnn-nnnn-nnnn
    private String lastFourDigits; // nnnn
    private String cardExpiry; // mmdd

    public Card() {

    }

    public Card(String cardNumber, String cardExpiry, String lastFourDigits) {
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.lastFourDigits = lastFourDigits;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }
}
