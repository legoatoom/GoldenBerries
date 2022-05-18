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

package com.github.legoatoom.goldenberries.fabric.platform

import com.github.legoatoom.goldenberries.platform.PlatformBridges
import com.github.legoatoom.goldenberries.platform.Registrar
import com.github.legoatoom.goldenberries.platform.RegistrarFactory
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

private object PlatformBridgesImpl : PlatformBridges {
    override val registrarFactory: RegistrarFactory = object : RegistrarFactory {
        override fun item(): Registrar<Item> = RegistrarImpl(Registry.ITEM)
        override fun block(): Registrar<Block> = RegistrarImpl(Registry.BLOCK)
    }
}

fun PlatformBridges.Companion.get() : PlatformBridges =
    PlatformBridgesImpl
