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

package com.github.legoatoom.goldenberries.items

import com.github.legoatoom.goldenberries.blocks.GBBlocks
import com.github.legoatoom.goldenberries.platform.PlatformBridges
import com.github.legoatoom.goldenberries.platform.Registrar
import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

object GBItems {

    @JvmField
    val ITEMS: Registrar<Item> = PlatformBridges.registrarFactory.item()

    val GOLDEN_BERRIES by ITEMS.register("golden_berries") {
        AliasedBlockItem(GBBlocks.GOLDEN_BERRY_BUSH,
            Item.Settings().food(
                FoodComponent.Builder().hunger(3).saturationModifier(0.6F).build()
            ).group(ItemGroup.BREWING))
    }

    fun init() {}
}
