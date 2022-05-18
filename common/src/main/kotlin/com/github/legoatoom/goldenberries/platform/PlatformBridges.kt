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

package com.github.legoatoom.goldenberries.platform

import dev.architectury.injectables.annotations.ExpectPlatform



interface PlatformBridges {
//    val blockEntities: BlockEntityBridge
//    val blockFactory: BlockFactory
//    val criteria: CriterionBridge
//    val configManager: ConfigManager
//    val entities: EntityBridge
//    val fluids: FluidBridge
//    val fluidRendering: FluidRenderingBridge
//    val items: ItemBridge
//    val menus: MenuBridge
//    val network: NetworkBridge
    val registrarFactory: RegistrarFactory
//    val resources: ResourceBridge

    companion object {
        inline val registrarFactory get() = getFromPlatForm().registrarFactory
    }
}

@ExpectPlatform
fun PlatformBridges.Companion.getFromPlatForm(): PlatformBridges =
    throw AssertionError()
