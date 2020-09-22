package com.github.legoatoom.goldenberries.potion;

import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

public class ModPotions {
    public static final Potion ALEXITERIC;
    public static Potion LONG_ALEXITERIC;
    public static Potion STRONG_ALEXITERIC;

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, name, potion);
    }

    static {
        ALEXITERIC = register("alexiteric", new Potion(new StatusEffectInstance(ModStatusEffects.POISON_RESISTANCE, 3600)));
    }
}
