package me.majeek.execute.event.listeners;

import me.majeek.execute.event.Event;
import me.majeek.execute.event.Listener;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface PlayerMoveListener extends Listener {
    void onPlayerMove(PlayerMoveEvent event);

    class PlayerMoveEvent extends Event<PlayerMoveListener> {
        @NotNull private final Vec3d from;
        @NotNull private final Vec3d to;

        public PlayerMoveEvent(@NotNull final Vec3d from, @NotNull final Vec3d to) {
            this.from = from;
            this.to = to;
        }

        @NotNull
        public Vec3d getFrom() {
            return this.from;
        }

        @NotNull
        public Vec3d getTo() {
            return this.to;
        }

        @Override
        public Class<PlayerMoveListener> getListenerType() {
            return PlayerMoveListener.class;
        }

        @Override
        protected void invoke(ArrayList<PlayerMoveListener> listeners) {
            for(PlayerMoveListener listener : listeners) {
                listener.onPlayerMove(this);
            }
        }
    }
}
