package net.minheur.sounds;

import net.minheur.SPBRevampedClient;
import net.minheur.block.custom.FluorescentLightBlock;
import net.minheur.block.entity.FluorescentLightBlockEntity;
import net.minheur.init.BackroomsLevels;
import net.minheur.init.ModBlocks;
import net.minheur.init.ModSounds;
import net.minheur.world.levels.BackroomsLevelWithLights;
import net.minheur.world.levels.custom.Level0BackroomsLevel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class FluorescentLightSoundInstance extends MovingSoundInstance {
    private final BlockEntity entity;
    private final PlayerEntity player;

    public FluorescentLightSoundInstance(BlockEntity entity, PlayerEntity player) {
        super(ModSounds.FLUORESCENT_LIGHT_HUM, SoundCategory.BLOCKS, SoundInstance.createRandom());
        this.x = (float) entity.getPos().toCenterPos().x;
        this.y = (float) entity.getPos().toCenterPos().y - 0.5;
        this.z = (float) entity.getPos().toCenterPos().z;
        this.entity = entity;
        this.player = player;
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public void tick() {
        World world = this.entity.getWorld();

        if (!((BackroomsLevels.getLevel(player.getWorld()).orElse(BackroomsLevels.OVERWORLD_REPRESENTING_BACKROOMS_LEVEL)) instanceof Level0BackroomsLevel level)) {
            return;
        }

        if(world != null) {
            if (!this.entity.isRemoved() &&
                this.entity.getPos().isWithinDistance(player.getPos(), 16.0f) &&
                level.getLightState() != BackroomsLevelWithLights.LightState.BLACKOUT &&
                ((FluorescentLightBlockEntity) entity).getCurrentState() == ModBlocks.FLUORESCENT_LIGHT.getDefaultState().with(FluorescentLightBlock.ON, true) &&
                !((FluorescentLightBlockEntity) entity).getCurrentState().get(FluorescentLightBlock.BLACKOUT) &&
                !SPBRevampedClient.blackScreen)
            {
                this.pitch = 1.0F;
                this.volume = 0.4F;
            } else {
                this.setDone();
                ((FluorescentLightBlockEntity) entity).setPlayingSound(false);
            }
        }
    }
}
