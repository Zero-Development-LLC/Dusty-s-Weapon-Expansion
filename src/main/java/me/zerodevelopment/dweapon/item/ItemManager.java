package me.zerodevelopment.dweapon.item;

import me.zerodevelopment.dweapon.Manager;
import me.zerodevelopment.dweapon.toolmaterials.ToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemManager {

    public static final Item BOWSTAFF = registerItem("bowstaff", new Bowstaff(ToolMaterials.BAMBOO, 5, -2f, new FabricItemSettings()));
    public static final Item FIREWAND = registerItem("firewand", new Firewand(net.minecraft.item.ToolMaterials.GOLD, new FabricItemSettings()));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Manager.id, name), item);
    }

    public static void registerChargable(Item chargeable, float chargeTime) {
        ModelPredicateProviderRegistry.register(chargeable, new Identifier(Manager.id,"charge"),
                ((stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return 1.0f;
                }));
        ModelPredicateProviderRegistry.register(chargeable, new Identifier(Manager.id, "charging"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime()-entity.getItemUseTimeLeft())/(20f*chargeTime);
                }
                );
    }

    public static void init() {
        registerChargable(FIREWAND, Firewand.CHARGE_TIME);
    }

}
