package net.minheur.world.events.generic.lights;

import net.minheur.init.BackroomsLevels;
import net.minheur.init.ModSounds;
import net.minheur.world.events.AbstractEvent;
import net.minheur.world.levels.BackroomsLevelWithLights;
import net.minecraft.world.World;

public class LightLevelBlackout extends AbstractEvent {
    @Override
    public void init(World world) {
        if (!(BackroomsLevels.getLevel(world).orElse(BackroomsLevels.OVERWORLD_REPRESENTING_BACKROOMS_LEVEL) instanceof BackroomsLevelWithLights level))
            return;

        if (level.getLightState() != BackroomsLevelWithLights.LightState.BLACKOUT) {
            level.setLightState(BackroomsLevelWithLights.LightState.BLACKOUT);
            playSound(world, ModSounds.LIGHTS_OUT);
        }
    }

    @Override
    public void finish(World world) {
        super.finish(world);
        if (!(BackroomsLevels.getLevel(world).orElse(BackroomsLevels.OVERWORLD_REPRESENTING_BACKROOMS_LEVEL) instanceof BackroomsLevelWithLights level))
            return;

        level.setLightState(BackroomsLevelWithLights.LightState.ON);
    }

    @Override
    public int duration() {
        return 20;
    }
}
