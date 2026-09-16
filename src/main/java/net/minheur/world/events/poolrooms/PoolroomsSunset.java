package net.minheur.world.events.poolrooms;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.WorldEvents;
import net.minheur.init.BackroomsLevels;
import net.minheur.init.ModSounds;
import net.minheur.render.PoolroomsDayCycle;
import net.minheur.world.events.AbstractEvent;
import net.minheur.world.levels.custom.PoolroomsBackroomsLevel;
import net.minecraft.world.World;

public class PoolroomsSunset extends AbstractEvent {
    @Override
    public void init(World world) {

        BackroomsLevels.getLevel(world).ifPresent(backroomsLevel -> {
            if (!(backroomsLevel instanceof PoolroomsBackroomsLevel level)) {
                return;
            }

            WorldEvents events = InitializeComponents.EVENTS.get(world);
            float currentDayTime = PoolroomsDayCycle.getDayTime(world);
            level.setSunsetTransitioning(true);
            level.setTimeOfDay(level.getTimeOfDay() + 0.25f);
            events.sync();

            if(currentDayTime == 0.0) playSound(world, ModSounds.SUNSET_TRANSITION);
            else if (currentDayTime == 0.25 || currentDayTime == 0.5) playSound(world, ModSounds.MIDNIGHT_TRANSITION);
            else playSound(world, ModSounds.SUNSET_TRANSITION_END);
        });
    }

    @Override
    public void finish(World world) {
        super.finish(world);

        BackroomsLevels.getLevel(world).ifPresent(backroomsLevel -> {
            if (backroomsLevel instanceof PoolroomsBackroomsLevel level) {
                WorldEvents events = InitializeComponents.EVENTS.get(world);

                level.setTimeOfDay(level.getTimeOfDay() >= 1.0f ? 0.0f : level.getTimeOfDay());
                level.setSunsetTransitioning(false);
                events.sync();
            }
        });
    }

    @Override
    public int duration() {
        return 200;
    }
}
