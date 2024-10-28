package net.nova.nmt.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.nmt.gui.EnderBrewingStandMenu;
import net.nova.nmt.init.NMTBlockEntities;
import net.nova.nmt.init.NMTItems;

import javax.annotation.Nullable;

public class EnderBrewingStandBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    private static final int INGREDIENT_SLOT = 3;
    private static final int FUEL_SLOT = 4;
    private static final int[] SLOTS_FOR_UP = new int[]{3};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0, 1, 2, 3};
    private static final int[] SLOTS_FOR_SIDES = new int[]{0, 1, 2, 4};
    public static final int FUEL_USES = 20;
    public static final int DATA_BREW_TIME = 0;
    public static final int DATA_FUEL_USES = 1;
    public static final int NUM_DATA_VALUES = 2;

    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    int brewTime;
    private boolean[] lastPotionCount;
    private Item ingredient;
    int fuel;

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int p_59038_) {
            return switch (p_59038_) {
                case 0 -> EnderBrewingStandBlockEntity.this.brewTime;
                case 1 -> EnderBrewingStandBlockEntity.this.fuel;
                default -> 0;
            };
        }

        @Override
        public void set(int p_59040_, int p_59041_) {
            switch (p_59040_) {
                case 0:
                    EnderBrewingStandBlockEntity.this.brewTime = p_59041_;
                    break;
                case 1:
                    EnderBrewingStandBlockEntity.this.fuel = p_59041_;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public EnderBrewingStandBlockEntity(BlockPos pos, BlockState state) {
        super(NMTBlockEntities.ENDER_BREWING_STAND.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.ender_brewing");
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, EnderBrewingStandBlockEntity blockEntity) {

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.brewTime = tag.getShort("BrewTime");
        if (this.brewTime > 0) {
            this.ingredient = this.items.get(3).getItem();
        }

        this.fuel = tag.getByte("Fuel");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putShort("BrewTime", (short)this.brewTime);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putByte("Fuel", (byte)this.fuel);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        PotionBrewing potionbrewing = this.level != null ? this.level.potionBrewing() : PotionBrewing.EMPTY;
        if (index == 3) {
            return potionbrewing.isIngredient(stack);
        } else {
            return index == 4
                    ? stack.is(Items.WIND_CHARGE)
                    : (potionbrewing.isInput(stack) || stack.is(NMTItems.OBSIDIAN_GLASS_BOTTLE))
                    && this.getItem(index).isEmpty();
        }
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP) {
            return SLOTS_FOR_UP;
        } else {
            return side == Direction.DOWN ? SLOTS_FOR_DOWN : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == 3 ? stack.is(NMTItems.OBSIDIAN_GLASS_BOTTLE) : true;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new EnderBrewingStandMenu(id, player, this, this.dataAccess);
    }
}
