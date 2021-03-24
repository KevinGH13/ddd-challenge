package co.com.sofka.challenge.domain.inventory.values;

import co.com.sofka.domain.generic.Identity;

public class CollectionId extends Identity {
    private CollectionId(String uid){
        super(uid);
    }

    public static CollectionId of(String uid){
        return new CollectionId(uid);
    }
}
