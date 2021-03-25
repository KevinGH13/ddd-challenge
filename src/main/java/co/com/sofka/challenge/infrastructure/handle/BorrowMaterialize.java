package co.com.sofka.challenge.infrastructure.handle;


import co.com.sofka.challenge.domain.borrow.events.BorrowCreated;
import co.com.sofka.challenge.domain.borrow.events.BorrowUpdated;
import co.com.sofka.challenge.domain.borrow.events.RegisteredBorrowRequest;
import co.com.sofka.challenge.domain.borrow.events.RegisteredUser;
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
public class BorrowMaterialize {

    private static final String COLLECTION_NAME = "Borrow";
    private static final String COLLECTION_USER = "Users";
    private static final Logger logger = Logger.getLogger(BorrowMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleBorrowCreated(BorrowCreated borrowCreated){
        logger.info("*** handle event borrowCreated");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", borrowCreated.getBorrowId().value());
        data.put("isBorrowCreated", true);
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleBorrowUpdated(BorrowUpdated borrowUpdated){
        logger.info("*** handle event borrowUpdated");
        Update update = new Update();
        var borrowId = borrowUpdated.getBorrowId().value();
        update.set("Borrow." + borrowId + ".date", borrowUpdated.getDate());
        update.set("Borrow." + borrowId + ".ticket", borrowUpdated.getTicket().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(borrowUpdated), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleRegisteredRequest(RegisteredBorrowRequest registeredBorrowRequest){
       logger.info("*** handle event registeredBorrowRequest");
       Map<String, Object> data = new HashMap<>();
       data.put("_id", registeredBorrowRequest.getBorrowId().value());
       data.put("isBorrowRegistered", true);
       mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleRegisteredUser(RegisteredUser registeredUser){
        logger.info("*** handle event registeredUser");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", registeredUser.getUser().identity().value());
        data.put("isUserRegistered", true);
        mongoTemplate.save(data, COLLECTION_USER);
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }
}
