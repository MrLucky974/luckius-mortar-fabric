package net.luckius.luckius_mortar.recipe;

import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;

public class ModRecipes {
	public static final RecipeType<MortarRecipe> MORTAR_RECIPE = new RecipeType<>() {};
	public static final RecipeSerializer<MortarRecipe> MORTAR_RECIPE_SERIALIZER = new MortarRecipeSerializer();
}
