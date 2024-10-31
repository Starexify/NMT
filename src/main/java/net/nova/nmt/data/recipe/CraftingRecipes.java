package net.nova.nmt.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.TippedArrowRecipe;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.recipe.ObsidianTippedArrowRecipe;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipes extends NMTRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CraftingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        // Ender Wart
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NMTBlocks.ENDER_WART_BLOCK, NMTItems.ENDER_WART);

        // Brewing Stand
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, NMTBlocks.ENDER_BREWING_STAND)
                .define('B', Items.END_ROD)
                .define('S', Items.BREWING_STAND)
                .define('#', Items.OBSIDIAN)
                .pattern(" B ")
                .pattern(" S ")
                .pattern("###")
                .unlockedBy("has_" + getItemName(Items.BREWING_STAND), has(Items.BREWING_STAND))
                .save(recipeOutput);

        // Obsidian Glass
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

        // Obsidian Bottle
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, NMTItems.OBSIDIAN_GLASS_BOTTLE, 3)
                .define('#', NMTBlocks.OBSIDIAN_GLASS)
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_" + getItemName(NMTBlocks.OBSIDIAN_GLASS), has(NMTBlocks.OBSIDIAN_GLASS))
                .save(recipeOutput);

        // Tipped Arrows
        SpecialRecipeBuilder.special(ObsidianTippedArrowRecipe::new).save(recipeOutput, NoMoreThings.rl("obsidian_tipped_arrow"));
    }
}