package net.luckius.luckius_mortar.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.minecraft.recipe.RecipeEntry;

public class MortarEmiRecipe extends BasicEmiRecipe {

    public MortarEmiRecipe(RecipeEntry<MortarRecipe> recipe) {
        super(LuckiusMortarEmiPlugin.MORTAR_RECIPE_CATEGORY, recipe.id(), 70, 18);
        this.inputs.add(EmiIngredient.of(recipe.value().getInput()));
        this.outputs.add(EmiStack.of(recipe.value().getResult(null)));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        // Add an arrow texture to indicate processing
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 22, 0);

        // Adds an input slot on the left
        widgets.addSlot(inputs.get(0), 0, 0);

        // Adds an output slot on the right
        // Note that output slots need to call `recipeContext` to inform EMI about their recipe context
        // This includes being able to resolve recipe trees, favorite stacks with recipe context, and more
        widgets.addSlot(outputs.get(0), 50, 0).recipeContext(this);
    }
}