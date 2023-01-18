package me.majeek.execute.event.listeners;

import me.majeek.execute.event.Event;
import me.majeek.execute.event.Listener;
import me.majeek.execute.module.Module;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface ModuleToggleListener extends Listener {
    void onModuleToggle(ModuleToggleEvent event);

    class ModuleToggleEvent extends Event<ModuleToggleListener> {
        @NotNull private final Module module;

        public ModuleToggleEvent(@NotNull final Module module) {
            this.module = module;
        }

        @NotNull
        public Module getModule() {
            return this.module;
        }

        @Override
        public Class<ModuleToggleListener> getListenerType() {
            return ModuleToggleListener.class;
        }

        @Override
        protected void invoke(ArrayList<ModuleToggleListener> listeners) {
            for(ModuleToggleListener listener : listeners) {
                listener.onModuleToggle(this);
            }
        }
    }
}
