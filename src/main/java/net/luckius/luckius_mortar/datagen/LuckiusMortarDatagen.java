package net.luckius.luckius_mortar.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckius.luckius_mortar.datagen.tag.ModItemTags;
import net.luckius.luckius_mortar.item.ModItems;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LuckiusMortarDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ItemTagGenerator::new);
    }

    private static class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
        public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(ModItemTags.MORTARS)
                    .add(ModItems.WOODEN_MORTAR)
                    .add(ModItems.STONE_MORTAR)
                    .add(ModItems.IRON_MORTAR)
                    .add(ModItems.DIAMOND_MORTAR);
        }
    }
}
