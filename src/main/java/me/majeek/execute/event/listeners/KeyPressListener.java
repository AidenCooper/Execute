package me.majeek.execute.event.listeners;

import me.majeek.execute.event.Event;
import me.majeek.execute.event.Listener;

import java.util.ArrayList;

public interface KeyPressListener extends Listener {
    void onKeyPress(KeyPressEvent event);

    class KeyPressEvent extends Event<KeyPressListener> {
        private final int keyCode, scanCode, action, modifiers;

        public KeyPressEvent(final int keyCode, final int scanCode, final int action, final int modifiers) {
            this.keyCode = keyCode;
            this.scanCode = scanCode;
            this.action = action;
            this.modifiers = modifiers;
        }

        public int getKeyCode() {
            return this.keyCode;
        }

        public int getScanCode() {
            return this.scanCode;
        }

        public int getAction() {
            return this.action;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        @Override
        public Class<KeyPressListener> getListenerType() {
            return KeyPressListener.class;
        }

        @Override
        protected void invoke(ArrayList<KeyPressListener> listeners) {
            for(KeyPressListener listener : listeners) {
                listener.onKeyPress(this);
            }
        }
    }
}
