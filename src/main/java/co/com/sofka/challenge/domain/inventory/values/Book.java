package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.challenge.domain.inventory.Author;
import co.com.sofka.challenge.domain.inventory.Collection;
import co.com.sofka.domain.generic.Entity;

import java.time.LocalDate;

public class Book extends Entity<BookId> {

    private final Isbn isbn;
    private final Author author;
    private final Collection collection;
    private final State state;
    private final ReturnDate returnDate;

    public Book(BookId entityId, Isbn isbn, Author author, Collection collection, State state, ReturnDate returnDate) {
        super(entityId);
        this.isbn = isbn;
        this.author = author;
        this.collection = collection;
        this.state = state;
        this.returnDate = returnDate;
    }

    public Book(BookId entityId, Isbn isbn, Author author, Collection collection) {
        super(entityId);
        this.isbn = isbn;
        this.author = author;
        this.collection = collection;
        this.state = new State(BookState.NOT_BORROWED);
        this.returnDate = new ReturnDate(LocalDate.now());
    }

    public Isbn isbn(){
        return isbn;
    }

    public Author author(){
        return author;
    }

    public Collection collection(){
        return collection;
    }

    public State state(){
        return state;
    }

    public ReturnDate returnDate(){
        return returnDate;
    }

    //TODO Implement method changeState of Book
    public void changeState(){

    }

    //TODO implement updateDateReturn
    public void updateDateReturn(){

    }

}
