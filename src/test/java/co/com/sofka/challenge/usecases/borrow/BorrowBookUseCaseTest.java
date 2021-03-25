package co.com.sofka.challenge.usecases.borrow;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.challenge.domain.borrow.User;
import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.borrow.events.RegisteredBorrowRequest;
import co.com.sofka.challenge.domain.borrow.values.BorrowId;
import co.com.sofka.challenge.domain.borrow.values.Name;
import co.com.sofka.challenge.domain.borrow.values.UserId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowBookUseCaseTest {

    private final BorrowId borrowId = BorrowId.of("01");
    private final UserId userId = UserId.of("1018");
    private final Name name = new Name("Kev");
    private final Set<BookId> bookIdSet = Set.of(BookId.of("kdj-123"));

    @Mock
    private DomainEventRepository repository;

    @Test
    void borrowBook(){
        var event = createTriggeredEventWith();
        var useCase = new BorrowBookUseCase();

        when(repository.getEventsBy(borrowId.value())).thenReturn(eventStored());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(borrowId.value())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var registeredBorrowRequest = (RegisteredBorrowRequest) events.get(0);

        Assertions.assertEquals("01", registeredBorrowRequest.getBorrowId().value());

    }


    private BorrowCreated createTriggeredEventWith(){
        var event = new BorrowCreated(borrowId, new User(userId, name.value()), bookIdSet);
        event.setAggregateName(borrowId.value());
        return event;
    }

    private List<DomainEvent> eventStored(){
        return List.of(new BorrowCreated(borrowId, new User(userId, name.value()), bookIdSet));
    }

}