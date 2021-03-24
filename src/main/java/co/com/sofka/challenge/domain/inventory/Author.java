package co.com.sofka.challenge.domain.inventory;

import co.com.sofka.challenge.domain.inventory.values.AuthorId;
import co.com.sofka.domain.generic.Entity;

public class Author extends Entity<AuthorId> {

    private final String name;

    public Author(AuthorId entityId, String name) {
        super(entityId);
        this.name = name;
    }

    public String name(){
        return name;
    }

}
