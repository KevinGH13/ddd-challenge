package co.com.sofka.challenge.infrastructure.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}