package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.blockentity.EnderBrewingStandBlockEntity;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final Supplier<BlockEntityType<EnderBrewingStandBlockEntity>> ENDER_BREWING_STAND = BLOCK_ENTITIES.register("ender_brewing_stand",
            () -> BlockEntityType.Builder.of(EnderBrewingStandBlockEntity::new, NMTBlocks.ENDER_BREWING_STAND.get()).build(null)
    );
}
