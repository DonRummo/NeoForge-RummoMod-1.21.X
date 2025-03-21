package net.rummo.rummomod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rummo.rummomod.RummoMod;
import net.rummo.rummomod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RummoMod.MOD_ID);

    public static final Supplier<CreativeModeTab> RUMMO_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("rummo_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.rummomod.rummo_items_tab"))
                    .icon(() -> new ItemStack(ModItems.RAW_RUBY.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RAW_RUBY);
                        pOutput.accept(ModItems.RUBY_SHARD);
                        pOutput.accept(ModItems.RUBY);

                        pOutput.accept(ModItems.FIRE_STAFF);

                    }).build());

    public static final Supplier<CreativeModeTab> RUMMO_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("rummo_blocks_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.rummomod.rummo_blocks_tab"))
                    .icon(() -> new ItemStack(ModBlocks.RUBY_DEEPSLATE_ORE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(RummoMod.MOD_ID, "rummo_items_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.RUBY_BLOCK);
                        pOutput.accept(ModBlocks.RUBY_DEEPSLATE_ORE);
                        
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
