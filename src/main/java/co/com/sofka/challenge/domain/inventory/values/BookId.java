package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.Identity;

public class BookId extends Identity {
    private BookId(String uid){
        super(uid);
    }

    public BookId(){

    }

    public static BookId of(String uid){
        return new BookId(uid);
    }
}
