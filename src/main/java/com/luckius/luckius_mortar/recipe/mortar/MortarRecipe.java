package com.luckius.luckius_mortar.recipe.mortar;

import com.luckius.luckius_mortar.recipe.ModRecipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MortarRecipe implements Recipe<Inventory> {
	private final Identifier id;
	final Ingredient input;
	final ItemStack output;
	final int damage;

	public MortarRecipe(Identifier id, Ingredient input, ItemStack output, int damage) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.damage = damage;
	}

	@Override
	public boolean matches(Inventory inventory, World world) {
		return this.input.test(inventory.getStack(0));
	}

	@Override
	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		return this.output.copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return this.output;
	}

	public int getDamage() {
		return this.damage;
	}
	public boolean canDamage() {
		return this.damage > 0;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipes.MORTAR_RECIPE_SERIALIZER;
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipes.MORTAR_RECIPE;
	}
}
