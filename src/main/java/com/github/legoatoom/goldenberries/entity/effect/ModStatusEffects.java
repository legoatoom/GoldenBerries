/*
 * Copyright (C) 2020 legoatoom
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
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

    public static final StatusEffect POISON_RESISTANCE;

    private static StatusEffect register(String id, StatusEffect entry){
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(GoldenBerries.MOD_ID, id), entry);
    }

    static {
        POISON_RESISTANCE = register("poison_resistance", new PoisonResistanceStatusEffect());
    }
}
