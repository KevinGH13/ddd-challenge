package co.com.sofka.challenge.domain.borrow.events;

import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.Ticket;
import co.com.sofka.domain.generic.DomainEvent;

import java.time.LocalDate;

public class BorrowUpdated extends DomainEvent {
    private final BorrowId borrowId;
    private final LocalDate date;
    private final Ticket ticket;

    public BorrowUpdated(BorrowId borrowId, LocalDate date, Ticket ticket) {
        super("ddd.borrow.borrowupdated");
        this.borrowId = borrowId;
        this.date = date;
        this.ticket = ticket;
    }

    public BorrowId getBorrowId() {
        return borrowId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
