package me.majeek.execute.event;

public abstract class CancellableEvent<T extends Listener> extends Event<T> {
    private boolean cancelled = false;

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
