package co.com.sofka.challenge.domain.borrow.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Ticket implements ValueObject<String> {
    private final String value;

    public Ticket(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return null;
    }
}
