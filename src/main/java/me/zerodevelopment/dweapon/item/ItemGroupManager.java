package me.zerodevelopment.dweapon.item;

import me.zerodevelopment.dweapon.Manager;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupManager {
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Manager.id, "moditemgroup"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.moditemgroup"))
                    .icon(() -> new ItemStack(ItemManager.BOWSTAFF))
                    .entries((displayContext, entries) -> {
                        entries.add(ItemManager.BOWSTAFF);
                        entries.add(ItemManager.FIREWAND);
                    }).build());

    public static void init() {

    }
}
