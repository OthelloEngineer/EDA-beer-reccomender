package org.com;

@FunctionalInterface
public interface EventListener {
    void onEvent(ReceiveEvent payload);
}
