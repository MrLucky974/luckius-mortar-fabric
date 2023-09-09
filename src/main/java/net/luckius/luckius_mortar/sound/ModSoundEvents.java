package net.luckius.luckius_mortar.sound;

import net.luckius.luckius_mortar.LuckiusMortar;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final Identifier MORTAR_SOUND_CLICK_ID = new Identifier(LuckiusMortar.MOD_ID, "mortar_click_craft");
    public static SoundEvent MORTAR_SOUND_CLICK = SoundEvent.of(MORTAR_SOUND_CLICK_ID);
}
