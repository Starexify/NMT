package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.recipe.ObsidianTippedArrowRecipe;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MODID);

    public static final Supplier<RecipeSerializer<ObsidianTippedArrowRecipe>> OBSIDIAN_TIPPED_ARROW = RECIPE_SERIALIZERS.register("crafting_special_obsidiantippedarrow", () -> new CustomRecipe.Serializer<>(ObsidianTippedArrowRecipe::new));
}
