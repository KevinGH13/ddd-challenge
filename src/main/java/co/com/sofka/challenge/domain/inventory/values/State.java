package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class State implements ValueObject<BookState> {

    private final BookState value;

    public State(BookState value) {
        this.value = Objects.requireNonNull(value);
    }

    public State changeState(BookState bookState){
        return new State(bookState);
    }

    @Override
    public BookState value() {
        return value;
    }
}
