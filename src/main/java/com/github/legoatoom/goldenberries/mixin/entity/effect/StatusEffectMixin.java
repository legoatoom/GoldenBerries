/*
 * This file is part of GoldenBerries.
 *
 * GoldenBerries is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GoldenBerries is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GoldenBerries.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.legoatoom.goldenberries.mixin.entity.effect;

import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import com.github.legoatoom.goldenberries.entity.effect.StatusEffectTimeOut;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin for the {@link StatusEffect} class. This mixin will add the code that ignores gives players the ability to
 * ignore {@link StatusEffects#POISON} and make {@link StatusEffects#WITHER} not lethal.
 *
 * @author legoatoom
 */
@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {

    /**
     * A condition for all the damage sources are reduced/negated.
     *
     * @param effect the effect to check on.
     *
     * @return Boolean whether it should be effected.
     */
    private static boolean isReduced(StatusEffect effect) {
        return effect == StatusEffects.WITHER || effect == StatusEffects.POISON;
    }

    @Inject(method = "applyUpdateEffect",
            cancellable = true,
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private void applyUpdateEffect(LivingEntity entity, int amplifier, CallbackInfo ci) {
        // This method cannot be inverted as we place this section in the code before each damage.
        // Mixins are not beautiful, or well optimized code, but it works.
        if (entity.hasStatusEffect(ModStatusEffects.POISON_RESISTANCE)) {
            //noinspection ConstantConditions, we just checked that it has it.
            reduceDamage(entity, ci, entity.getStatusEffect(ModStatusEffects.POISON_RESISTANCE).getAmplifier());
        }
    }

    private void reduceDamage(LivingEntity entity, CallbackInfo ci, int poisonResistanceAmplifier) {
        StatusEffect thisEffect = (StatusEffect) (Object) this;
        if (isReduced(thisEffect)) {
            // based on the difficulty we specify a damage amount.
            StatusEffectTimeOut instance = (StatusEffectTimeOut) entity.getStatusEffect(thisEffect);
            if (instance != null) {
                int timeOut = instance.getTimeOut();
                int limit   = 0;
                switch (entity.world.getDifficulty()) {
                    case NORMAL -> limit = 3 + poisonResistanceAmplifier;
                    case HARD -> limit = 2 + poisonResistanceAmplifier;
                    default -> ci.cancel();
                }
                instance.reduceTimeOut();
                if (timeOut == 0) {
                    instance.setTimeout(limit);
                } else {
                    ci.cancel();
                }
            }
        }
    }
}

