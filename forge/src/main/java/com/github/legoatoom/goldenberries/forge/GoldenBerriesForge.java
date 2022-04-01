/*
 * This file is part of GoldenBerriesForge.
 *
 * GoldenBerriesForge is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GoldenBerriesForge is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GoldenBerriesForge.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.legoatoom.goldenberries.forge;

import com.github.legoatoom.goldenberries.common.GoldenBerries;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.github.legoatoom.goldenberries.common.GoldenBerries.MOD_ID;

@Mod(MOD_ID)
public class GoldenBerriesForge {

    public GoldenBerriesForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MOD_ID, eventBus);
        GoldenBerries.init();
    }
}
