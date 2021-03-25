package co.com.sofka.challenge.domain.borrow.commands;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.Command;

import java.time.LocalDate;
import java.util.Set;

public class UpdateBorrow implements Command {
    private final BorrowId borrowId;
    private final Set<BookId> bookId;
    private final User user;
    private final LocalDate date;

    public UpdateBorrow(BorrowId borrowId, Set<BookId> bookId, User user, LocalDate date) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.user = user;
        this.date = date;
    }

    public BorrowId borrowId(){return borrowId;}

    public Set<BookId> bookId() {
        return bookId;
    }

    public User user(){
        return user;
    }

    public LocalDate date(){
        return date;
    }
}
