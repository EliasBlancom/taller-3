package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Loans {
    private String id; // ID único
    private LocalDate date;
    private String type;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmt;

    public Loans() {
        this.id = UUID.randomUUID().toString();
    }

    public Loans(LocalDate date, String type, BigDecimal totalLoan, BigDecimal amountPaid, BigDecimal outstandingAmt) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.type = type;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmt = outstandingAmt;
    }

    public String getId() { return id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getTotalLoan() { return totalLoan; }
    public void setTotalLoan(BigDecimal totalLoan) { this.totalLoan = totalLoan; }
    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }
    public BigDecimal getOutstandingAmt() { return outstandingAmt; }
    public void setOutstandingAmt(BigDecimal outstandingAmt) { this.outstandingAmt = outstandingAmt; }

    @Override
    public String toString() {
        return "Loans [id=" + id + ", date=" + date + ", type=" + type + ", totalLoan=" + totalLoan + 
               ", amountPaid=" + amountPaid + ", outstandingAmt=" + outstandingAmt + "]";
    }
}
