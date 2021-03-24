package co.com.sofka.challenge.domain.inventory.events;

import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Name;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;

public class InventoryCreated extends DomainEvent {
    private final InventoryId inventoryId;
    private final Name name;

    public InventoryCreated(InventoryId inventoryId, Name name) {
        super("ddd.inventorycreated");
        this.inventoryId = inventoryId;
        this.name = name;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Name getName() {
        return name;
    }
}
