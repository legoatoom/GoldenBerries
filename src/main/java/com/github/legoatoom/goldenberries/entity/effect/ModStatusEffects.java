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

package com.github.legoatoom.goldenberries.entity.effect;

import com.github.legoatoom.goldenberries.GoldenBerries;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * ModStatusEffects is the Modded version of the {@link net.minecraft.entity.effect.StatusEffects} class.
 *
 * Containing all the status effects this mod has.
 * @author legoatoom
 */
@SuppressWarnings("SameParameterValue")
public class ModStatusEffects {

    public static StatusEffect POISON_RESISTANCE = new PoisonResistanceStatusEffect();

    private static void register(String id, StatusEffect entry){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(GoldenBerries.MOD_ID, id), entry);
    }

    public static void init(){
         register("poison_resistance", POISON_RESISTANCE);
    }
}
