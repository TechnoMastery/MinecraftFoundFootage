package net.minheur.entity.ai.goals;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.SkinWalkerComponent;
import net.minheur.entity.custom.SkinWalkerEntity;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;

public class FinalFormWanderGoal extends WanderAroundFarGoal {
    private final SkinWalkerComponent component;

    public FinalFormWanderGoal(SkinWalkerEntity entity, double d) {
        super(entity, d);
        this.component = InitializeComponents.SKIN_WALKER.get(entity);
    }

    @Override
    public boolean canStart() {
        return super.canStart() && component.isInTrueForm() && component.isIdle();
    }
}
