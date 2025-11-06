package services;

import java.util.*;
import model.Cards;

public class CardsService implements ICardsService {
    private final Map<String, Cards> storage = new HashMap<>();

    @Override
    public Cards save(Cards card) {
        if (card == null) return null;
        String id = UUID.randomUUID().toString();
        storage.put(id, card);
        return card;
    }

    @Override
    public Optional<Cards> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Cards> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}
