package net.minheur.item.client.renderer;

import net.minheur.item.client.model.GasPumpItemModel;
import net.minheur.item.custom.GasPumpItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GasPumpItemRenderer extends GeoItemRenderer<GasPumpItem> {
    public GasPumpItemRenderer() {
        super(new GasPumpItemModel());
    }
}