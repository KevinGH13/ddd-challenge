package co.com.sofka.challenge.domain.borrow.commands;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.domain.generic.Command;

import java.util.Set;

public class CreateBorrow implements Command {

    private final InventoryId inventoryId;
    private final Set<BookId> booksId;
    private final User user;

    public CreateBorrow(InventoryId inventoryId, Set<BookId> booksId, User user) {
        this.inventoryId = inventoryId;
        this.booksId = booksId;
        this.user = user;
    }

    public InventoryId inventoryId(){ return inventoryId;}

    public Set<BookId> booksId(){
        return booksId;
    }

    public User user(){
        return user;
    }
}
