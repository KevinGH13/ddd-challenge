package co.com.sofka.challenge.domain.borrow;

import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.borrow.events.RegisteredBorrowRequest;
import co.com.sofka.challenge.domain.borrow.events.RegisteredUser;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.Ticket;
import co.com.sofka.challenge.domain.inventory.Book;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Borrow extends AggregateEvent<BorrowId> {
    protected Set<BookId> booksId;
    protected User user;
    protected Ticket ticket;

    public Borrow(BorrowId entityId, Set<BookId> booksId, User user) {
        super(entityId);
        this.booksId = booksId;
        this.user = user;
        appendChange(new BorrowCreated(entityId, user, booksId)).apply();
    }

    public Borrow(BorrowId entityId) {
        super(entityId);
        subscribe(new BorrowChange(this));
    }

    public static Borrow from(BorrowId borrowId, List<DomainEvent> eventList){
        var borrow = new Borrow(borrowId);
        eventList.forEach(borrow::applyEvent);
        return borrow;
    }

    public void registerUser(User user){
        appendChange(new RegisteredUser(user)).apply();
    }

    public void registerBorrowRequest(BorrowId borrowId, Set<BookId> booksId, User user){
        var ticket = new Ticket(generateTicket());
        appendChange(new RegisteredBorrowRequest(borrowId,  user, booksId, ticket)).apply();
    }

    private String generateTicket(){
        return LocalDate.now() + Double.toString(((Math.random()*100) + 1));
    }

    public Set<BookId> booksId(){
        return booksId;
    }

    public User user(){
        return user;
    }
}
