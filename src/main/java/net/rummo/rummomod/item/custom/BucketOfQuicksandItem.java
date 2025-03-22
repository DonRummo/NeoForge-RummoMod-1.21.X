package net.rummo.rummomod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.rummo.rummomod.block.ModBlocks;

public class BucketOfQuicksandItem extends BucketItem {

    public BucketOfQuicksandItem(Block block, Properties properties) {
        super(Fluids.EMPTY, properties);  // No fluid, it's handled like powdered snow
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Check if the block under the player is suitable for placing quicksand
        if (!level.isClientSide)
        {
            if (level.isEmptyBlock(pos.above()))
            {  // Check if the above space is free
                level.setBlock(pos.above(), ModBlocks.QUICKSAND.get().defaultBlockState(), 11);  // Place quicksand above the player

                // If the player is in creative mode, consume the bucket
                if (!player.isCreative())
                {
                    context.getItemInHand().shrink(1);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.FAIL;  // Return failure if the block is not placed
    }
}