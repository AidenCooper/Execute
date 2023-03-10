package me.majeek.execute.mixin;

import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.RenderListener;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        EventManager.invoke(new RenderListener.RenderEvent(matrices, tickDelta));
    }
}
