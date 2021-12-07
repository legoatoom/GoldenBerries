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

package com.github.legoatoom.goldenberries.items;

import net.minecraft.item.FoodComponent;

/**
 * The modded version of the {@link net.minecraft.item.FoodComponents} class containing all the food components added
 * by this mod.
 * @author legoatoom
 */
public class ModFoodComponents {
    public static final FoodComponent GOLDEN_BERRIES;

    static{
        GOLDEN_BERRIES = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.6F).build();
    }
}
