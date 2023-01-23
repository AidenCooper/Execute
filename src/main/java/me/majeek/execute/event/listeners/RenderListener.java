package me.majeek.execute.event.listeners;

import me.majeek.execute.event.Event;
import me.majeek.execute.event.Listener;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface RenderListener extends Listener {
    void onRender(RenderEvent event);

    class RenderEvent extends Event<RenderListener> {
        @NotNull private final MatrixStack matrices;
        private final float tickDelta;

        public RenderEvent(@NotNull final MatrixStack matrices, final float tickDelta) {
            this.matrices = matrices;
            this.tickDelta = tickDelta;
        }

        @NotNull
        public MatrixStack getMatrices() {
            return this.matrices;
        }

        public float getTickDelta() {
            return this.tickDelta;
        }

        @Override
        public Class<RenderListener> getListenerType() {
            return RenderListener.class;
        }

        @Override
        protected void invoke(ArrayList<RenderListener> listeners) {
            for(RenderListener listener : listeners) {
                listener.onRender(this);
            }
        }
    }
}
