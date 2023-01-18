package me.majeek.execute.event;

import java.util.ArrayList;

public abstract class Event<T extends Listener> {
    public abstract Class<T> getListenerType();

    protected abstract void invoke(ArrayList<T> listeners);
}
