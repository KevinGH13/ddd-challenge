package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.challenge.domain.borrow.Borrow;
import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;

public class BorrowBookUseCase extends UseCase<TriggeredEvent<BorrowCreated>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<BorrowCreated> borrowCreatedTriggeredEvent) {
        var event = borrowCreatedTriggeredEvent.getDomainEvent();
        var borrow = Borrow.from(event.getBorrowId(), retrieveEvents());

        borrow.registerBorrowRequest(event.getBorrowId(), event.getBookId(), event.getUser());

        emit().onResponse(new ResponseEvents(borrow.getUncommittedChanges()));
    }
}
