package me.majeek.execute.module.modules.movement;

import me.majeek.execute.module.Module;
import me.majeek.execute.module.ModuleName;
import me.majeek.execute.module.ModuleType;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Module {
    public NoFall() {
        super(ModuleName.NO_FALL, GLFW.GLFW_KEY_M, ModuleType.MOVEMENT);
    }
}
