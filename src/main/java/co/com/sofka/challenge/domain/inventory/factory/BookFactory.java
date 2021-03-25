package co.com.sofka.challenge.domain.inventory.factory;

import co.com.sofka.challenge.domain.inventory.Author;
import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.Collection;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Isbn;

import java.util.HashSet;
import java.util.Set;

public class BookFactory {
    private final Set<Book> books;

    private BookFactory(){
        books = new HashSet<>();
    }

    public static BookFactory builder(){
        return new BookFactory();
    }

    public BookFactory newBook(BookId bookId, Isbn isbn, Author author, Collection collection, InventoryId inventoryId){
        books.add(new Book(bookId, isbn, author, collection, inventoryId));
        return this;
    }

    public Set<Book> books() {
        return books;
    }
}
