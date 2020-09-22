package com.github.legoatoom.goldenberries.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;

public class PoisonResistanceStatusEffect extends StatusEffect {

    public PoisonResistanceStatusEffect(StatusEffectType type, int color) {
        super(type, color);
    }

    public PoisonResistanceStatusEffect(){
        super(StatusEffectType.BENEFICIAL, 0xa3134c);
    }
}
