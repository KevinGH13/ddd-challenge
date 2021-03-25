package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.DomainEvent;

public class RegisteredBook extends DomainEvent {

    private final Book book;
    private final InventoryId inventoryId;

    public RegisteredBook(Book book, InventoryId inventoryId) {
        super("ddd.inventory.registeredbook");
        this.book = book;
        this.inventoryId = inventoryId;
    }

    public Book getBook() {
        return book;
    }

    public InventoryId getInventoryId(){
        return inventoryId;
    }
}
