package net.rummo.rummomod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rummo.rummomod.RummoGems;
import net.rummo.rummomod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RummoGems.MOD_ID);

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.AMETHYST).strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(5, 7), BlockBehaviour.Properties.of()
                    .sound(SoundType.DEEPSLATE).strength(6f).requiresCorrectToolForDrops()));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
