package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.challenge.domain.borrow.Borrow;
import co.com.sofka.challenge.domain.borrow.commands.UpdateBorrow;

public class UpdateBorrowUseCase extends UseCase<RequestCommand<UpdateBorrow>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateBorrow> updateBorrowRequestCommand) {
        var command = updateBorrowRequestCommand.getCommand();
        var borrow = new Borrow(command.borrowId(), command.bookId(), command.user());

        borrow.updateBorrow(command.borrowId(), command.date());

        emit().onResponse(new ResponseEvents(borrow.getUncommittedChanges()));
    }
}
