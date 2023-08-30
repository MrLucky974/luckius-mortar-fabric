package com.luckius.luckius_mortar.recipe.mortar;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class MortarRecipeSerializer implements RecipeSerializer<MortarRecipe> {
	@Override
	public MortarRecipe read(Identifier id, JsonObject json) {
		// Gets the object specified by the "input" key
		var inputObject = json.getAsJsonObject("ingredient");
		// Helper method to read Ingredient from JsonElement
		var input = Ingredient.fromJson(inputObject);
		// Gets the string in the "output" key
		var outputIdentifier = json.get("result").getAsString();
		// Gets the integer in the "count" key, fallbacking to 1 if it does not exist
		var count = JsonHelper.getInt(json, "count", 1);
		// Attempts to get the item in "output" with the registry and creates a stack with it
		var output = new ItemStack(Registries.ITEM.getOrEmpty(new Identifier(outputIdentifier))
				.orElseThrow(() -> new IllegalStateException("Item " + outputIdentifier + " does not exist")), count);
		var damage = JsonHelper.getInt(json, "damage", 0);

		return new MortarRecipe(id, input, output, Math.max(0, damage));
	}


	/*public JsonObject toJson(MortarRecipe recipe) {
		var obj = new JsonObject();
		// Puts the serialized ingredient json element into the "input" key
		obj.add("ingredient", recipe.input.toJson());
		// Checks if the output count is higher than the default, and if it is, adds into the "count" key
		if (recipe.output.getCount() > 1) {
			obj.addProperty("count", recipe.output.getCount());
		}
		// Gets the output identifier from the registry and adds it into the "output" key
		obj.addProperty("result", Registries.ITEM.getId(recipe.output.getItem()).toString());

		obj.addProperty("damage", Math.max(0, recipe.damage));

		return obj;
	}

	@Override
	public MortarRecipe read(Identifier id, JsonObject json) {
		// Gets the object specified by the "input" key
		var inputObject = json.getAsJsonObject("ingredient");
		// Helper method to read Ingredient from JsonElement
		var input = Ingredient.fromJson(inputObject);
		// Gets the string in the "output" key
		var outputIdentifier = json.get("result").getAsString();
		// Gets the integer in the "count" key, fallbacking to 1 if it does not exist
		var count = JsonHelper.getInt(json, "count", 1);
		// Attempts to get the item in "output" with the registry and creates a stack with it
		var output = new ItemStack(Registries.ITEM.getOrEmpty(new Identifier(outputIdentifier))
				.orElseThrow(() -> new IllegalStateException("Item " + outputIdentifier + " does not exist")), count);
		var damage = JsonHelper.getInt(json, "damage", 0);

		return new MortarRecipe(id, input, output, Math.max(0, damage));
	}*/

	@Override
	public MortarRecipe read(Identifier id, PacketByteBuf buf) {
		var input = Ingredient.fromPacket(buf);
		var output = buf.readItemStack();
		var damage = buf.readInt();

		return new MortarRecipe(id, input, output, damage);
	}

	@Override
	public void write(PacketByteBuf buf, MortarRecipe recipe) {
		recipe.input.write(buf);
		buf.writeItemStack(recipe.output);
		buf.writeInt(recipe.damage);
	}
}
