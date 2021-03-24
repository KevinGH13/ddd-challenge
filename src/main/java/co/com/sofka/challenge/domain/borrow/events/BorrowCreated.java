package co.com.sofka.challenge.domain.borrow.events;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;

public class BorrowCreated extends DomainEvent {
    private final BorrowId borrowId;
    private final User user;
    private final Set<BookId> bookId;

    public BorrowCreated(BorrowId borrowId, User user, Set<BookId> bookId) {
        super("ddd.borrow.borrowcreated");
        this.borrowId = borrowId;
        this.user = user;
        this.bookId = bookId;
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
}
