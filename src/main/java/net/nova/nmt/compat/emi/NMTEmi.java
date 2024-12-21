package net.nova.nmt.compat.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.material.Fluids;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;
import net.nova.nmt.recipe.EnderPotionBrewing;
import net.nova.nmt.recipe.ObsidianTippedArrowRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EmiEntrypoint
public class NMTEmi implements EmiPlugin {
    public static final EmiRecipeCategory ENDER_BREWING = new EmiRecipeCategory(NoMoreThings.rl("ender_brewing"), EmiStack.of(NMTBlocks.ENDER_BREWING_STAND),
            new EmiTexture(NoMoreThings.rl("textures/gui/emi_simplified_textures.png"), 0, 0, 16, 16));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ENDER_BREWING);
        registry.addWorkstation(ENDER_BREWING, EmiStack.of(NMTBlocks.ENDER_BREWING_STAND));

        addEnderRecipes(registry);
        addWorldInteraction(registry);
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
                                    + "/" + subId(BuiltInRegistries.POTION.getKey(recipe.from().value()))
                                    + "/" + subId(BuiltInRegistries.POTION.getKey(recipe.to().value())));
                            registry.addRecipe(new EmiEnderBrewingRecipe(
                                    EmiStack.of(setPotion(stack.copy(), recipe.from().value())), EmiIngredient.of(recipe.ingredient()),
                                    EmiStack.of(setPotion(stack.copy(), recipe.to().value())), id));
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
                        Potion potion = entry.value();
                        if (enderBrewing.isBrewablePotion(entry)) {
                            ResourceLocation id = id("emi", "/ender_brewing/item/"
                                    + subId(entry.unwrapKey().get().location()) + "/" + gid + "/" + iid + "/" + oid);
                            registry.addRecipe(new EmiEnderBrewingRecipe(
                                    EmiStack.of(setPotion(new ItemStack(recipe.from().value()), potion)), EmiIngredient.of(recipe.ingredient()),
                                    EmiStack.of(setPotion(new ItemStack(recipe.to().value()), potion)), id));
                        }
                    };
                    if ((recipe.from().value() instanceof PotionItem)) {
                        BuiltInRegistries.POTION.holders().forEach(potionRecipeGen);
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
                EmiStack.of(setPotion(new ItemStack(NMTItems.OBSIDIAN_POTION.get()), NMTPotions.LAVA.value())),
                synthetic("world/unique", "nmt/lava_bottle")));

        EmiStack lavaBottle = EmiStack.of(setPotion(new ItemStack(NMTItems.OBSIDIAN_POTION.get()), NMTPotions.LAVA.value()))
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

    public static ItemStack setPotion(ItemStack stack, Potion potion) {
        stack.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, BuiltInRegistries.POTION.wrapAsHolder(potion), PotionContents::withPotion);
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
