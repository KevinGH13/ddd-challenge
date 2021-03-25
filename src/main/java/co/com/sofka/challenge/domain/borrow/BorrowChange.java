package co.com.sofka.challenge.domain.borrow;

import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.borrow.events.BorrowUpdated;
import co.com.sofka.challenge.domain.borrow.events.RegisteredBorrowRequest;
import co.com.sofka.domain.generic.EventChange;

public class BorrowChange extends EventChange {
    public BorrowChange(Borrow borrow) {
        apply((BorrowCreated event) -> {
            borrow.booksId = event.getBookId();
            borrow.user = event.getUser();
        });

        apply((RegisteredBorrowRequest event) -> borrow.ticket = event.getTicket());

        apply((BorrowUpdated event) -> {
            if(event.getBorrowId().equals(borrow.identity())){
                borrow.ticket = event.getTicket();
                borrow.date = event.getDate();
            }
        });
    }
}
