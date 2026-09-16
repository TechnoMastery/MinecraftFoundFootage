package net.minheur.mixin.renderlayer;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minheur.render.RenderLayers;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(RenderLayer.class)
public class RenderLayerMixin {

    @ModifyReturnValue(method = "getBlockLayers", at = @At("RETURN"))
    private static List<RenderLayer> addRenderLayer(List<RenderLayer> original){
        List<RenderLayer> list = new ArrayList<>(original);
        list.add(RenderLayers.getPoolroomsSky());
        list.add(RenderLayers.getPbrLayer());
        return list;
    }

}
