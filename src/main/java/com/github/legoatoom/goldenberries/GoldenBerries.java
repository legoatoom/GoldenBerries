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

package com.github.legoatoom.goldenberries;

import net.fabricmc.api.ModInitializer;

/**
 * <h3>GoldenBerries implements {@link ModInitializer}</h3>
 *
 * <p>You may notice that the entry point for the mod is empty, this is because all registering of the Mod is done
 * via static classes, therefor this class only contains the a public static string for the <i>MOD_ID</i>.
 *
 * The MOD_ID is used by fabric to differentiate between mods.</p>
 *
 * @author legoatoom
 */
public class GoldenBerries implements ModInitializer {

    /**
     * A static final string of the MOD_ID, so I don't have to write it each time and create the possibility of a typo.
     */
    public static final String MOD_ID = "goldenberries";

    @Override
    public void onInitialize() {}
}
