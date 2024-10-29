package net.nova.nmt.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.block.EnderBrewingStandBlock;
import net.nova.nmt.gui.ender_brewing_stand.EnderBrewingStandMenu;
import net.nova.nmt.init.NMTBlockEntities;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.recipe.EnderPotionBrewing;

import javax.annotation.Nullable;
import java.util.Arrays;

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
        ItemStack itemstack = blockEntity.items.get(4);
        if (blockEntity.fuel <= 0 && itemstack.is(Items.WIND_CHARGE)) {
            blockEntity.fuel = 20;
            itemstack.shrink(1);
            setChanged(level, pos, state);
        }

        boolean flag = isBrewable(NoMoreThings.getEnderBrewing(), blockEntity.items);
        boolean flag1 = blockEntity.brewTime > 0;
        ItemStack itemstack1 = blockEntity.items.get(3);
        if (flag1) {
            blockEntity.brewTime--;
            boolean flag2 = blockEntity.brewTime == 0;
            if (flag2 && flag) {
                doBrew(level, pos, blockEntity.items);
            } else if (!flag || !itemstack1.is(blockEntity.ingredient)) {
                blockEntity.brewTime = 0;
            }

            setChanged(level, pos, state);
        } else if (flag && blockEntity.fuel > 0) {
            blockEntity.fuel--;
            blockEntity.brewTime = 400;
            blockEntity.ingredient = itemstack1.getItem();
            setChanged(level, pos, state);
        }

        boolean[] aboolean = blockEntity.getPotionBits();
        if (!Arrays.equals(aboolean, blockEntity.lastPotionCount)) {
            blockEntity.lastPotionCount = aboolean;
            BlockState blockstate = state;
            if (!(state.getBlock() instanceof EnderBrewingStandBlock)) {
                return;
            }

            for (int i = 0; i < EnderBrewingStandBlock.HAS_BOTTLE.length; i++) {
                blockstate = blockstate.setValue(EnderBrewingStandBlock.HAS_BOTTLE[i], Boolean.valueOf(aboolean[i]));
            }

            level.setBlock(pos, blockstate, 2);
        }
    }

    private boolean[] getPotionBits() {
        boolean[] aboolean = new boolean[3];

        for (int i = 0; i < 3; i++) {
            if (!this.items.get(i).isEmpty()) {
                aboolean[i] = true;
            }
        }

        return aboolean;
    }

    private static boolean isBrewable(EnderPotionBrewing potionBrewing, NonNullList<ItemStack> items) {
        ItemStack itemstack = items.get(3);
        if (itemstack.isEmpty()) {
            return false;
        } else if (!potionBrewing.isIngredient(itemstack)) {
            return false;
        } else {
            for (int i = 0; i < 3; i++) {
                ItemStack itemstack1 = items.get(i);
                if (!itemstack1.isEmpty() && potionBrewing.hasMix(itemstack1, itemstack)) {
                    return true;
                }
            }

            return false;
        }
    }

    private static void doBrew(Level level, BlockPos pos, NonNullList<ItemStack> items) {
        ItemStack itemstack = items.get(3);
        EnderPotionBrewing potionbrewing = NoMoreThings.getEnderBrewing();

        for (int i = 0; i < 3; i++) {
            items.set(i, potionbrewing.mix(itemstack, items.get(i)));
        }

        if (itemstack.hasCraftingRemainingItem()) {
            ItemStack itemstack1 = itemstack.getCraftingRemainingItem();
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                itemstack = itemstack1;
            } else {
                Containers.dropItemStack(level, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemstack1);
            }
        }
        else itemstack.shrink(1);

        items.set(3, itemstack);
        level.levelEvent(1035, pos, 0);
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
        EnderPotionBrewing potionbrewing = this.level != null ? NoMoreThings.getEnderBrewing() : EnderPotionBrewing.EMPTY;
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
