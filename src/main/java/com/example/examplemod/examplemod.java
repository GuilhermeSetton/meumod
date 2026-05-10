package com.example.examplemod;

import com.example.examplemod.block.SwampEyeBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


import java.util.function.Supplier;

@Mod(examplemod.MODID)
public final class examplemod {
    public static final String MODID = "examplemod";

    // DeferredRegisters - SEMPRE use createBlocks() e createItems()
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Registrar blocos - retorna Supplier<Block>
    // setId() é OBRIGATÓRIO no NeoForge 1.21.4+ para evitar "block id not set"
    public static final Supplier<Block> SWAMP_EYE = BLOCKS.register("swamp_eye",
            () -> new SwampEyeBlock(Block.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_eye")))
                    .strength(1.0f)              // Dureza (tempo para quebrar)
                    .lightLevel(state -> 15)));  // Nível de luz (0-15)

    // Registrar BlockItem - retorna Supplier<Item>
    public static final Supplier<Item> SWAMP_EYE_ITEM = ITEMS.register("swamp_eye",
            () -> new BlockItem(SWAMP_EYE.get(), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_eye")))));
    public static final Supplier<Item> SWAMP_FRUIT = ITEMS.register("swamp_fruit",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_fruit")))
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.3f)
                            .alwaysEdible()
                            .build())));
    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.examplemod"))
                    .icon(() -> SWAMP_EYE_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(SWAMP_EYE_ITEM.get());
                        output.accept(SWAMP_FRUIT.get());
                    })
                    .build());

    // Construtor - OBRIGATÓRIO receber IEventBus
    public examplemod(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}