package net.rummo.rummomod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rummo.rummomod.RummoGems;

public class ModTags
{
    public static class Blocks
    {
        private static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RummoGems.MOD_ID, name));
        }
    }

    public static class Items
    {
        // Tag Example (Context is the Magical Block):
        // public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        // In resources.data.rummomod.tags.item Create a File Named: transformable_items.json
        /*{
        *   "values": [
        *       "rummomod:raw_black_opal",
        *       "minecraft:coal",
        *       "minecraft:dandelion",
        *       "minecraft:compass"
        *   ]
        * }*/
        // Insert Tag in Item's isValidItem Method
        // private boolean isValidItem(ItemStack item)
        // {
        //    return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
        // }

        private static TagKey<Item> createTag(String name)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RummoGems.MOD_ID, name));
        }
    }
}
