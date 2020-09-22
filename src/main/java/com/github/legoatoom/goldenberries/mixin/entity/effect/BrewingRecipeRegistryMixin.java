package com.github.legoatoom.goldenberries.mixin.entity.effect;

import com.github.legoatoom.goldenberries.items.ModItems;
import com.github.legoatoom.goldenberries.potion.ModPotions;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {


    @Shadow private static void registerPotionRecipe(Potion input, Item item, Potion output){}

    @Inject(
            method = "registerDefaults()V",
            at = @At("TAIL"))
    private static void registerDefaults(CallbackInfo ci){
        registerPotionRecipe(Potions.AWKWARD, ModItems.GOLDEN_BERRIES, ModPotions.ALEXITERIC);
    }
}
