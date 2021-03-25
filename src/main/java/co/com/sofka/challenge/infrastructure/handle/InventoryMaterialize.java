package co.com.sofka.challenge.infrastructure.handle;

import co.com.sofka.challenge.domain.inventory.events.BookCreated;
import co.com.sofka.challenge.domain.inventory.events.InventoryCreated;
import co.com.sofka.challenge.domain.inventory.events.RegisteredBook;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class InventoryMaterialize {

    private static final String COLLECTION_NAME = "Inventory";
    private static final Logger logger = Logger.getLogger(InventoryMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleInventoryCreated(InventoryCreated inventoryCreated){
        logger.info("*** handle event inventoryCreated");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", inventoryCreated.getInventoryId().value());
        data.put("isInventoryCreated", true);
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleBookCreated(BookCreated bookCreated){
        logger.info("*** handle event bookCreated");
        Update update = new Update();
        var bookId = bookCreated.getBook().identity().value();
        update.set("Book." + bookId + ".isbn", bookCreated.getBook().isbn().value());
        update.set("Book." + bookId + ".author", bookCreated.getBook().author().name());
        update.set("Book." + bookId + ".collection", bookCreated.getBook().collection().name());

        mongoTemplate.updateFirst(getFilterByAggregateId(bookCreated), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleRegisteredBook(RegisteredBook registeredBook){
        logger.info("*** handle event bookCreated");
        Update update = new Update();
        var bookId = registeredBook.getBook().identity();
        update.set("Book." + bookId + ".isbn", registeredBook.getBook().isbn().value());
        update.set("Book." + bookId + ".inventory", registeredBook.getInventoryId().value());
        update.set("Book." + bookId + ".returnDate", registeredBook.getBook().returnDate());

        mongoTemplate.updateFirst(getFilterByAggregateId(registeredBook), update, COLLECTION_NAME);
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}
