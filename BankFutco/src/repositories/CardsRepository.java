package repositories;

import java.util.*;
import model.Cards;

public class CardsRepository {
    private final Map<String, Cards> storage = new HashMap<>();

    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Tarjeta o cardNumber no puede ser null");
        }
        storage.put(card.getCardNumber(), card);
        return card;
    }

    public Optional<Cards> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    public List<Cards> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}
