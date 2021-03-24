package co.com.sofka.challenge.usecases.inventory;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.challenge.domain.inventory.Inventory;
import co.com.sofka.challenge.domain.inventory.commands.CreateInventory;

public class CreateInventoryUseCase extends UseCase<RequestCommand<CreateInventory>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateInventory> createInventoryRequestCommand) {
        var command = createInventoryRequestCommand.getCommand();

        var inventory = new Inventory(command.inventoryId(), command.name());

        emit().onResponse(new ResponseEvents(inventory.getUncommittedChanges()));

    }
}
