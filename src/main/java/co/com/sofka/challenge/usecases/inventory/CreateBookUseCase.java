package co.com.sofka.challenge.usecases.inventory;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.challenge.domain.inventory.Inventory;
import co.com.sofka.challenge.domain.inventory.commands.CreateBook;
import co.com.sofka.challenge.domain.inventory.factory.BookFactory;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;

public class CreateBookUseCase extends UseCase<RequestCommand<CreateBook>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateBook> createBookRequestCommand) {
        var command = createBookRequestCommand.getCommand();
        var inventoryId = new InventoryId();
        var inventory = new Inventory(inventoryId);
        var factory = BookFactory.builder();

        factory.newBook(command.bookId(), command.isbn(), command.author(), command.collection(), inventoryId);

        inventory.createBook(factory);

        emit().onResponse(new ResponseEvents(inventory.getUncommittedChanges()));

    }
}
