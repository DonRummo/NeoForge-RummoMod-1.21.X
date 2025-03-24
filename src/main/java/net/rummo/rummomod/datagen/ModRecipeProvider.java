package net.rummo.rummomod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rummo.rummomod.RummoGems;
import net.rummo.rummomod.block.ModBlocks;
import net.rummo.rummomod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    // The Recipes are Here
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput)
    {
        List<ItemLike> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY, ModBlocks.RUBY_DEEPSLATE_ORE);

        /* SHAPED RECIPES */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLAZING_CORE.get())
                .pattern("RBR")
                .pattern("BMB")
                .pattern("RBR")
                .define('R', ModItems.RUBY_SHARD.get())
                .define('B', Items.BLAZE_ROD)
                .define('M', Items.MAGMA_CREAM)
                .unlockedBy("has_ruby_shard", has(ModItems.RUBY_SHARD.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get())).save(recipeOutput);

        /* SHAPELESS RECIPES */
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy("has_ruby_block", has(ModBlocks.RUBY_BLOCK.get())).save(recipeOutput);

        /* SMELTABLE RECIPES */
        oreSmelting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.5F, 300, "ruby");
        oreBlasting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.5F, 200, "ruby");
    }

    /* DO NOT TOUCH */
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
            ItemLike result, float experience, int cookingTime, String group)
    {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new,
                ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
            ItemLike result, float experience, int cookingTime, String group)
    {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new,
                ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
            RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients,
            RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix)
    {
        for (ItemLike itemlike : ingredients)
        {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, RummoGems.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }
}
