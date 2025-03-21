package net.rummo.rummomod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rummo.rummomod.RummoMod;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RummoMod.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.registerSimpleItem("ruby");
    public static final DeferredItem<Item> RAW_RUBY =
            ITEMS.registerItem("raw_ruby", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RUBY_SHARD = ITEMS.registerSimpleItem("ruby_shard");

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
