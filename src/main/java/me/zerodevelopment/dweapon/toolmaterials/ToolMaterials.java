package me.zerodevelopment.dweapon.toolmaterials;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ToolMaterials implements ToolMaterial {
    BAMBOO(350, MiningLevels.HAND, 0, 0, 0, () -> Ingredient.ofItems(Items.BAMBOO));

    private int durability, miningLevel, enchantability;
    private float miningSpeedMultiplier, attackDamage;
    private Supplier<Ingredient> repairIngredients;

    private ToolMaterials(int durability, int miningLevel, int enchantability, float miningSpeedMultiplier, float attackDamage, Supplier<Ingredient> repairIngredients) {
        this.durability = durability;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.repairIngredients = repairIngredients;
    }



    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredients.get();
    }
}
