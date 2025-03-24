package net.rummo.rummomod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rummo.rummomod.RummoGems;
import net.rummo.rummomod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider
{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, RummoGems.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.RUBY_DEEPSLATE_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
