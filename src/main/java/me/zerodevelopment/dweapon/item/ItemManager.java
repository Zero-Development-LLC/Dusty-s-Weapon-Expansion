package me.zerodevelopment.dweapon.item;

import me.zerodevelopment.dweapon.Manager;
import me.zerodevelopment.dweapon.toolmaterials.ToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemManager {

    public static final Item BOWSTAFF = registerItem("bowstaff", new BowStaff(ToolMaterials.BAMBOO, 5, -3.65f, new FabricItemSettings()));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Manager.id, name), item);
    }

    public static void init() {

    }

}
