package com.github.legoatoom.goldenberries.items;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent GOLDEN_BERRIES;

    static{
        GOLDEN_BERRIES = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.6F).build();
    }
}
