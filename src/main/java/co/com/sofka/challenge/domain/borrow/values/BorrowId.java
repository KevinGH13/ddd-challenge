package co.com.sofka.challenge.domain.borrow.values;

import co.com.sofka.domain.generic.Identity;

public class BorrowId extends Identity {
    private BorrowId(String uid){
        super(uid);
    }

    public BorrowId of(String uid){
        return new BorrowId(uid);
    }
}
