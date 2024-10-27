package net.nova.nmt.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.nova.nmt.init.NMTBlocks;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipes extends NMTRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CraftingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NMTBlocks.OBSIDIAN_GLASS)
                .group(getItemName(NMTBlocks.OBSIDIAN_GLASS))
                .define('#', Items.OBSIDIAN)
                .define('O', Tags.Items.GLASS_BLOCKS_CHEAP)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .unlockedBy("has_" + getItemName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(recipeOutput);

        stainedGlassPaneFromStainedGlass(recipeOutput, NMTBlocks.OBSIDIAN_GLASS_PANE, NMTBlocks.OBSIDIAN_GLASS);
    }
}