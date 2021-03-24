package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.events.BookCreated;
import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.challenge.domain.inventory.factory.BookFactory;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory extends AggregateEvent<InventoryId> {

    protected Map<BookId, Book> books;

    public Inventory(InventoryId entityId, Map<BookId, Book> books) {
        super(entityId);
        this.books = books;
        appendChange(new InventoryCreated(entityId, new ArrayList<>(books.values()))).apply();
    }

    public Inventory(InventoryId entityId){
        super(entityId);
        subscribe(new InventoryChange(this));
    }

    public Inventory from(InventoryId inventoryId, List<DomainEvent> eventList){
        var inventory = new Inventory(inventoryId);
        eventList.forEach(inventory::applyEvent);
        return inventory;
    }

    //TODO to implement method for remove a book of inventory
    public void removeBook(){}

    public void createBook(BookFactory bookFactory){
        bookFactory.books().forEach(book -> appendChange(new BookCreated(book)).apply());
        registerBook(bookFactory);
    }

    public void registerBook(BookFactory bookFactory){
        bookFactory.books().forEach(book -> appendChange(new RegisteredBook(book)).apply());
    }

    public Map<BookId, Book> books(){
        return books;
    }
}
