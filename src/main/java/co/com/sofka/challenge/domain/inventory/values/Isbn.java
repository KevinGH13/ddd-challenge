package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Isbn implements ValueObject<String> {
    private final String value;

    public Isbn(String value){
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
