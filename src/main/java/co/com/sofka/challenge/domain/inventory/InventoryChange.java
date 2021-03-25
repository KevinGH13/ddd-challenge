package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashMap;

public class InventoryChange extends EventChange {
    public InventoryChange(Inventory inventory){

        apply((InventoryCreated event) -> {
            inventory.books = new HashMap<>();
            inventory.booksInStock = 0;
            inventory.booksOnload = 0;
        } );

        apply((RegisteredBook event) -> {
            inventory.books = new HashMap<>();
            inventory.books.put(event.getBook().identity(), event.getBook());
        });

    }
}
