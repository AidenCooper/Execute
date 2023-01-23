package me.majeek.execute.module;

import me.majeek.execute.event.listeners.KeyPressListener;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleManager implements KeyPressListener {
    @NotNull private final Set<Module> modules = new HashSet<>();

    @NotNull
    public Set<Module> getModules(final boolean showHidden) {
        return showHidden ? this.modules : this.modules.stream().filter(module -> !module.isHidden()).collect(Collectors.toSet());
    }

    @NotNull
    public Set<Module> getEnabledModules(final boolean showHidden) {
        return this.getModules(showHidden).stream().filter(Module::isToggled).collect(Collectors.toSet());
    }

    @NotNull
    public Set<Module> getDisabledModules(final boolean showHidden) {
        return this.getModules(showHidden).stream().filter(module -> !module.isToggled()).collect(Collectors.toSet());
    }

    public void add(@NotNull final Module module) {
        this.modules.add(module);
    }

    public void addAll(@NotNull final Collection<Module> modules) {
        this.modules.addAll(modules);
    }

    public void remove(@NotNull final Module module) {
        this.modules.remove(module);
    }

    public void removeAll(@NotNull final Collection<Module> modules) {
        this.modules.removeAll(modules);
    }

    @Override
    public void onKeyPress(KeyPressEvent event) {
        if(MinecraftClient.getInstance().player != null && event.getAction() == 1) {
            for (Module module : this.getModules(true)) {
                if (module.getKeyCode() == event.getKeyCode()) {
                    module.toggle();
                }
            }
        }
    }
}
