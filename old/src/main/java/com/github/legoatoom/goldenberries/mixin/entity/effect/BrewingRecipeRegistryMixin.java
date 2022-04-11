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

import com.github.legoatoom.goldenberries.items.ModItems;
import com.github.legoatoom.goldenberries.potion.ModPotions;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin for the {@link BrewingRecipeRegistry}.
 * Because Minecraft's potion registry and recipes are not yet data based via .json files, as normal crafting recipes are,
 * this class has to register my recipes directly as a mixin during the registration of the other 'vanilla' recipes.
 *
 * @author legoatoom
 */
@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {


    @Shadow private static void registerPotionRecipe(Potion input, Item item, Potion output){}

    @Inject(
            method = "registerDefaults()V",
            at = @At("TAIL"))
    private static void registerDefaults(CallbackInfo ci){
//        ModItems.init(); // Needs to otherwise it crashes :(
//        ModPotions.init();
        registerPotionRecipe(Potions.AWKWARD, ModItems.GOLDEN_BERRIES, ModPotions.ALEXITERIC);
        registerPotionRecipe(ModPotions.ALEXITERIC, Items.REDSTONE, ModPotions.LONG_ALEXITERIC);
    }
}
