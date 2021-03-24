package co.com.sofka.challenge.domain.borrow.values;

import co.com.sofka.domain.generic.Identity;

public class UserId extends Identity {
    private UserId(String uid){
        super(uid);
    }

    public UserId of(String uid){
        return new UserId(uid);
    }
}
