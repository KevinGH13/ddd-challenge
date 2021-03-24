package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Inventory extends AggregateEvent<InventoryId> {

    protected Map<BookId, Book> books;

    public Inventory(InventoryId entityId, Map<BookId, Book> books) {
        super(entityId);
        this.books = books;
        //appendChange
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

    //TODO to implement method for register a book
    public void registerBook(){}

    //TODO to implement method for remove a book of inventory
    public void removeBook(){}

    public Map<BookId, Book> books(){
        return books;
    }
}
