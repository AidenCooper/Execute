package me.majeek.execute.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.ReceivePacketListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;)V", ordinal = 0), method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V", cancellable = true)
    private void channelRead0(ChannelHandlerContext context, Packet<?> packet, CallbackInfo info) {
        ReceivePacketListener.ReceivePacketEvent event = new ReceivePacketListener.ReceivePacketEvent(context, packet);
        EventManager.invoke(event);

        if(event.isCancelled())
            info.cancel();
    }
}
