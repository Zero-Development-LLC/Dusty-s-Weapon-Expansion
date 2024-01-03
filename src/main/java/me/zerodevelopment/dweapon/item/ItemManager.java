package me.zerodevelopment.dweapon.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import me.zerodevelopment.dweapon.Manager;
import me.zerodevelopment.dweapon.toolmaterials.ToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ItemManager {

    //ability weapons
    public static final Item BOWSTAFF = registerItem("bowstaff", new Bowstaff(ToolMaterials.BAMBOO, 3, -2f, new FabricItemSettings()));
    public static final Item FIREWAND = registerItem("firewand", new Firewand(net.minecraft.item.ToolMaterials.GOLD, new FabricItemSettings()));

    //shields
    public static final Item WOODENSHIELD = registerItem("woodenshield", new FabricShieldItem(new FabricItemSettings().maxDamage(net.minecraft.item.ToolMaterials.WOOD.getDurability()),
            160, 15, ItemTags.PLANKS));
    public static final Item GOLDENSHIELD = registerItem("goldenshield", new FabricShieldItem(new FabricItemSettings().maxDamage(net.minecraft.item.ToolMaterials.GOLD.getDurability()),
            120, 20, Items.GOLD_INGOT));
    public static final Item IRONSHIELD = registerItem("ironshield", new FabricShieldItem(new FabricItemSettings().maxDamage(net.minecraft.item.ToolMaterials.IRON.getDurability()),
            80, 10, Items.IRON_INGOT));
    public static final Item DIAMONDSHIELD = registerItem("diamondshield", new FabricShieldItem(new FabricItemSettings().maxDamage(net.minecraft.item.ToolMaterials.DIAMOND.getDurability()),
            60, 17, Items.DIAMOND));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Manager.id, name), item);
    }

    //Add registeries in the Client class!
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

    }

}
