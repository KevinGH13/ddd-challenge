package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.challenge.domain.borrow.Borrow;
import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.inventory.Inventory;

public class BorrowBookUseCase extends UseCase<TriggeredEvent<BorrowCreated>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<BorrowCreated> borrowCreatedTriggeredEvent) {
        var event = borrowCreatedTriggeredEvent.getDomainEvent();
        var borrow = Borrow.from(event.getBorrowId(), retrieveEvents());
        var inventory = Inventory.from(event.getInventoryId(), retrieveEvents());


        borrow.registerBorrowRequest(event.getBorrowId(), event.getBookId(), event.getUser());
        inventory.updateBooksInStock(borrow.inventoryId(), inventory.booksInStock().value(), borrow.booksId().size());

        emit().onResponse(new ResponseEvents(borrow.getUncommittedChanges()));
    }
}
