package me.majeek.execute.module;

import me.majeek.execute.ExecuteMod;
import me.majeek.execute.event.EventManager;
import me.majeek.execute.event.listeners.ModuleToggleListener;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Module {
    @NotNull protected final static MinecraftClient CLIENT = MinecraftClient.getInstance();

    @NotNull private final ModuleName name;
    private int keyCode;
    @NotNull private final ModuleType type;

    private boolean toggled;
    private boolean hidden;

    public Module(@NotNull final ModuleName name, final int keyCode, @NotNull final ModuleType type) {
        this.name = name;
        this.keyCode = keyCode;
        this.type = type;

        this.toggled = false;
        this.hidden = false;
    }

    @NotNull
    public String getName() {
        return this.name.getName();
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    @NotNull
    public ModuleType getType() {
        return this.type;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(final boolean toggled) {
        if (this.toggled && !toggled) {
            this.toggled = false;

            this.onDisable();
        } else if (!this.toggled && toggled) {
            this.toggled = true;

            this.onEnable();
        }
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }

    public void toggle() {
        this.setToggled(!this.toggled);
    }

    public void setKeyCode(final int keyCode) {
        this.keyCode = keyCode;
    }

    protected void onEnable() {
        EventManager.invoke(new ModuleToggleListener.ModuleToggleEvent(this));
    }

    protected void onDisable() {
        EventManager.invoke(new ModuleToggleListener.ModuleToggleEvent(this));
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        return this.name == ((Module) o).name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
