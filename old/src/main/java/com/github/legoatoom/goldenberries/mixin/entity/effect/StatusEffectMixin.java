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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * The mixin for the {@link StatusEffect} class.
 * This mixin will add the code that ignores gives players the ability to ignore {@link StatusEffects#POISON} and make
 * {@link StatusEffects#WITHER} not lethal.
 *
 * @author legoatoom
 */
@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {

    @Inject(
            method = "applyUpdateEffect(Lnet/minecraft/entity/LivingEntity;I)V",
            cancellable = true,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    private void applyUpdateEffect(LivingEntity entity, int amplifier, CallbackInfo ci) {
        if (entity.hasStatusEffect(ModStatusEffects.POISON_RESISTANCE)){
            // Apparently this works.
            if ((Object)this == StatusEffects.WITHER) ci.cancel();
            if ((Object)this == StatusEffects.POISON) ci.cancel();
        }
    }
}
