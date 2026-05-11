package com.example.examplemod.item.client;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SwampFruitRenderer extends GeoItemRenderer<SwampFruitItem> {
    public SwampFruitRenderer() {
        super(new SwampFruitModel());
    }
}