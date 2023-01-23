package me.majeek.execute.module.modules.movement;

import me.majeek.execute.ExecuteMod;
import me.majeek.execute.event.listeners.TickListener;
import me.majeek.execute.module.Module;
import me.majeek.execute.module.ModuleName;
import me.majeek.execute.module.ModuleType;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Module implements TickListener {
    public NoFall() {
        super(ModuleName.NO_FALL, GLFW.GLFW_KEY_UNKNOWN, ModuleType.MOVEMENT);
    }

    @Override
    public void onEnable() {
        ExecuteMod.INSTANCE.getEventManager().add(TickListener.class, this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        ExecuteMod.INSTANCE.getEventManager().remove(TickListener.class, this);

        super.onDisable();
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = CLIENT.player;
        if(player != null) {
            if(player.fallDistance > 3) {
                player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
            }
//            if(player.fallDistance > (player.isFallFlying() ? 1 : 2) && !(player.isFallFlying() && player.isSneaking() && player.getVelocity().getY() >= -0.5)) {
//                player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
//            }
        }
    }
}
