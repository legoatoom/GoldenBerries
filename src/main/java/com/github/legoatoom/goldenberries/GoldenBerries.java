package com.github.legoatoom.goldenberries;

import com.github.legoatoom.goldenberries.items.ModItems;
import net.fabricmc.api.ModInitializer;

public class GoldenBerries implements ModInitializer {

    public static final String MOD_ID = "goldenberries";


    @Override
    public void onInitialize() {
        System.out.println("Got Here");
        ModItems.registerItems();
    }
}
