package co.com.sofka.challenge.domain.borrow.commands;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.Command;

import java.util.Map;
import java.util.Set;

public class CreateBorrow implements Command {

    private final Set<BookId> booksId;
    private final User user;

    public CreateBorrow(Set<BookId> booksId, User user) {
        this.booksId = booksId;
        this.user = user;
    }

    public Set<BookId> booksId(){
        return booksId;
    }

    public User user(){
        return user;
    }
}
