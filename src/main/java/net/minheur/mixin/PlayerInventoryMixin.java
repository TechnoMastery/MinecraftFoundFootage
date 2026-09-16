package net.minheur.mixin;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.PlayerComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Inject(method = "scrollInHotbar", at = @At("HEAD"))
    private void isScrolling(double scrollAmount, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();

        if(client.player != null){
            PlayerComponent component = InitializeComponents.PLAYER.get(client.player);
            component.setScrollingInInventoryTime(component.getScrollingInInventoryTime() + 1);
        }

    }

}
