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

import com.github.legoatoom.goldenberries.GoldenBerries;
import com.github.legoatoom.goldenberries.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The modded version of the {@link net.minecraft.item.Items} class containing all the items added by this mod.
 *
 * @author legoatoom
 */
@SuppressWarnings("SameParameterValue")
public class ModItems {


    public static final Item GOLDEN_BERRIES;

    private static Item register(String id, Item item){
        return register(new Identifier(GoldenBerries.MOD_ID,id), item);
    }

    private static Item register(Identifier id, Item item){
        if (item instanceof BlockItem){
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, id, item);
    }

    static {
        GOLDEN_BERRIES = register("golden_berries", new AliasedBlockItem(ModBlocks.GOLDEN_BERRY_BUSH, new FabricItemSettings().food(ModFoodComponents.GOLDEN_BERRIES).group(ItemGroup.BREWING)));
    }

    public static void init(){}
}
