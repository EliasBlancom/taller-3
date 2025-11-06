package services;

import java.util.List;
import java.util.Optional;
import model.Cards;

public interface ICardsService {
	Cards save(Cards card);
	Optional<Cards> findById(String cardNumber);
	List<Cards> findAll();
	boolean deleteById(String cardNumber);
}
