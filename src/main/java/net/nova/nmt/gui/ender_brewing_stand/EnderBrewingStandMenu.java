package net.nova.nmt.gui.ender_brewing_stand;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.event.EventHooks;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTMenuType;
import net.nova.nmt.recipe.EnderPotionBrewing;

import java.util.Optional;

public class EnderBrewingStandMenu extends AbstractContainerMenu {
    private final Container brewingStand;
    private final ContainerData brewingStandData;
    private final Slot ingredientSlot;

    public EnderBrewingStandMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(5), new SimpleContainerData(2));
    }

    public EnderBrewingStandMenu(int containerId, Inventory playerInventory, Container brewingStandContainer, ContainerData brewingStandData) {
        super(NMTMenuType.ENDER_BREWING_STAND.get(), containerId);
        checkContainerSize(brewingStandContainer, 5);
        checkContainerDataCount(brewingStandData, 2);
        this.brewingStand = brewingStandContainer;
        this.brewingStandData = brewingStandData;
        EnderPotionBrewing potionbrewing = NoMoreThings.getEnderBrewing();

        this.addSlot(new EnderBrewingStandMenu.PotionSlot(potionbrewing, brewingStandContainer, 0, 56, 51));
        this.addSlot(new EnderBrewingStandMenu.PotionSlot(potionbrewing, brewingStandContainer, 1, 79, 58));
        this.addSlot(new EnderBrewingStandMenu.PotionSlot(potionbrewing, brewingStandContainer, 2, 102, 51));
        this.ingredientSlot = this.addSlot(new EnderBrewingStandMenu.IngredientsSlot(potionbrewing, brewingStandContainer, 3, 79, 17));
        this.addSlot(new EnderBrewingStandMenu.FuelSlot(brewingStandContainer, 4, 17, 17));
        this.addDataSlots(brewingStandData);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.brewingStand.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if ((index < 0 || index > 2) && index != 3 && index != 4) {
                if (EnderBrewingStandMenu.FuelSlot.mayPlaceItem(itemstack)) {
                    if (this.moveItemStackTo(itemstack1, 4, 5, false)
                            || this.ingredientSlot.mayPlace(itemstack1) && !this.moveItemStackTo(itemstack1, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.ingredientSlot.mayPlace(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (EnderBrewingStandMenu.PotionSlot.mayPlaceItem(NoMoreThings.getEnderBrewing(), itemstack)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 5 && index < 32) {
                    if (!this.moveItemStackTo(itemstack1, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 32 && index < 41) {
                    if (!this.moveItemStackTo(itemstack1, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack);
        }

        return itemstack;
    }

    public int getFuel() {
        return this.brewingStandData.get(1);
    }

    public int getBrewingTicks() {
        return this.brewingStandData.get(0);
    }

    static class FuelSlot extends Slot {
        public FuelSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return mayPlaceItem(stack);
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.is(Items.WIND_CHARGE);
        }
    }

    static class IngredientsSlot extends Slot {
        private final EnderPotionBrewing potionBrewing;

        public IngredientsSlot(EnderPotionBrewing potionBrewing, Container container, int slot, int x, int y) {
            super(container, slot, x, y);
            this.potionBrewing = potionBrewing;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return this.potionBrewing.isIngredient(stack);
        }
    }

    static class PotionSlot extends Slot {
        private final EnderPotionBrewing potionBrewing;

        public PotionSlot(EnderPotionBrewing potionBrewing, Container container, int p_39124_, int p_39125_, int p_39126_) {
            super(container, p_39124_, p_39125_, p_39126_);
            this.potionBrewing = potionBrewing;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return mayPlaceItem(this.potionBrewing, stack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public void onTake(Player player, ItemStack stack) {
            Optional<Holder<Potion>> optional = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
            if (optional.isPresent() && player instanceof ServerPlayer serverplayer) {
                EventHooks.onPlayerBrewedPotion(player, stack);
                CriteriaTriggers.BREWED_POTION.trigger(serverplayer, optional.get());
            }

            super.onTake(player, stack);
        }

        public static boolean mayPlaceItem(EnderPotionBrewing potionBrewing, ItemStack p_39134_) {
            return potionBrewing.isInput(p_39134_) || p_39134_.is(NMTItems.OBSIDIAN_GLASS_BOTTLE);
        }
    }
}
