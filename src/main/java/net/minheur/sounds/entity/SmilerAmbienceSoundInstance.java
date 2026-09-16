package net.minheur.sounds.entity;

import net.minheur.init.BackroomsLevels;
import net.minheur.init.ModSounds;
import net.minheur.world.levels.BackroomsLevelWithLights;
import net.minheur.world.levels.custom.Level1BackroomsLevel;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;

public class SmilerAmbienceSoundInstance extends MovingSoundInstance {
    private final PlayerEntity player;

    public SmilerAmbienceSoundInstance(PlayerEntity player) {
        super(ModSounds.SMILER_AMBIENCE, SoundCategory.AMBIENT, SoundInstance.createRandom());
        this.player = player;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.5F;
        this.relative = true;
    }

    @Override
    public void tick() {
        if (!((BackroomsLevels.getLevel(player.getWorld()).orElse(BackroomsLevels.OVERWORLD_REPRESENTING_BACKROOMS_LEVEL)) instanceof Level1BackroomsLevel level)) {
            return;
        }

        if(level.getLightState() != BackroomsLevelWithLights.LightState.BLACKOUT || this.player.isRemoved()){
            this.setDone();
        }
    }
}
