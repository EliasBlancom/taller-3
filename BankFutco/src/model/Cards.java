package model;

import java.math.BigDecimal;

public class Cards {
	// Atributos
	private String cardNumber;
	private String type; // e.g. "Credit", "Debit"
	private BigDecimal totalLimit;
	private BigDecimal amountUsed;
	private BigDecimal available;

	// Constructor vacío
	public Cards() {
	}

	// Constructor con todos los campos
	public Cards(String cardNumber, String type, BigDecimal totalLimit, BigDecimal amountUsed, BigDecimal available) {
		this.cardNumber = cardNumber;
		this.type = type;
		this.totalLimit = totalLimit;
		this.amountUsed = amountUsed;
		this.available = available;
	}

	// Getters y setters
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}

	public BigDecimal getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(BigDecimal amountUsed) {
		this.amountUsed = amountUsed;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
}
