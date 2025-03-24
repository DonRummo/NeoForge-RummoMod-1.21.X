package net.rummo.rummomod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties RUBY_RAGOUT = new FoodProperties.Builder()
            .nutrition(8)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 0), 1.0F)
            .build();
}
