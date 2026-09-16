package net.minheur.entity.ai.goals;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.SkinWalkerComponent;
import net.minheur.entity.custom.SkinWalkerEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

public class SkinWalkerActiveTarget extends ActiveTargetGoal<PlayerEntity> {
    private final SkinWalkerComponent component;

    public SkinWalkerActiveTarget(SkinWalkerEntity entity) {
        super(entity, PlayerEntity.class, false);
        this.component = InitializeComponents.SKIN_WALKER.get(entity);
    }

    @Override
    public boolean canStart() {
        if(!this.component.isInTrueForm() && !this.component.shouldBeginReveal()) {
            return super.canStart();
        }

        return false;
    }
}
