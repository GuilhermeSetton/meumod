package com.example.examplemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SwampnessEffect extends MobEffect {

    public SwampnessEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Aplica o efeito a cada tick (20 vezes por segundo)
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Lógica do efeito será implementada aqui
        // Por enquanto, deixamos vazio (será preenchido na Etapa 3)
    }
}