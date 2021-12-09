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

package com.github.legoatoom.goldenberries.potion;

import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

/**
 * ModPotions is the Modded version of the {@link net.minecraft.potion.Potions} class.
 *
 * Containing all the potions that this mod adds.
 * @author legoatoom
 */
public class ModPotions {
    public static final Potion ALEXITERIC;
    public static Potion LONG_ALEXITERIC;

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, name, potion);
    }

    static {
        ALEXITERIC = register("alexiteric", new Potion(new StatusEffectInstance(ModStatusEffects.POISON_RESISTANCE, 3600)));
        LONG_ALEXITERIC = register("long_alexiteric", new Potion(new StatusEffectInstance(ModStatusEffects.POISON_RESISTANCE, 9600)));
    }

    public static void init(){}
}
