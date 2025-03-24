package net.rummo.rummomod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rummo.rummomod.RummoGems;
import net.rummo.rummomod.item.custom.FireStaffItem;
import net.rummo.rummomod.item.custom.FuelItem;
import net.rummo.rummomod.item.custom.RubyRagoutItem;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RummoGems.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.registerSimpleItem("ruby");
    public static final DeferredItem<Item> RAW_RUBY = ITEMS.registerSimpleItem("raw_ruby");
    public static final DeferredItem<Item> RUBY_SHARD = ITEMS.registerSimpleItem("ruby_shard");

    public static final DeferredItem<Item> BLAZING_CORE =
            ITEMS.registerItem("blazing_core",  properties -> new FuelItem(properties, 20000), new Item.Properties());

    public static final DeferredItem<Item> BASALT_BOWL =
            ITEMS.registerItem("basalt_bowl", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RUBY_RAGOUT =
            ITEMS.registerItem("ruby_ragout", RubyRagoutItem::new, new Item.Properties().stacksTo(1).food(ModFoodProperties.RUBY_RAGOUT));

    public static final DeferredItem<Item> FIRE_STAFF =
            ITEMS.registerItem("fire_staff", FireStaffItem::new, new Item.Properties().stacksTo(1).durability(64));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
