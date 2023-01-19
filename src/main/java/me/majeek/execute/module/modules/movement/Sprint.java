package me.majeek.execute.module.modules.movement;

import me.majeek.execute.ExecuteMod;
import me.majeek.execute.event.listeners.PlayerMoveListener;
import me.majeek.execute.event.listeners.TickListener;
import me.majeek.execute.module.Module;
import me.majeek.execute.module.ModuleName;
import me.majeek.execute.module.ModuleType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module implements TickListener {
    public Sprint() {
        super(ModuleName.SPRINT, GLFW.GLFW_KEY_UNKNOWN, ModuleType.MOVEMENT);
    }

    @Override
    public void onEnable() {
        ExecuteMod.INSTANCE.getEventManager().add(TickListener.class, this);
    }

    @Override
    public void onDisable() {
        ExecuteMod.INSTANCE.getEventManager().remove(TickListener.class, this);
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player == null) return;

        if(player.getHungerManager().getFoodLevel() <= 6) return;
        if(player.hasStatusEffect(StatusEffects.BLINDNESS)) return;
        if(player.isUsingItem()) return;
        if(player.isSneaking()) return;
        if(player.horizontalCollision) return;
        if(player.isInsideWaterOrBubbleColumn() || player.isSubmergedInWater()) return;
        if(!player.input.hasForwardMovement()) return;

        MinecraftClient.getInstance().player.setSprinting(true);
    }
}
