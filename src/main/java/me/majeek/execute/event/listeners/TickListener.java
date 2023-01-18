package me.majeek.execute.event.listeners;

import me.majeek.execute.event.Event;
import me.majeek.execute.event.Listener;

import java.util.ArrayList;

public interface TickListener extends Listener {
    void onTick();

    class TickEvent extends Event<TickListener> {
        public static final TickEvent INSTANCE = new TickEvent();

        @Override
        public Class<TickListener> getListenerType() {
            return TickListener.class;
        }

        @Override
        protected void invoke(ArrayList<TickListener> listeners) {
            for(TickListener listener : listeners) {
                listener.onTick();
            }
        }
    }
}
