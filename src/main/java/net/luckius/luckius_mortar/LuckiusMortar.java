package net.luckius.luckius_mortar;

import net.luckius.luckius_mortar.item.ModItems;
import net.luckius.luckius_mortar.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckius.luckius_mortar.sound.ModSoundEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckiusMortar implements ModInitializer {
	public static final String MOD_ID = "luckius_mortar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world from Luckius' Mortar!");

		// Register sounds
		Registry.register(Registries.SOUND_EVENT, ModSoundEvents.MORTAR_SOUND_CLICK_ID, ModSoundEvents.MORTAR_SOUND_CLICK);

		// Register recipe type
		Registry.register(Registries.RECIPE_TYPE, new Identifier(MOD_ID, "mortar"), ModRecipes.MORTAR_RECIPE);
		Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "mortar"), ModRecipes.MORTAR_RECIPE_SERIALIZER);

		// Register items
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "wooden_mortar"), ModItems.WOODEN_MORTAR);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "stone_mortar"), ModItems.STONE_MORTAR);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "iron_mortar"), ModItems.IRON_MORTAR);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "diamond_mortar"), ModItems.DIAMOND_MORTAR);

		// Add items to creative tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.FLINT_AND_STEEL,
					ModItems.WOODEN_MORTAR,
					ModItems.STONE_MORTAR,
					ModItems.IRON_MORTAR,
					ModItems.DIAMOND_MORTAR
			);
		});
	}
}