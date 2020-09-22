package com.github.legoatoom.goldenberries.mixin.entity.effect;

import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StatusEffect.class)
public class StatusEffectMixin {

    @Inject(
            method = "applyUpdateEffect(Lnet/minecraft/entity/LivingEntity;I)V",
            cancellable = true,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ))
    private void applyUpdateEffect(LivingEntity entity, int amplifier, CallbackInfo ci) {
        if (entity.hasStatusEffect(ModStatusEffects.POISON_RESISTANCE)){
            if(entity.hasStatusEffect(StatusEffects.POISON)){
                ci.cancel();
            } else if (entity.hasStatusEffect(StatusEffects.WITHER)) {
                if (entity.getHealth() <= 4.0F){ ci.cancel();}
            }
        }

    }
}
