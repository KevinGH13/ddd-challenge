package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashMap;

public class InventoryChange extends EventChange {
    public InventoryChange(Inventory inventory){

        apply((InventoryCreated event) -> inventory.books = new HashMap<>());

        apply((RegisteredBook event) -> {
            var bookId = new BookId();
            inventory.books = new HashMap<>();
            inventory.books.put(bookId, event.getBook());
        });

    }
}