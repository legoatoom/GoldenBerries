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

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * The status effect resulted from the {@link com.github.legoatoom.goldenberries.potion.ModPotions#ALEXITERIC} potion.
 * This gives the settings of the potion and the colour.
 *
 * @author legoatoom
 */
public class PoisonResistanceStatusEffect extends StatusEffect {

    public PoisonResistanceStatusEffect(){
        super(StatusEffectCategory.BENEFICIAL, 0x0FD2FC);
    }
}
