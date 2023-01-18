package me.majeek.execute.module;

import org.jetbrains.annotations.NotNull;

public enum ModuleName {
    FLIGHT("Flight"),
    NO_FALL("NoFall");

    @NotNull private final String name;

    ModuleName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return this.name;
    }
}
