package org.jcv.cart.eventpublisher;

public interface EventPublisher {
    void publish(String topic, Object event);
}
