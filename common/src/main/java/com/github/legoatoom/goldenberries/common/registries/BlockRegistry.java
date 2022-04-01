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
import com.github.legoatoom.goldenberries.common.blocks.GoldenBerryBushBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

import static com.github.legoatoom.goldenberries.common.GoldenBerries.MOD_ID;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_KEY);

    public static RegistrySupplier<Block> GOLDEN_BERRY_BUSH = register("golden_berry_bush",
                    () -> new GoldenBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));


    public static RegistrySupplier<Block> register(String id, Supplier<Block> block){
        return BLOCKS.register(id, block);
    }


    public static void init() {
        GoldenBerries.LOGGER.debug("Registering Blocks");
        BLOCKS.register();
    }
}
