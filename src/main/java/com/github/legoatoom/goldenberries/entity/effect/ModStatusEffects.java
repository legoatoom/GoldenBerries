package com.github.legoatoom.goldenberries.entity.effect;

import com.github.legoatoom.goldenberries.GoldenBerries;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("SameParameterValue")
public class ModStatusEffects {

    public static final StatusEffect POISON_RESISTANCE;

    private static StatusEffect register(String id, StatusEffect entry){
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(GoldenBerries.MOD_ID, id), entry);
    }

    static {
        POISON_RESISTANCE = register("poison_resistance", new PoisonResistanceStatusEffect());
    }
}
