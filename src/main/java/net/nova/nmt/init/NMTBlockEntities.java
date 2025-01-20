package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.block.entity.EnderBrewingStandBlockEntity;
import net.nova.nmt.block.entity.StaffAssemblerBlockEntity;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnderBrewingStandBlockEntity>> ENDER_BREWING_STAND = BLOCK_ENTITIES.register("ender_brewing_stand",
            () -> new BlockEntityType<>(EnderBrewingStandBlockEntity::new, NMTBlocks.ENDER_BREWING_STAND.get())
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StaffAssemblerBlockEntity>> STAFF_ASSEMBLER = BLOCK_ENTITIES.register("staff_assembler",
            () -> new BlockEntityType<>(StaffAssemblerBlockEntity::new, NMTBlocks.ENDER_BREWING_STAND.get())
    );
}
