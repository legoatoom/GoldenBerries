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

package com.github.legoatoom.goldenberries.blocks

import com.github.legoatoom.goldenberries.platform.PlatformBridges
import com.github.legoatoom.goldenberries.platform.Registrar
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup

object GBBlocks {
    val BLOCKS: Registrar<Block> = PlatformBridges.registrarFactory.block()

    val GOLDEN_BERRY_BUSH: Block by BLOCKS.register("") {
        GoldenBerryBushBlock(AbstractBlock.Settings.of(Material.PLANT)
            .ticksRandomly()
            .noCollision()
            .sounds(BlockSoundGroup.SWEET_BERRY_BUSH))
    }


    fun init() {}
}


