package me.zerodevelopment.dweapon.datagen;

import me.zerodevelopment.dweapon.Manager;
import me.zerodevelopment.dweapon.item.ItemManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //Weapons
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.BOWSTAFF, 1)
                .pattern("B")
                .pattern("B")
                .pattern("B")
                .input('B', Items.BAMBOO)
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.BOWSTAFF)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.FIREWAND, 1)
                .pattern(" GF")
                .pattern(" GG")
                .pattern("B  ")
                .input('G', Items.GOLD_INGOT)
                .input('F', Items.FIRE_CHARGE)
                .input('B', Items.BLAZE_ROD)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.FIREWAND)));
        //Chainmail armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.CHAINMAIL_HELMET)
                .pattern("INI")
                .pattern("N N")
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(Items.CHAINMAIL_HELMET)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.CHAINMAIL_CHESTPLATE)
                .pattern("N N")
                .pattern("INI")
                .pattern("NIN")
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(Items.CHAINMAIL_CHESTPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.CHAINMAIL_LEGGINGS)
                .pattern("NIN")
                .pattern("I I")
                .pattern("N N")
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(Items.CHAINMAIL_LEGGINGS)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.CHAINMAIL_BOOTS)
                .pattern("N N")
                .pattern("I I")
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(Items.CHAINMAIL_BOOTS)));
        //NameTag
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NAME_TAG)
                .pattern("N")
                .pattern("P")
                .input('N', Items.IRON_NUGGET)
                .input('P', Items.PAPER)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(Items.NAME_TAG)));
        //Shields
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.WOODENSHIELD)
                .pattern("PLP")
                .pattern("PPP")
                .pattern(" P ")
                .input('P', ItemTags.PLANKS)
                .input('L', ItemTags.LOGS)
                .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.WOODENSHIELD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.GOLDENSHIELD)
                .pattern("PLP")
                .pattern("PPP")
                .pattern(" P ")
                .input('P', Items.GOLD_INGOT)
                .input('L', ItemTags.LOGS)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.GOLDENSHIELD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.IRONSHIELD)
                .pattern("PLP")
                .pattern("PPP")
                .pattern(" P ")
                .input('P', Items.IRON_INGOT)
                .input('L', ItemTags.LOGS)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.IRONSHIELD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemManager.DIAMONDSHIELD)
                .pattern("PLP")
                .pattern("PPP")
                .pattern(" P ")
                .input('P', Items.DIAMOND)
                .input('L', ItemTags.LOGS)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(Manager.id, getRecipeName(ItemManager.DIAMONDSHIELD)));
        offerNetheriteUpgradeRecipe(exporter, ItemManager.DIAMONDSHIELD, RecipeCategory.COMBAT, ItemManager.NETHERITESHIELD);
    }
}
