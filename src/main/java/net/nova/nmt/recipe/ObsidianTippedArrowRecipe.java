package net.nova.nmt.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTRecipeSerializers;

public class ObsidianTippedArrowRecipe extends CustomRecipe {
    public ObsidianTippedArrowRecipe(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput input, Level level) {
        if (input.width() == 3 && input.height() == 3) {
            for (int i = 0; i < input.height(); i++) {
                for (int j = 0; j < input.width(); j++) {
                    ItemStack itemstack = input.getItem(j, i);
                    if (itemstack.isEmpty()) {
                        return false;
                    }

                    if (j == 1 && i == 1) {
                        if (!itemstack.is(NMTItems.LINGERING_OBSIDIAN_POTION)) {
                            return false;
                        }
                    } else if (!itemstack.is(Items.ARROW)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack itemstack = input.getItem(1, 1);
        if (!itemstack.is(NMTItems.LINGERING_OBSIDIAN_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemstack1 = new ItemStack(NMTItems.OBSIDIAN_TIPPED_ARROW.get(), 8);
            itemstack1.set(DataComponents.POTION_CONTENTS, itemstack.get(DataComponents.POTION_CONTENTS));
            return itemstack1;
        }
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NMTRecipeSerializers.OBSIDIAN_TIPPED_ARROW.get();
    }
}
