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

package com.github.legoatoom.goldenberries.common;

import com.github.legoatoom.goldenberries.common.registries.BlockRegistry;
import com.github.legoatoom.goldenberries.common.registries.ItemRegistry;
import com.github.legoatoom.goldenberries.common.util.Init;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registries;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Supplier;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class GoldenBerries {

    /**
     * A static final string of the MOD_ID, so I don't have to write it each time and create the possibility of a typo.
     */
    public static final String MOD_ID = "goldenberries";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Reflections reflections = new Reflections("com.github.legoatoom.goldenberries");

    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));


    public static void init() {
        BlockRegistry.init();
        ItemRegistry.init();
    }
}
