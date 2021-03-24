package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.Identity;

public class InventoryId extends Identity {
    private InventoryId(String uid) {
        super(uid);
    }

    public InventoryId(){}

    public static InventoryId of(String uid) {
        return new InventoryId(uid);
    }
}
