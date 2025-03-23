package net.rummo.rummomod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties CHEESE_WEDGE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.45F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 0), 0.025F)
            .build();
}
