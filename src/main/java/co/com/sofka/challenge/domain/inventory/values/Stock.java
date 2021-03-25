package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Stock implements ValueObject<Integer> {
    private final Integer value;

    public Stock(Integer value) {
        this.value = Objects.requireNonNull(value);
        if (value < 0) {
            throw new IllegalArgumentException("Stock value can't be negative.");
        }
    }

    public Stock increase(Integer value){
        return new Stock(this.value + value);
    }

    public Stock decrease(Integer value){
        return new Stock(this.value - value);
    }

    @Override
    public Integer value() {
        return null;
    }

}
