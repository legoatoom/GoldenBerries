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

import com.github.legoatoom.goldenberries.lib.Registered

/**
 * @author Adorn/Juuz
 */
interface Registrar<T> {
    /**
     * Registers an object with the [id]. The object is created using the [provider].
     */
    fun <U : T> register(id: String, provider: () -> U): Registered<U>

    /**
     * Registers an optional object with the [id]. The [provider] can either create the object,
     * or return null, in which case the return value contains null.
     *
     * Note that this, unlike the other methods, always invokes the provider eagerly even on Forge.
     */
    fun <U : T> registerOptional(id: String, provider: () -> U?): Registered<U?> {
        val value: U = provider.invoke() ?: return Registered { null }
        return register(id) { value }
    }
}
