package co.com.sofka.challenge.domain.inventory.commands;

import co.com.sofka.challenge.domain.inventory.Author;
import co.com.sofka.challenge.domain.inventory.Collection;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.Isbn;
import co.com.sofka.domain.generic.Command;

public class CreateBook implements Command {

    private final BookId bookId;
    private final Isbn isbn;
    private final Author author;
    private final Collection collection;

    public CreateBook(BookId bookId, Isbn isbn, Author author, Collection collection) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.author = author;
        this.collection = collection;
    }

    public BookId bookId() {
        return bookId;
    }

    public Isbn isbn() { return isbn; }

    public Author author() { return author; }

    public Collection collection() {
        return collection;
    }
}
