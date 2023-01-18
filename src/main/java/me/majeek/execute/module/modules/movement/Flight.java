package me.majeek.execute.module.modules.movement;

import me.majeek.execute.module.Module;
import me.majeek.execute.module.ModuleName;
import me.majeek.execute.module.ModuleType;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module {
    public Flight() {
        super(ModuleName.FLIGHT, GLFW.GLFW_KEY_G, ModuleType.MOVEMENT);
    }
}
