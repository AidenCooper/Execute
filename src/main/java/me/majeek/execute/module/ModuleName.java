package me.majeek.execute.module;

import org.jetbrains.annotations.NotNull;

public enum ModuleName {
    FLIGHT("Flight"),
    HUD("Hud"),
    NO_FALL("NoFall"),
    SPRINT("Sprint");

    @NotNull private final String name;

    ModuleName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return this.name;
    }
}
