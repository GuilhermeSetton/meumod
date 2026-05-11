package com.example.examplemod;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import com.example.examplemod.block.SwampEyeBlock;
import com.example.examplemod.item.client.SwampFruitItem;
import com.example.examplemod.effect.SwampnessEffect; // Import do seu efeito
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder; // Novo Import do Holder
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffect;
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

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);

    // ✅ REGISTRADO ANTES: Para o Java ler e saber que ele existe
    // ✅ DeferredHolder: É a exigência da 1.21.4
    public static final DeferredHolder<MobEffect, SwampnessEffect> SWAMPNESS = MOB_EFFECTS.register("swampness", SwampnessEffect::new);

    public static final Supplier<Block> SWAMP_EYE = BLOCKS.register("swamp_eye",
            () -> new SwampEyeBlock(Block.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_eye")))
                    .strength(1.0f)
                    .lightLevel(state -> 15)));

    public static final Supplier<Item> SWAMP_EYE_ITEM = ITEMS.register("swamp_eye",
            () -> new BlockItem(SWAMP_EYE.get(), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_eye")))));

    // ✅ FRUTA REGISTRADA DEPOIS: Agora ela pode achar o efeito!
    // ✅ FRUTA REGISTRADA DEPOIS: Agora ela pode achar o efeito!
    public static final Supplier<Item> SWAMP_FRUIT = ITEMS.register("swamp_fruit",
            () -> new SwampFruitItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "swamp_fruit")))
                    .food(
                            // 1º Parâmetro: Propriedades da Comida (fome e saturação)
                            new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(0.3f)
                                    .alwaysEdible()
                                    .build(),
                            // 2º Parâmetro: O novo componente "Consumable" para os Efeitos
                            Consumables.defaultFood()
                                    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(SWAMPNESS, 600, 0), 1.0f))
                                    .build()
                    )
            ));

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.examplemod"))
                    .icon(() -> SWAMP_EYE_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(SWAMP_EYE_ITEM.get());
                        output.accept(SWAMP_FRUIT.get());
                    })
                    .build());

    public examplemod(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
    }
}