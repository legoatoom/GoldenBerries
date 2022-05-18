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

package com.github.legoatoom.goldenberries.forge

import com.github.legoatoom.goldenberries.GoldenBerriesCommon
import com.github.legoatoom.goldenberries.blocks.GBBlocks
import com.github.legoatoom.goldenberries.items.GBItems
import dev.architectury.registry.registries.Registrar
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(GoldenBerriesCommon.MOD_ID)
object GoldenBerriesForge {
    init {
        MOD_BUS.addListener(this::init)
        register(GBBlocks.BLOCKS, MOD_BUS)
        register(GBItems.ITEMS, MOD_BUS)
    }

    private fun <T> register(registrar: Registrar<T>, modBus: IEventBus){
        registrar as ForgeRegistra
    }

    private fun init(event: FMLCommonSetupEvent){

    }
}
