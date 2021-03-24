package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;

public class InventoryCreated extends DomainEvent {
    private final InventoryId inventoryId;
    private final List<Book> books;

    public InventoryCreated(InventoryId inventoryId, List<Book> books) {
        super("ddd.inventorycreated");
        this.inventoryId = inventoryId;
        this.books = books;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public List<Book> getBooks() {
        return books;
    }
}
