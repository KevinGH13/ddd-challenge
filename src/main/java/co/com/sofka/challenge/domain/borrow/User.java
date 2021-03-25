package co.com.sofka.challenge.domain.borrow;

import co.com.sofka.challenge.domain.borrow.values.UserId;
import co.com.sofka.domain.generic.Entity;

public class User extends Entity<UserId> {

    private final String name;

    public User(UserId entityId, String name) {
        super(entityId);
        this.name = name;
    }

    public String name(){
        return name;
    }
}
