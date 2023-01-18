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
        @NotNull private final MovementType type;
        @NotNull private final Vec3d offset;

        public PlayerMoveEvent(@NotNull final MovementType type, @NotNull Vec3d offset) {
            this.type = type;
            this.offset = offset;
        }

        @NotNull
        public MovementType getType() {
            return this.type;
        }

        @NotNull
        public Vec3d getOffset() {
            return this.offset;
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
