package me.majeek.execute;

import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.KeyPressListener;
import me.majeek.execute.module.ModuleManager;
import me.majeek.execute.module.modules.movement.Flight;
import me.majeek.execute.module.modules.movement.NoFall;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class ExecuteMod implements ModInitializer {
	private EventManager eventManager;
	private ModuleManager moduleManager;

	public static MinecraftClient CLIENT;
	public static ExecuteMod INSTANCE;
	public static Logger LOGGER;

	@Override
	public void onInitialize() {
		CLIENT = MinecraftClient.getInstance();
		INSTANCE = this;
		LOGGER = LoggerFactory.getLogger("execute");

		this.eventManager = new EventManager();
		this.moduleManager = new ModuleManager();

		this.moduleManager.addAll(new HashSet<>(){{
			add(new Flight());
			add(new NoFall());
		}});
		ExecuteMod.INSTANCE.getEventManager().add(KeyPressListener.class, this.moduleManager);
	}

	public EventManager getEventManager() {
		return this.eventManager;
	}

	public ModuleManager getModuleManager() {
		return this.moduleManager;
	}
}
