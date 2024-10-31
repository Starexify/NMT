package net.nova.nmt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.CommonHooks;
import net.nova.nmt.init.NMTItems;

public class EnderWartCrop extends NetherWartBlock {
    public EnderWartCrop(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 3 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(20) == 0)) {
            state = state.setValue(AGE, Integer.valueOf(i + 1));
            level.setBlock(pos, state, 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.getValue(AGE) + 1) * 2;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.END_STONE);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(NMTItems.ENDER_WART.get());
    }
}
