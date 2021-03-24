package co.com.sofka.challenge.domain.borrow;

import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Borrow extends AggregateEvent<BorrowId> {
    protected Map<BookId, Book> books;
    protected User user;

    public Borrow(BorrowId entityId, Map<BookId, Book> books, User user) {
        super(entityId);
        this.books = books;
        this.user = user;
        //appendChange()
    }

    public Borrow(BorrowId entityId) {
        super(entityId);
        //subscribe(new borrowChange(this))
    }

    public Borrow from(BorrowId borrowId, List<DomainEvent> eventList){
        var borrow = new Borrow(borrowId);
        eventList.forEach(borrow::applyEvent);
        return borrow;
    }

    //TODO to implement method for borrowRequest or borrowApplication
    public void borrowRequest(){}

    public Map<BookId, Book> books(){
        return books;
    }

    public User user(){
        return user;
    }
}
