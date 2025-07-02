package me.wait_for_meee.deathEffectPlugin.model.task;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface IObserver<T> {

    void update(@NotNull T a);
}
