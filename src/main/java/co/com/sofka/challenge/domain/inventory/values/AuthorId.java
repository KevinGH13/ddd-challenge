package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.Identity;

public class AuthorId extends Identity {
    private AuthorId(String uid){
        super(uid);
    }

    public static AuthorId of(String uid){
        return new AuthorId(uid);
    }
}
