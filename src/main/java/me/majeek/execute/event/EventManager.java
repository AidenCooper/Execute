package me.majeek.execute.event;

import me.majeek.execute.ExecuteMod;

import java.util.*;

public class EventManager {
    private final HashMap<Class<? extends Listener>, ArrayList<? extends Listener>> listenerMap = new HashMap<>();

    public static <L extends Listener, E extends Event<L>> void invoke(E event) {
        EventManager manager = ExecuteMod.INSTANCE.getEventManager();

        if(manager == null)
            return;

        manager.invokeImpl(event);
    }

    private <L extends Listener, E extends Event<L>> void invokeImpl(E event) {
        try {
            Class<L> type = event.getListenerType();
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>) this.listenerMap.get(type);

            if(listeners == null || listeners.isEmpty())
                return;

            ArrayList<L> listenersCopy = new ArrayList<>(listeners);
            listenersCopy.removeIf(Objects::isNull);

            event.invoke(listenersCopy);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public <L extends Listener> void add(Class<L> type, L listener) {
        try {
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>) this.listenerMap.get(type);

            if(listeners == null) {
                listeners = new ArrayList<>(List.of(listener));
                this.listenerMap.put(type, listeners);
                return;
            }

            listeners.add(listener);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public <L extends Listener> void remove(Class<L> type, L listener) {
        try {
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>) this.listenerMap.get(type);

            if(listeners != null)
                listeners.remove(listener);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
