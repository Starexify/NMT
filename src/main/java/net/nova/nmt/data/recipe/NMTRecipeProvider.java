package net.nova.nmt.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class NMTRecipeProvider extends RecipeProvider {
    protected NMTRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }


    @Override
    protected void buildRecipes() {
        new CraftingRecipes(registries, output).build();
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new NMTRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "Big Swords R Recipes";
        }
    }
}
