package net.rummo.rummomod.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;
import net.rummo.rummomod.item.ModItems;

public class RubyRagoutItem extends SuspiciousStewItem
{

    public RubyRagoutItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving)
    {
        super.finishUsingItem(stack, level, entityLiving);

        if (stack.isEmpty())
        {
            return new ItemStack(ModItems.BASALT_BOWL.get());
        } else {
            if (entityLiving instanceof Player player && !player.getAbilities().instabuild)
            {
                player.getInventory().add(new ItemStack(ModItems.BASALT_BOWL.get()));
            }
            return stack;
        }
    }
}
