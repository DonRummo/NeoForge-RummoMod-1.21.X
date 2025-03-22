package net.rummo.rummomod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FireStaffItem extends Item
{
    private static final int CHARGE_DURATION = 80; // How long to charge before the slam ability activates
    private static final float SHOCKWAVE_RADIUS = 8.0F; // Radius of the shockwave effect
    private static final double KNOCKBACK_STRENGTH = 5.5D; // Knockback strength applied to entities
    private static final float FIREBALL_DAMAGE = 5.0F; // Damage dealt by the fireball

    public FireStaffItem(Properties properties) {
        super(properties);
    }

    private boolean isCharging = false; // Track if the player is charging
    private int chargeTime = 0; // Keep track of the charge time

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand)
    {
        ItemStack stack = player.getItemInHand(usedHand);

        if(!level.isClientSide)
        {
            if(player.isCrouching()) // Start charging the slam ability if the player is crouching
            {
                isCharging = true;
                chargeTime = 0;
                player.startUsingItem(usedHand); // Begin using Staff

                // Play the charging sound
                BlockPos playerPos = player.blockPosition();
                level.playSound(null, playerPos, SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);

            } else { // Otherwise, shoot fireball
                shootFireball(level, player);
            }
        }
        return InteractionResultHolder.success(stack);
    }

    // This method runs every tick the item is used (when the player is holding it)
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (isCharging)
        {
            chargeTime++; // Increase charge time while charging
            System.out.println("Charge Time: " + chargeTime); // Debugging log
        }
    }

    private void shootFireball(Level level, Player player)
    {
        // Get the player's look direction
        Vec3 lookVector = player.getLookAngle();

        // Adjust the fireball's starting position to be a bit in front of the player's hand
        Vec3 handPosition = player.getEyePosition(1.0F).add(lookVector.x * 1.5D, lookVector.y * 1.5D, lookVector.z * 1.5D);

        // Create a new small fireball entity at the calculated position
        SmallFireball fireball = new SmallFireball(level, player, lookVector);

        // Set the fireball's position to the hand position
        fireball.setPos(handPosition.x, handPosition.y, handPosition.z);

        // Add the fireball to the world
        level.addFreshEntity(fireball);

        // Play a shooting sound
        level.playSound(null, player.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    // Displays a bow-like charging animation.
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW; // MAKE SURE TO CHANGE TO NEW STAFF ANIMATION
    }

    // Defines the time needed to fully charge staff
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return CHARGE_DURATION;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged)
    {
        if (!level.isClientSide && livingEntity instanceof Player player)
        {
            if (isCharging)
            {
                System.out.println("timeCharged: " + timeCharged + ", CHARGE_DURATION: " + CHARGE_DURATION); // Debugging log

                // If the charge time exceeds the duration, trigger the shockwave
                if (chargeTime >= CHARGE_DURATION)
                {
                    System.out.println("Shockwave triggered!"); // Debug log
                    triggerShockwave(level, player); // Trigger the shockwave
                }
                else
                {
                    System.out.println("Charge not long enough!"); // Debug log
                }

                // After releasing, reset the charging flag and time
                isCharging = false; // Stop charging
                chargeTime = 0; // Reset charge time
            }
        }
    }

    private void triggerShockwave(Level level, Player player)
    {
        Vec3 position = player.position();

        // Define the area of effect for the shockwave
        AABB areaOfEffect = new AABB(position.x - SHOCKWAVE_RADIUS, position.y - 1, position.z - SHOCKWAVE_RADIUS,
                position.x + SHOCKWAVE_RADIUS, position.y + 1, position.z + SHOCKWAVE_RADIUS);

        // Get all entities within the AOE
        List<Entity> entities = level.getEntities(player, areaOfEffect);
        for(Entity entity : entities)
        {
            if(entity instanceof LivingEntity target)
            {
                double distance = player.distanceTo(target);
                if(distance <= SHOCKWAVE_RADIUS)
                {
                    // Calculate knockback strength based on distance from the player
                    double knockback = KNOCKBACK_STRENGTH * (1.0 - (distance / SHOCKWAVE_RADIUS));
                    Vec3 knockbackDirection = target.position().subtract(position).normalize().scale(knockback);

                    // Apply knockback and set the target on fire
                    target.setDeltaMovement(knockbackDirection);
                    target.setRemainingFireTicks(100); // 100 ticks = 5 seconds on fire

                    target.hurt(player.damageSources().magic(), 10.F);
                }
            }
        }

        // Play sound to indicate the shockwave activation
        level.playSound(null, position.x, position.y, position.z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.5F, 0.8F);
    }
}
