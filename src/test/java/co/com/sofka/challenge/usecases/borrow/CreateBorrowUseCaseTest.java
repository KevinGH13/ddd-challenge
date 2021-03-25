package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.commands.CreateBorrow;
import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.UserId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class CreateBorrowUseCaseTest {

    private final InventoryId inventoryId = InventoryId.of("1");
    private final BorrowId borrowId = BorrowId.of("0001");
    private final User user = new User(UserId.of("1018"), "Kev");
    private final Set<BookId> booksId = Set.of(BookId.of("123-asd"));

    @Test
    void createBorrow(){
        var command = new CreateBorrow(inventoryId, booksId, user);

        var useCase = new CreateBorrowUseCase();

        var event = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var borrowCreated = (BorrowCreated) event.get(0);

        Assertions.assertEquals("[123-asd]", borrowCreated.getBookId().toString());
        Assertions.assertEquals("1018", borrowCreated.getUser().identity().toString());
    }

}