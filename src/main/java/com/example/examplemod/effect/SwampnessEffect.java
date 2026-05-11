package com.example.examplemod.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SwampnessEffect extends MobEffect {

    public SwampnessEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x1d4d1e);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    // ✅ ATUALIZADO 1.21.4: O ServerLevel agora vem direto no parâmetro!
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {

        AABB hitboxRaio6 = entity.getBoundingBox().inflate(6.0);
        List<Monster> monstrosProximos = level.getEntitiesOfClass(Monster.class, hitboxRaio6);

        for (Monster monstro : monstrosProximos) {
            monstro.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10, false, false));
            monstro.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 5, false, false));

            level.sendParticles(ParticleTypes.COMPOSTER,
                    monstro.getX(), monstro.getY() + 1.0, monstro.getZ(),
                    10, 0.5, 0.5, 0.5, 0.0);
        }
        return true;
    }
}