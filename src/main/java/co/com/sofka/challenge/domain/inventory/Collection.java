package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.values.CollectionId;
import co.com.sofka.domain.generic.Entity;

public class Collection extends Entity<CollectionId> {

    private final String name;

    public Collection(CollectionId entityId, String name) {
        super(entityId);
        this.name = name;
    }

    public String name() {
        return name;
    }

}
