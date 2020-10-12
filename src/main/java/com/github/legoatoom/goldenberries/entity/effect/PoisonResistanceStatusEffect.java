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

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

/**
 * The status effect resulted from the {@link com.github.legoatoom.goldenberries.potion.ModPotions#ALEXITERIC} potion.
 * This gives the settings of the potion and the colour.
 *
 * @author legoatoom
 */
public class PoisonResistanceStatusEffect extends StatusEffect {

    public PoisonResistanceStatusEffect(){
        super(StatusEffectType.BENEFICIAL, 0x0FD2FC);
    }
}
