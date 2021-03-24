package co.com.sofka.challenge.usecases.inventory;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.challenge.domain.inventory.commands.CreateInventory;
import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.values.InventoryId;
import co.com.sofka.challenge.domain.inventory.values.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateInventoryUseCaseTest {

    @Test
    void createInventory(){
        var inventoryId = InventoryId.of("001");
        var name = new Name("inventory");

        var command = new CreateInventory(inventoryId, name);
        var useCase = new CreateInventoryUseCase();

        var event = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var inventoryCreated = (InventoryCreated) event.get(0);

        Assertions.assertEquals("001", inventoryCreated.getInventoryId().value());
        Assertions.assertEquals("inventory", inventoryCreated.getName().value());
    }

}