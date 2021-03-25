package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class ReturnDate implements ValueObject<LocalDate> {
    private final LocalDate value;

    public ReturnDate(LocalDate value){
        this.value = Objects.requireNonNull(value);
    }

    public ReturnDate updateReturnDate(LocalDate value){
        return new ReturnDate(value);
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
