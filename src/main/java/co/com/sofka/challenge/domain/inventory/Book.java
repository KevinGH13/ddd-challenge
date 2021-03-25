package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.values.*;
import co.com.sofka.domain.generic.Entity;

import java.time.LocalDate;

public class Book extends Entity<BookId> {

    private final Isbn isbn;
    private final Author author;
    private final Collection collection;
    private final State state;
    private final ReturnDate returnDate;
    private final InventoryId inventoryId;

    public Book(BookId entityId, Isbn isbn, Author author, Collection collection, State state, ReturnDate returnDate, InventoryId inventoryId) {
        super(entityId);
        this.isbn = isbn;
        this.author = author;
        this.collection = collection;
        this.state = state;
        this.returnDate = returnDate;
        this.inventoryId = inventoryId;
    }

    public Book(BookId entityId, Isbn isbn, Author author, Collection collection, InventoryId inventoryId) {
        super(entityId);
        this.isbn = isbn;
        this.author = author;
        this.collection = collection;
        this.inventoryId = inventoryId;
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

    public InventoryId inventoryId(){ return inventoryId; }

    public void changeState(BookState state){
        this.state.changeState(state);
    }

    public void updateDateReturn(LocalDate date){
        this.returnDate.updateReturnDate(date);
    }


}
