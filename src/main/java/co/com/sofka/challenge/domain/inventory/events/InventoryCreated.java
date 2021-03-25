package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Name;
import co.com.sofka.challenge.domain.inventory.values.Stock;
import co.com.sofka.domain.generic.DomainEvent;

public class InventoryCreated extends DomainEvent {
    private final InventoryId inventoryId;
    private final Name name;
    private final Stock booksInStock;

    public InventoryCreated(InventoryId inventoryId, Name name, Stock booksInStock) {
        super("ddd.inventory.inventorycreated");
        this.inventoryId = inventoryId;
        this.name = name;
        this.booksInStock = booksInStock;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Name getName() {
        return name;
    }

    public Stock getBooksInStock() {
        return booksInStock;
    }
}
