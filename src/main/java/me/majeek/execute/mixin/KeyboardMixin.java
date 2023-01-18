package me.majeek.execute.mixin;

import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.KeyPressListener;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at = @At("HEAD"), method = "onKey(JIIII)V")
    private void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo info) {
        EventManager.invoke(new KeyPressListener.KeyPressEvent(keyCode, scanCode, action, modifiers));
    }
}
