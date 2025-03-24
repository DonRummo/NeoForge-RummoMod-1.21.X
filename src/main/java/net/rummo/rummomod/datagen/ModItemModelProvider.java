package net.rummo.rummomod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rummo.rummomod.RummoGems;
import net.rummo.rummomod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, RummoGems.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(ModItems.RAW_RUBY.get());
        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.RUBY_SHARD.get());

        basicItem(ModItems.BASALT_BOWL.get());
        basicItem(ModItems.RUBY_RAGOUT.get());

        basicItem(ModItems.BLAZING_CORE.get());

        basicItem(ModItems.FIRE_STAFF.get());
    }
}
