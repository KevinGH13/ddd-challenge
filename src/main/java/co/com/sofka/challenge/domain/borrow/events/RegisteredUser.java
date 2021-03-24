package co.com.sofka.challenge.domain.borrow.events;

import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.domain.generic.DomainEvent;

public class RegisteredUser extends DomainEvent {
    private final User user;

    public RegisteredUser(User user) {
        super("ddd.borrow.registereduser");
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
