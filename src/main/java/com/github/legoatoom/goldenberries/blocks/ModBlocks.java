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

package com.github.legoatoom.goldenberries.blocks;

import com.github.legoatoom.goldenberries.GoldenBerries;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The modded version of the {@link net.minecraft.block.Blocks} class containing all the blocks added by this mod.
 *
 * @author legoatoom
 */
@SuppressWarnings("SameParameterValue")
public class ModBlocks {

    public static final Block GOLDEN_BERRY_BUSH;

    static{
        GOLDEN_BERRY_BUSH = register("golden_berry_bush", new GoldenBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
    }

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(GoldenBerries.MOD_ID, id), block);
    }

    public static void init(){}
}
