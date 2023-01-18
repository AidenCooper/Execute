package me.majeek.execute.mixin;

import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.SendPacketListener;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(at = @At("HEAD"), method = "sendPacket(Lnet/minecraft/network/Packet;)V", cancellable = true)
    private void sendPacket(Packet<?> packet, CallbackInfo info) {
        SendPacketListener.SendPacketEvent event = new SendPacketListener.SendPacketEvent(packet);
        EventManager.invoke(event);

        if(event.isCancelled())
            info.cancel();
    }
}
