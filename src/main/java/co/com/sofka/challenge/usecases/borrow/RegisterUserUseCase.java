package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.challenge.domain.borrow.Borrow;
import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;

public class RegisterUserUseCase extends UseCase<TriggeredEvent<BorrowCreated>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<BorrowCreated> borrowCreatedTriggeredEvent) {
        var event = borrowCreatedTriggeredEvent.getDomainEvent();
        var user = new User(event.getUser().identity(), event.getUser().name());

        var borrow = Borrow.from(event.getBorrowId(), retrieveEvents());

        borrow.registerUser(user);

        emit().onResponse(new ResponseEvents(borrow.getUncommittedChanges()));
    }
}
