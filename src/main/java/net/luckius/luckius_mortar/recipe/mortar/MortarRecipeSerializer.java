package net.luckius.luckius_mortar.recipe.mortar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;

public class MortarRecipeSerializer implements RecipeSerializer<MortarRecipe> {
	// TODO: standardize result format to standard output format and use ItemStack.RECIPE_RESULT_CODEC
	Codec<MortarRecipe> CODEC = RecordCodecBuilder.create(instance->instance.group(
			Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(MortarRecipe::getInput),
			Registries.ITEM.createEntryCodec().fieldOf("result").forGetter(recipe->recipe.output.getRegistryEntry()),
			Codec.INT.optionalFieldOf("count",1).forGetter(recipe->recipe.output.getCount()),
			Codec.INT.optionalFieldOf("damage",0).forGetter(MortarRecipe::getDamage)
	).apply(instance,(ingredient,result,count,damage)->new MortarRecipe(ingredient,new ItemStack(result,count),Math.max(damage,0))));

	@Override
	public Codec<MortarRecipe> codec() {
		return CODEC;
	}

	@Override
	public MortarRecipe read(PacketByteBuf buf) {
		var input = Ingredient.fromPacket(buf);
		var output = buf.readItemStack();
		var damage = buf.readInt();

		return new MortarRecipe(input, output, damage);
	}

	@Override
	public void write(PacketByteBuf buf, MortarRecipe recipe) {
		recipe.input.write(buf);
		buf.writeItemStack(recipe.output);
		buf.writeInt(recipe.damage);
	}
}
