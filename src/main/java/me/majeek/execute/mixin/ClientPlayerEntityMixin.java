package me.majeek.execute.mixin;

import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.PlayerMoveListener;
import me.majeek.execute.event.listeners.TickListener;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")
    private void move(MovementType type, Vec3d offset, CallbackInfo info) {
        EventManager.invoke(new PlayerMoveListener.PlayerMoveEvent(type, offset));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", ordinal = 0), method = "tick()V")
    private void tick(CallbackInfo info) {
        EventManager.invoke(TickListener.TickEvent.INSTANCE);
    }
}
