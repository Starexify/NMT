package net.nova.nmt.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.*;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.material.Fluids;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;
import net.nova.nmt.item.ObsidianPotionItem;
import net.nova.nmt.recipe.EnderPotionBrewing;
import net.nova.nmt.recipe.ObsidianTippedArrowRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EmiEntrypoint
public class NMTEmi implements EmiPlugin {
    public static final EmiRecipeCategory ENDER_BREWING = new EmiRecipeCategory(NoMoreThings.rl("ender_brewing"), EmiStack.of(NMTBlocks.ENDER_BREWING_STAND),
            new EmiTexture(id("emi", "textures/gui/widgets.png"), 224, 224, 16, 16), EmiRecipeSorting.none());

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ENDER_BREWING);
        registry.addWorkstation(ENDER_BREWING, EmiStack.of(NMTBlocks.ENDER_BREWING_STAND));

        addWorldInteraction(registry);
        addEnderRecipes(registry);

        for (CraftingRecipe recipe : getRecipes(registry, RecipeType.CRAFTING)) {
            if (recipe instanceof ObsidianTippedArrowRecipe) {
                NMTPotions.POTIONS.getEntries().forEach(entry -> {
                    EmiStack arrow = EmiStack.of(Items.ARROW);
                    addRecipeSafe(registry, () -> new EmiCraftingRecipe(List.of(
                            arrow, arrow, arrow, arrow,
                            EmiStack.of(setPotion(new ItemStack(NMTItems.LINGERING_OBSIDIAN_POTION.get()), entry)),
                            arrow, arrow, arrow, arrow
                    ),
                            EmiStack.of(setPotion(new ItemStack(NMTItems.OBSIDIAN_TIPPED_ARROW.get(), 8), entry)),
                            synthetic("crafting/obsidian_tipped_arrow", subId(entry.getId())),
                            false));
                });
            }
        }
    }

    public static <C extends RecipeInput, T extends Recipe<C>> Iterable<T> getRecipes(EmiRegistry registry, RecipeType<T> type) {
        return registry.getRecipeManager().getAllRecipesFor(type).stream().map(e -> e.value())::iterator;
    }

    public void addEnderRecipes(EmiRegistry registry) {
        EnderPotionBrewing enderBrewing = Minecraft.getInstance().level != null ? NoMoreThings.getEnderBrewing() : EnderPotionBrewing.EMPTY;
        for (Ingredient ingredient : enderBrewing.containers) {
            for (ItemStack stack : ingredient.getItems()) {
                String pid = subId(stack.getItem());
                for (EnderPotionBrewing.Mix<Potion> recipe : enderBrewing.potionMixes) {
                    try {
                        if (recipe.ingredient().getItems().length > 0) {
                            ResourceLocation id = id("emi", "/ender_brewing/" + pid
                                    + "/" + subId(recipe.ingredient().getItems()[0].getItem())
                                    + "/" + subId(recipe.from().getKey().location())
                                    + "/" + subId(recipe.to().getKey().location()));
                            registry.addRecipe(new EmiEnderBrewingRecipe(
                                    EmiStack.of(setPotion(stack.copy(), recipe.from())), EmiIngredient.of(recipe.ingredient()),
                                    EmiStack.of(setPotion(stack.copy(), recipe.to())), id));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (EnderPotionBrewing.Mix<Item> recipe : enderBrewing.containerMixes) {
            try {
                if (recipe.ingredient().getItems().length > 0) {
                    String gid = subId(recipe.ingredient().getItems()[0].getItem());
                    String iid = subId(recipe.from().value());
                    String oid = subId(recipe.to().value());
                    Consumer<Holder<Potion>> potionRecipeGen = entry -> {
                        if (enderBrewing.isBrewablePotion(entry)) {
                            ResourceLocation id = id("emi", "/ender_brewing/item/"
                                    + subId(entry.unwrapKey().get().location()) + "/" + gid + "/" + iid + "/" + oid);
                            registry.addRecipe(new EmiEnderBrewingRecipe(
                                    EmiStack.of(setPotion(new ItemStack(recipe.from().value()), entry)), EmiIngredient.of(recipe.ingredient()),
                                    EmiStack.of(setPotion(new ItemStack(recipe.to().value()), entry)), id));
                        }
                    };
                    if ((recipe.from().value() instanceof ObsidianPotionItem)) {
                        NMTPotions.POTIONS.getEntries().forEach(potionRecipeGen);
                    } else {
                        potionRecipeGen.accept(NMTPotions.AWFULLY);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addWorldInteraction(EmiRegistry registry) {
        addRecipeSafe(registry, () -> basicWorld(EmiStack.of(NMTItems.OBSIDIAN_GLASS_BOTTLE), EmiStack.of(Fluids.LAVA, 1000),
                EmiStack.of(setPotion(new ItemStack(NMTItems.OBSIDIAN_POTION.get()), NMTPotions.LAVA)),
                synthetic("world/unique", "nmt/lava_bottle")));

        EmiStack lavaBottle = EmiStack.of(setPotion(new ItemStack(NMTItems.OBSIDIAN_POTION.get()), NMTPotions.LAVA))
                .setRemainder(EmiStack.of(NMTItems.OBSIDIAN_GLASS_BOTTLE));
        EmiStack magma_block = EmiStack.of(Items.MAGMA_BLOCK);
        addRecipeSafe(registry, () -> basicWorld(EmiStack.of(Items.NETHERRACK), lavaBottle, magma_block, synthetic("world/unique", "minecraft/magma_block"), false));
    }

    public static String subId(ResourceLocation id) {
        return id.getNamespace() + "/" + id.getPath();
    }

    public static String subId(Item item) {
        return subId(BuiltInRegistries.ITEM.getKey(item));
    }

    public static ItemStack setPotion(ItemStack stack, Holder<Potion> potion) {
        stack.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, potion, PotionContents::withPotion);
        return stack;
    }

    public static ResourceLocation id(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation synthetic(String type, String name) {
        return id("emi", "/" + type + "/" + name);
    }

    public static void addRecipeSafe(EmiRegistry registry, Supplier<EmiRecipe> supplier) {
        registry.addRecipe(supplier.get());
    }

    public static EmiRecipe basicWorld(EmiIngredient left, EmiIngredient right, EmiStack output, ResourceLocation id) {
        return basicWorld(left, right, output, id, true);
    }

    public static EmiRecipe basicWorld(EmiIngredient left, EmiIngredient right, EmiStack output, ResourceLocation id, boolean catalyst) {
        return EmiWorldInteractionRecipe.builder()
                .id(id)
                .leftInput(left)
                .rightInput(right, catalyst)
                .output(output)
                .build();
    }
}
