package net.nova.nmt.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.nmt.init.NMTBlockEntities;

public class StaffAssemblerBlockEntity extends BlockEntity {
    public StaffAssemblerBlockEntity(BlockPos pos, BlockState blockState) {
        super(NMTBlockEntities.STAFF_ASSEMBLER.get(), pos, blockState);
    }
}
