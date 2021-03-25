package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.challenge.domain.borrow.Borrow;
import co.com.sofka.challenge.domain.borrow.commands.CreateBorrow;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;

public class CreateBorrowUseCase extends UseCase<RequestCommand<CreateBorrow>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateBorrow> createBorrowRequestCommand) {
        var command = createBorrowRequestCommand.getCommand();
        var borrowId = new BorrowId();
        var borrow = new Borrow(borrowId, command.inventoryId(), command.booksId(), command.user());

        emit().onResponse(new ResponseEvents(borrow.getUncommittedChanges()));

    }
}
