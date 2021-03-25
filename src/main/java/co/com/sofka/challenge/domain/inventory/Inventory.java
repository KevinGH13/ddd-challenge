package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.events.BookCreated;
import co.com.sofka.challenge.domain.inventory.events.BooksInStockUpdated;
import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.challenge.domain.inventory.factory.BookFactory;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Name;
import co.com.sofka.challenge.domain.inventory.values.Stock;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Inventory extends AggregateEvent<InventoryId> {

    protected Name name;
    protected Map<BookId, Book> books;
    protected Stock booksInStock;

    public Inventory(InventoryId entityId, Name name) {
        super(entityId);
        this.name = name;
        this.booksInStock = new Stock(0);
        appendChange(new InventoryCreated(entityId, name, booksInStock)).apply();
    }

    public Inventory(InventoryId entityId){
        super(entityId);
        this.booksInStock = new Stock(0);
        subscribe(new InventoryChange(this));
    }

    public static Inventory from(InventoryId inventoryId, List<DomainEvent> eventList){
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
        bookFactory.books().forEach(book -> appendChange(new RegisteredBook(book, book.inventoryId())).apply());
    }

    public void updateBooksInStock(InventoryId inventoryId, Integer actualStock, Integer booksOutStock){
        appendChange(new BooksInStockUpdated(inventoryId, actualStock, booksOutStock)).apply();

    }

    protected void increaseStock(Integer value){
        this.booksInStock = this.booksInStock.increase(value);
    }

    protected void decreaseStock(Integer value){
        this.booksInStock = this.booksInStock.decrease(value);
    }

    public Name name(){return name;}

    public Map<BookId, Book> books(){
        return books;
    }

    public Stock booksInStock(){
        return booksInStock;
    }

}
