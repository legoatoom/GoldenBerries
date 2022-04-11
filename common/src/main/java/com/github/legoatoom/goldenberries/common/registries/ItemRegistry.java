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

package com.github.legoatoom.goldenberries.common.registries;

import com.github.legoatoom.goldenberries.common.GoldenBerries;
import com.github.legoatoom.goldenberries.common.items.ModFoodComponents;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static com.github.legoatoom.goldenberries.common.GoldenBerries.MOD_ID;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_KEY);

    public static Item GOLDEN_BERRIES = ITEMS.register("golden_berries", () ->
            new AliasedBlockItem(BlockRegistry.GOLDEN_BERRY_BUSH.get(), new Item.Settings()
                    .food(ModFoodComponents.GOLDEN_BERRIES)
                    .group(ItemGroup.FOOD)
            )).get();


    public static void init() {
        GoldenBerries.LOGGER.debug("Registering Items");
        ITEMS.register();
    }
}
