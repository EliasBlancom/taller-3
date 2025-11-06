package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
	// Atributos
	private LocalDate date;
	private String description;
	private BigDecimal cashIn;
	private BigDecimal cashOut;
	private BigDecimal closingBalance;

	// Constructor vacío
	public Balance() {
	}

	// Constructor con todos los campos
	public Balance(LocalDate date, String description, BigDecimal cashIn, BigDecimal cashOut, BigDecimal closingBalance) {
		this.date = date;
		this.description = description;
		this.cashIn = cashIn;
		this.cashOut = cashOut;
		this.closingBalance = closingBalance;
	}

	// Getters y setters
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCashIn() {
		return cashIn;
	}

	public void setCashIn(BigDecimal cashIn) {
		this.cashIn = cashIn;
	}

	public BigDecimal getCashOut() {
		return cashOut;
	}

	public void setCashOut(BigDecimal cashOut) {
		this.cashOut = cashOut;
	}

	public BigDecimal getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}
}
