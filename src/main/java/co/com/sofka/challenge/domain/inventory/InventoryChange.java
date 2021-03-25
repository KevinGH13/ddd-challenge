package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.events.BooksInStockUpdated;
import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.challenge.domain.inventory.values.Stock;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashMap;

public class InventoryChange extends EventChange {
    public InventoryChange(Inventory inventory){
        inventory.booksInStock = new Stock(0);
        apply((InventoryCreated event) -> {
            inventory.books = new HashMap<>();
            inventory.booksInStock = new Stock(0);
        } );

        apply((RegisteredBook event) -> {
            inventory.books = new HashMap<>();
            inventory.books.put(event.getBook().identity(), event.getBook());
            inventory.increaseStock(1);
        });

        apply((BooksInStockUpdated event) -> {
            inventory.booksInStock = new Stock(1);
            inventory.increaseStock(event.getBooksOutStock());
        });

    }
}
