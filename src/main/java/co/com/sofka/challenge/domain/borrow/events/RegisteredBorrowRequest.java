package co.com.sofka.challenge.domain.borrow.events;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.Ticket;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;

public class RegisteredBorrowRequest extends DomainEvent {
    private final BorrowId borrowId;
    private final User user;
    private final Set<BookId> bookId;
    private final Ticket ticket;

    public RegisteredBorrowRequest(BorrowId borrowId, User user, Set<BookId> bookId, Ticket ticket) {
        super("ddd.borrow.registeredborrowrequest");
        this.borrowId = borrowId;
        this.user = user;
        this.bookId = bookId;
        this.ticket = ticket;
    }

    public BorrowId getBorrowId() {
        return borrowId;
    }

    public User getUser() {
        return user;
    }

    public Set<BookId> getBookId() {
        return bookId;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
