package com.github.legoatoom.goldenberries.items;

import com.github.legoatoom.goldenberries.GoldenBerries;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static Item GOLDEN_BERRIES;

    private static Item register(String id, Item item){
        return register(new Identifier(GoldenBerries.MOD_ID,id), item);
    }

    private static Item register(Identifier id, Item item){
        return Registry.register(Registry.ITEM, id, item);
    }

    public static void registerItems() {
        GOLDEN_BERRIES = register("golden_berries", new Item((new Item.Settings()).group(ItemGroup.BREWING).food(FoodComponents.GOLDEN_CARROT)));
    }
}
