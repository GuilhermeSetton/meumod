package com.example.examplemod.item.client;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

// Usar DefaultedItemGeoModel salva muito código!
public class SwampFruitModel extends DefaultedItemGeoModel<SwampFruitItem> {
    public SwampFruitModel() {
        // Ele vai automaticamente procurar na pasta geo/item/, textures/item/, etc.
        super(ResourceLocation.fromNamespaceAndPath("examplemod", "swamp_fruit"));
    }
}