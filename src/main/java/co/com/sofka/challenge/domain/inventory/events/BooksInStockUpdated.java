package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.DomainEvent;

public class BooksInStockUpdated extends DomainEvent {

    private final InventoryId inventoryId;
    private final Integer actualStock;
    private final Integer booksOutStock;

    public BooksInStockUpdated(InventoryId inventoryId, Integer actualStock, Integer booksOutStock) {
        super("ddd.inventory.booksinstockupdated");
        this.inventoryId = inventoryId;
        this.actualStock = actualStock;
        this.booksOutStock = booksOutStock;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Integer getActualStock() {
        return actualStock;
    }

    public Integer getBooksOutStock() {
        return booksOutStock;
    }
}
