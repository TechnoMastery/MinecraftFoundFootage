package net.minheur.mixininterfaces;

import net.minecraft.client.option.SimpleOption;

public interface VideoModeOptionAccessor {

    SimpleOption<Integer> getNormalVideoMode();
    SimpleOption<Integer> getVHSVVideoMode();

}
