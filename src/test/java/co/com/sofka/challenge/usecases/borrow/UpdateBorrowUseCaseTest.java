package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.commands.UpdateBorrow;
import co.com.sofka.challenge.domain.borrow.events.BorrowUpdated;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.UserId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

class UpdateBorrowUseCaseTest {

    private final BorrowId borrowId = BorrowId.of("01");
    private final Set<BookId> bookIdSet = Set.of(BookId.of("123-234"));
    private final User user = new User(UserId.of("1018"), "Kev");

    @Test
    void updateBorrow(){
        var command = new UpdateBorrow(borrowId, bookIdSet, user, LocalDate.now());
        var useCase = new UpdateBorrowUseCase();

        var event = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var borrowUpdated = (BorrowUpdated) event.get(1);

        Assertions.assertEquals(command.borrowId(), borrowUpdated.getBorrowId());
    }

}