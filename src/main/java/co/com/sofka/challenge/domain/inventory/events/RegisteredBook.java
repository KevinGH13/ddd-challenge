package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.factory.BookFactory;
import co.com.sofka.domain.generic.DomainEvent;

public class RegisteredBook extends DomainEvent {

    private final Book book;

    public RegisteredBook(Book book) {
        super("ddd.inventory.registeredbook");
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
