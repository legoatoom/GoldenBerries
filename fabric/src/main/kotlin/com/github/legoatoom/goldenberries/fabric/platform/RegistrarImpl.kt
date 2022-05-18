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

import com.github.legoatoom.goldenberries.GoldenBerriesCommon.id
import com.github.legoatoom.goldenberries.lib.Registered
import com.github.legoatoom.goldenberries.platform.Registrar
import net.minecraft.util.registry.Registry

class RegistrarImpl<T>(private val registry: Registry<T>) : Registrar<T> {
    override fun <U : T> register(id: String, provider: () -> U): Registered<U> {
        val registered =  Registry.register(registry, id(id), provider())
        return Registered<U> { registered }
    }
}
