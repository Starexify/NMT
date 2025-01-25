package net.nova.nmt.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.nmt.init.NMTBlockEntities;
import org.jetbrains.annotations.Nullable;

public class StaffAssemblerBlockEntity extends BlockEntity implements MenuProvider {
    public StaffAssemblerBlockEntity(BlockPos pos, BlockState blockState) {
        super(NMTBlockEntities.STAFF_ASSEMBLER.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return null;
    }
}
