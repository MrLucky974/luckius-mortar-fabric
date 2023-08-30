package com.luckius.luckius_mortar.item.custom;

import com.luckius.luckius_mortar.recipe.ModRecipes;
import com.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;

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
		if (clickType == ClickType.RIGHT) {
			ItemStack input = cursorStackReference.get();

			SimpleInventory inventory = new SimpleInventory(1);
			inventory.setStack(0, new ItemStack(input.getItem(), 1));
			Optional<MortarRecipe> match = player.getWorld().getRecipeManager().getFirstMatch(ModRecipes.MORTAR_RECIPE, inventory, player.getWorld());

			if (match.isEmpty()) return false; // Check if a recipe was found

			if (!player.isCreative()) input.decrement(1);

			MortarRecipe recipe = match.get();

			ItemStack output = recipe.getOutput(null).copy();

			if (!player.getInventory().insertStack(output)) {
				player.dropStack(output); // Drop the remains
			}

			boolean canDamage = recipe.canDamage() && !player.isCreative();
			if (canDamage) thisStack.setDamage(thisStack.getDamage() + recipe.getDamage()); // Damage the item

			if (!player.getWorld().isClient) {
				player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.BLOCK_STONE_HIT, SoundCategory.PLAYERS);
			}
			return true;
		} else {
			return false;
		}
	}
}
