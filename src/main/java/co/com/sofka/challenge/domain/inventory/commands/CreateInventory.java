package co.com.sofka.challenge.domain.inventory.commands;

import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Name;
import co.com.sofka.domain.generic.Command;

public class CreateInventory implements Command {

    private final InventoryId inventoryId;
    private final Name name;

    public CreateInventory(InventoryId inventoryId, Name name) {
        this.inventoryId = inventoryId;
        this.name = name;
    }

    public InventoryId inventoryId(){return inventoryId;}

    public Name name(){return name;}

}
