package me.majeek.execute.event.listeners;

import io.netty.channel.ChannelHandlerContext;
import me.majeek.execute.event.CancellableEvent;
import me.majeek.execute.event.Listener;
import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface ReceivePacketListener extends Listener {
    void onReceivePacket(ReceivePacketEvent event);

    class ReceivePacketEvent extends CancellableEvent<ReceivePacketListener> {
        @NotNull private final ChannelHandlerContext context;
        @NotNull private final Packet<?> packet;

        public ReceivePacketEvent(@NotNull final ChannelHandlerContext context, @NotNull final Packet<?> packet) {
            this.context = context;
            this.packet = packet;
        }

        @NotNull
        public ChannelHandlerContext getContext() {
            return this.context;
        }

        @NotNull
        public Packet<?> getPacket() {
            return this.packet;
        }

        @Override
        public Class<ReceivePacketListener> getListenerType() {
            return ReceivePacketListener.class;
        }

        @Override
        protected void invoke(ArrayList<ReceivePacketListener> listeners) {
            for(ReceivePacketListener listener : listeners) {
                listener.onReceivePacket(this);

                if(this.isCancelled())
                    break;
            }
        }
    }
}
