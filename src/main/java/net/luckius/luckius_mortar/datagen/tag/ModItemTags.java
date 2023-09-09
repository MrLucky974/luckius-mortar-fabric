package net.luckius.luckius_mortar.datagen.tag;

import net.luckius.luckius_mortar.LuckiusMortar;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> MORTARS = TagKey.of(RegistryKeys.ITEM, new Identifier(LuckiusMortar.MOD_ID, "mortars"));
}
