package co.com.sofka.challenge.usecases.inventory;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.challenge.domain.inventory.Author;
import co.com.sofka.challenge.domain.inventory.Collection;
import co.com.sofka.challenge.domain.inventory.commands.CreateBook;
import co.com.sofka.challenge.domain.inventory.events.BookCreated;
import co.com.sofka.challenge.domain.inventory.values.AuthorId;
import co.com.sofka.challenge.domain.inventory.values.BookId;
import co.com.sofka.challenge.domain.inventory.values.CollectionId;
import co.com.sofka.challenge.domain.inventory.values.Isbn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateBookUseCaseTest {

    @Test
    void createBook(){

        var bookId = new BookId();
        var isbn = new Isbn("323-43");
        var author = new Author(AuthorId.of("1018"), "Kev");
        var collection = new Collection(CollectionId.of("001"), "social sciences");

        var command = new CreateBook(bookId, isbn, author, collection);
        var useCase = new CreateBookUseCase();

        var event = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var bookCreated = (BookCreated) event.get(0);

        Assertions.assertEquals(bookId, bookCreated.getBook().identity());
        Assertions.assertEquals(isbn, bookCreated.getBook().isbn());
        Assertions.assertEquals(author, bookCreated.getBook().author());
        Assertions.assertEquals(collection, bookCreated.getBook().collection());

    }

}