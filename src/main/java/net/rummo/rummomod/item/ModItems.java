package net.rummo.rummomod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rummo.rummomod.RummoMod;
import net.rummo.rummomod.item.custom.FireStaffItem;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RummoMod.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.registerSimpleItem("ruby");
    public static final DeferredItem<Item> RAW_RUBY = ITEMS.registerSimpleItem("raw_ruby");
    public static final DeferredItem<Item> UNCUT_RUBY = ITEMS.registerSimpleItem("uncut_ruby");
    public static final DeferredItem<Item> RUBY_SHARD = ITEMS.registerSimpleItem("ruby_shard");

    public static final DeferredItem<Item> FIRE_STAFF =
            ITEMS.registerItem("fire_staff", FireStaffItem::new, new Item.Properties().durability(64));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
