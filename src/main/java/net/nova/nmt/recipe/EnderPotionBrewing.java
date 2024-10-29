package net.nova.nmt.recipe;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;
import net.nova.nmt.item.ObsidianPotionItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderPotionBrewing {
    public static final int BREWING_TIME_SECONDS = 20;
    public static final EnderPotionBrewing EMPTY = new EnderPotionBrewing(List.of(), List.of(), List.of());
    private final List<Ingredient> containers;
    private final List<EnderPotionBrewing.Mix<Potion>> potionMixes;
    private final List<EnderPotionBrewing.Mix<Item>> containerMixes;
    private final BrewingRecipeRegistry registry;

    EnderPotionBrewing(List<Ingredient> containers, List<EnderPotionBrewing.Mix<Potion>> potionMixes, List<EnderPotionBrewing.Mix<Item>> containerMixes) {
        this(containers, potionMixes, containerMixes, List.of());
    }

    EnderPotionBrewing(List<Ingredient> containers, List<EnderPotionBrewing.Mix<Potion>> potionMixes, List<EnderPotionBrewing.Mix<Item>> containerMixes, List<net.neoforged.neoforge.common.brewing.IBrewingRecipe> recipes) {
        this.containers = containers;
        this.potionMixes = potionMixes;
        this.containerMixes = containerMixes;
        this.registry = new BrewingRecipeRegistry(recipes);
    }

    public boolean isIngredient(ItemStack stack) {
        return this.registry.isValidIngredient(stack) || this.isContainerIngredient(stack) || this.isPotionIngredient(stack);
    }

    public boolean isInput(ItemStack stack) {
        return this.registry.isValidInput(stack) || isContainer(stack);
    }

    public List<IBrewingRecipe> getRecipes() {
        return registry.recipes();
    }

    private boolean isContainer(ItemStack stack) {
        for (Ingredient ingredient : this.containers) {
            if (ingredient.test(stack)) {
                return true;
            }
        }

        return false;
    }

    public boolean isContainerIngredient(ItemStack stack) {
        for (EnderPotionBrewing.Mix<Item> mix : this.containerMixes) {
            if (mix.ingredient.test(stack)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPotionIngredient(ItemStack stack) {
        for (EnderPotionBrewing.Mix<Potion> mix : this.potionMixes) {
            if (mix.ingredient.test(stack)) {
                return true;
            }
        }

        return false;
    }

    public boolean isBrewablePotion(Holder<Potion> potion) {
        for (EnderPotionBrewing.Mix<Potion> mix : this.potionMixes) {
            if (mix.to.is(potion)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasMix(ItemStack reagent, ItemStack potionItem) {
        if (registry.hasOutput(reagent, potionItem)) return true;
        return !this.isContainer(reagent) ? false : this.hasContainerMix(reagent, potionItem) || this.hasPotionMix(reagent, potionItem);
    }

    public boolean hasContainerMix(ItemStack reagent, ItemStack potionItem) {
        for (EnderPotionBrewing.Mix<Item> mix : this.containerMixes) {
            if (reagent.is(mix.from) && mix.ingredient.test(potionItem)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPotionMix(ItemStack reagent, ItemStack potionItem) {
        Optional<Holder<Potion>> optional = reagent.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
        if (optional.isEmpty()) {
            return false;
        } else {
            for (EnderPotionBrewing.Mix<Potion> mix : this.potionMixes) {
                if (mix.from.is(optional.get()) && mix.ingredient.test(potionItem)) {
                    return true;
                }
            }

            return false;
        }
    }

    public ItemStack mix(ItemStack potion, ItemStack potionItem) {
        if (potionItem.isEmpty()) {
            return potionItem;
        } else {
            var customMix = registry.getOutput(potionItem, potion);
            if (!customMix.isEmpty()) return customMix;
            Optional<Holder<Potion>> optional = potionItem.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
            if (optional.isEmpty()) {
                return potionItem;
            } else {
                for (EnderPotionBrewing.Mix<Item> mix : this.containerMixes) {
                    if (potionItem.is(mix.from) && mix.ingredient.test(potion)) {
                        return PotionContents.createItemStack(mix.to.value(), optional.get());
                    }
                }

                for (EnderPotionBrewing.Mix<Potion> mix1 : this.potionMixes) {
                    if (mix1.from.is(optional.get()) && mix1.ingredient.test(potion)) {
                        return PotionContents.createItemStack(potionItem.getItem(), mix1.to);
                    }
                }

                return potionItem;
            }
        }
    }

    public static EnderPotionBrewing bootstrap(FeatureFlagSet enabledFeatures) {
        EnderPotionBrewing.Builder potionbrewing$builder = new EnderPotionBrewing.Builder(enabledFeatures);
        addEnderMixes(potionbrewing$builder);
        return potionbrewing$builder.build();
    }

    public static void addEnderMixes(EnderPotionBrewing.Builder builder) {
        builder.addContainer(NMTItems.LAVA_BOTTLE.get());
        builder.addMix(NMTPotions.LAVA, Items.NETHER_WART, NMTPotions.AWFULLY);
        builder.addContainerRecipe(NMTItems.LAVA_BOTTLE.get(), Items.NETHER_WART, NMTItems.AWFULLY_POTION.get());
    }

    public static class Builder {
        private final List<Ingredient> containers = new ArrayList<>();
        private final List<EnderPotionBrewing.Mix<Potion>> potionMixes = new ArrayList<>();
        private final List<EnderPotionBrewing.Mix<Item>> containerMixes = new ArrayList<>();
        private final List<net.neoforged.neoforge.common.brewing.IBrewingRecipe> recipes = new ArrayList<>();
        private final FeatureFlagSet enabledFeatures;

        public Builder(FeatureFlagSet enabledFeatures) {
            this.enabledFeatures = enabledFeatures;
        }

        private static void expectPotion(Item item) {
            if (!(item instanceof ObsidianPotionItem)) {
                throw new IllegalArgumentException("Expected a potion, got: " + BuiltInRegistries.ITEM.getKey(item));
            }
        }

        public void addContainerRecipe(Item input, Item reagent, Item result) {
            if (input.isEnabled(this.enabledFeatures) && reagent.isEnabled(this.enabledFeatures) && result.isEnabled(this.enabledFeatures)) {
                expectPotion(input);
                expectPotion(result);
                this.containerMixes
                        .add(new EnderPotionBrewing.Mix<>(input.builtInRegistryHolder(), Ingredient.of(reagent), result.builtInRegistryHolder()));
            }
        }

        public void addContainer(Item container) {
            if (container.isEnabled(this.enabledFeatures)) {
                expectPotion(container);
                this.containers.add(Ingredient.of(container));
            }
        }

        public void addMix(Holder<Potion> input, Item reagent, Holder<Potion> result) {
            if (input.value().isEnabled(this.enabledFeatures)
                    && reagent.isEnabled(this.enabledFeatures)
                    && result.value().isEnabled(this.enabledFeatures)) {
                this.potionMixes.add(new EnderPotionBrewing.Mix<>(input, Ingredient.of(reagent), result));
            }
        }

        public void addStartMix(Item reagent, Holder<Potion> result) {
            if (result.value().isEnabled(this.enabledFeatures)) {
                this.addMix(NMTPotions.AWFULLY, reagent, result);
            }
        }

        public EnderPotionBrewing build() {
            return new EnderPotionBrewing(List.copyOf(this.containers), List.copyOf(this.potionMixes), List.copyOf(this.containerMixes), List.copyOf(this.recipes));
        }
    }

    static record Mix<T>(Holder<T> from, Ingredient ingredient, Holder<T> to) {
    }
}