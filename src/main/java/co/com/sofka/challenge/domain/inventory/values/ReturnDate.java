package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class ReturnDate implements ValueObject<Date> {
    private final Date value;

    public ReturnDate(Date value){
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Date value() {
        return value;
    }
}
