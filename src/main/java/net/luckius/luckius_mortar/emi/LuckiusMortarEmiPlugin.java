package net.luckius.luckius_mortar.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.luckius.luckius_mortar.LuckiusMortar;
import net.luckius.luckius_mortar.item.ModItems;
import net.luckius.luckius_mortar.recipe.ModRecipes;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class LuckiusMortarEmiPlugin implements EmiPlugin {
    public static final EmiRecipeCategory MORTAR_RECIPE_CATEGORY
            = new EmiRecipeCategory(new Identifier(LuckiusMortar.MOD_ID, "mortar"), EmiStack.of(ModItems.WOODEN_MORTAR));


    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(MORTAR_RECIPE_CATEGORY);

        registry.addWorkstation(MORTAR_RECIPE_CATEGORY, EmiStack.of(ModItems.WOODEN_MORTAR));
        registry.addWorkstation(MORTAR_RECIPE_CATEGORY, EmiStack.of(ModItems.STONE_MORTAR));
        registry.addWorkstation(MORTAR_RECIPE_CATEGORY, EmiStack.of(ModItems.IRON_MORTAR));
        registry.addWorkstation(MORTAR_RECIPE_CATEGORY, EmiStack.of(ModItems.DIAMOND_MORTAR));

        RecipeManager manager = registry.getRecipeManager();

        for (MortarRecipe recipe : manager.listAllOfType(ModRecipes.MORTAR_RECIPE)) {
            registry.addRecipe(new MortarEmiRecipe(recipe));
        }
    }
}
