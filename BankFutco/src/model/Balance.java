package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Balance {
    private String id; // ID único
    private LocalDate date;
    private String description;
    private BigDecimal cashIn;
    private BigDecimal cashOut;
    private BigDecimal closingBalance;

    public Balance() {
        this.id = UUID.randomUUID().toString();
    }

    public Balance(LocalDate date, String description, BigDecimal cashIn, BigDecimal cashOut, BigDecimal closingBalance) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.description = description;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.closingBalance = closingBalance;
    }

    public String getId() { return id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getCashIn() { return cashIn; }
    public void setCashIn(BigDecimal cashIn) { this.cashIn = cashIn; }
    public BigDecimal getCashOut() { return cashOut; }
    public void setCashOut(BigDecimal cashOut) { this.cashOut = cashOut; }
    public BigDecimal getClosingBalance() { return closingBalance; }
    public void setClosingBalance(BigDecimal closingBalance) { this.closingBalance = closingBalance; }

    @Override
    public String toString() {
        return "Balance [id=" + id + ", date=" + date + ", description=" + description + 
               ", cashIn=" + cashIn + ", cashOut=" + cashOut + ", closingBalance=" + closingBalance + "]";
    }
}
