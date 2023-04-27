package org.com;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractReceiver implements EventReceiver {
    private final Set<EventListener> listeners = new HashSet<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    protected final void fire(ReceiveEvent event){
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
