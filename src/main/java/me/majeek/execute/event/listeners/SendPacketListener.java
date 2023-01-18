package me.majeek.execute.event.listeners;

import me.majeek.execute.event.CancellableEvent;
import me.majeek.execute.event.Listener;
import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface SendPacketListener extends Listener {
    void onSendPacket(SendPacketEvent event);

    class SendPacketEvent extends CancellableEvent<SendPacketListener> {
        @NotNull private final Packet<?> packet;

        public SendPacketEvent(@NotNull final Packet<?> packet) {
            this.packet = packet;
        }

        @NotNull
        public Packet<?> getPacket() {
            return this.packet;
        }

        @Override
        public Class<SendPacketListener> getListenerType() {
            return SendPacketListener.class;
        }

        @Override
        protected void invoke(ArrayList<SendPacketListener> listeners) {
            for(SendPacketListener listener : listeners) {
                listener.onSendPacket(this);

                if(this.isCancelled())
                    break;
            }
        }
    }
}
