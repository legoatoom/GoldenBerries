/*
 * This file is part of GoldenBerriesFabric.
 *
 * GoldenBerriesFabric is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GoldenBerriesFabric is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GoldenBerriesFabric.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.legoatoom.goldenberries.fabric

import com.github.legoatoom.goldenberries.items.GBItems
import net.fabricmc.api.ModInitializer

object GoldenBerriesFabric : ModInitializer {

    override fun onInitialize() {
        GBItems.init()
    }
}


