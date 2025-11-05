package services;

import java.util.*;
import model.Cards;

public class CardsService implements ICardsService {

    private final List<Cards> cardsList = new ArrayList<>();

   
    public CardsService() {
        initData();
    }

    private void initData() {
        cardsList.add(new Cards("CARD001", "Credit", new java.math.BigDecimal("5000.00"), new java.math.BigDecimal("1500.00"), new java.math.BigDecimal("3500.00")));
        cardsList.add(new Cards("CARD002", "Debit", new java.math.BigDecimal("2000.00"), new java.math.BigDecimal("500.00"), new java.math.BigDecimal("1500.00")));
        cardsList.add(new Cards("CARD003", "Credit", new java.math.BigDecimal("10000.00"), new java.math.BigDecimal("2000.00"), new java.math.BigDecimal("8000.00")));
    }

    @Override
    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        
        deleteById(card.getCardNumber());
        cardsList.add(card);
        System.out.println("✅ Tarjeta guardada: " + card.getCardNumber());
        return card;
    }

    @Override
    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return cardsList.stream()
                .filter(c -> cardNumber.equals(c.getCardNumber()))
                .findFirst();
    }

    @Override
    public List<Cards> findAll() {
        return new ArrayList<>(cardsList);
    }

    @Override
    public boolean deleteById(String cardNumber) {
        if (cardNumber == null) return false;
        boolean removed = cardsList.removeIf(c -> cardNumber.equals(c.getCardNumber()));
        if (removed) {
            System.out.println("✅ Tarjeta eliminada: " + cardNumber);
        } else {
            System.out.println("⚠️ No se encontró tarjeta: " + cardNumber);
        }
        return removed;
    }
}
