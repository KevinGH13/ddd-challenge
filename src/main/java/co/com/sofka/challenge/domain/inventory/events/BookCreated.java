package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.domain.generic.DomainEvent;

public class BookCreated extends DomainEvent {
    private final Book book;

    public BookCreated(Book book) {
        super("ddd.inventory.bookcreated");
        this.book = book;
    }

    public Book getBook(){return book; }

}
