package net.minheur.world.events;

import net.minecraft.world.World;

public class EmptyEvent extends AbstractEvent {
    @Override
    public void init(World world) {

        done = true;
    }

    @Override
    public int duration() {
        return 0;
    }
}
