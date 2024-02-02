package net.luckius.luckius_mortar.item.custom;

import net.luckius.luckius_mortar.recipe.ModRecipes;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.luckius.luckius_mortar.sound.ModSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

import java.util.Optional;

public class MortarItem extends Item {
	public MortarItem(Settings settings) {
		super(settings);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean onClicked(ItemStack thisStack, ItemStack otherStack, Slot thisSlot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		World world = player.getWorld();

		if (clickType == ClickType.RIGHT) {
			ItemStack input = cursorStackReference.get();

			SimpleInventory inventory = new SimpleInventory(1);
			inventory.setStack(0, new ItemStack(input.getItem(), 1));
			Optional<MortarRecipe> match = world.getRecipeManager().getFirstMatch(ModRecipes.MORTAR_RECIPE, inventory, world).map(RecipeEntry::value);

			if (match.isEmpty()) return false; // Check if a recipe was found

			if (!player.isCreative()) input.decrement(1);

			MortarRecipe recipe = match.get();

			ItemStack output = recipe.getResult(null).copy();
			if (!player.getInventory().insertStack(output)) {
				player.dropStack(output); // Drop the remains
			}

			world.playSound(null, player.getBlockPos(), ModSoundEvents.MORTAR_SOUND_CLICK, SoundCategory.PLAYERS);

			boolean canDamage = recipe.canDamage() && !player.isCreative();
			if (canDamage) thisStack.setDamage(thisStack.getDamage() + recipe.getDamage()); // Damage the item

			return true;
		} else {
			return false;
		}
	}
}
