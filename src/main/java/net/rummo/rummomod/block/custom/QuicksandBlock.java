package net.rummo.rummomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.rummo.rummomod.item.ModItems;
import net.rummo.rummomod.item.custom.BucketOfQuicksandItem;

import javax.annotation.Nullable;

public class QuicksandBlock extends PowderSnowBlock
{

    public QuicksandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            Vec3 motion = entity.getDeltaMovement();
            entity.setDeltaMovement(motion.x * 0.4, motion.y * 0.5, motion.z * 0.4);
            entity.hurtMarked = true;
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);

        if (!level.isClientSide)
        {
            // If player is holding a bucket and destroys the quicksand block, place the bucket of quicksand
            if (player.getMainHandItem().getItem() instanceof BucketOfQuicksandItem)
            {
                level.setBlock(pos,Blocks.AIR.defaultBlockState(), 3); // Remove the block (set it w/ air)

                // Give the player a bucket of quicksand
                if (!player.getInventory().add(new ItemStack(ModItems.QUICKSAND_BUCKET.get())))
                {
                    player.drop(new ItemStack(ModItems.QUICKSAND_BUCKET.get()), false);
                }
            }
        }
        return willHarvest;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState();
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state)
    {
        return true;
    }
}
