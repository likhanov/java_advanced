package com.epam.jmp.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Bank;

import java.util.concurrent.ThreadLocalRandom;

public class BankImpl implements Bank {

    private final double INITIAL_CREDIT_LIMIT = 0;
    private final double INITIAL_BALANCE = 0;

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        if (user == null) {
            throw new IllegalArgumentException("User must be provided");
        }

        if (cardType == null) {
            throw new IllegalArgumentException("Card type must be provided");
        }

        String number = generateCardNumber();

        switch (cardType) {
            case CREDIT:
                return new CreditBankCard(number, user, INITIAL_CREDIT_LIMIT);
            case DEBIT:
                return new DebitBankCard(number, user, INITIAL_BALANCE);
            default:
                throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            cardNumber.append(ThreadLocalRandom.current().nextInt(10));
        }
        return cardNumber.toString();
    }
}
