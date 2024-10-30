package net.nova.nmt.data.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTFeature;
import net.nova.nmt.world.features.configurations.StructureFeatureConfiguration;

public class NMTConfiguredFeature {
    public static ResourceKey<ConfiguredFeature<?, ?>> END_FARM = createKey("end_farm");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderSet<Block> ignoredBlocks = HolderSet.direct(Block::builtInRegistryHolder);
        Vec3i endFarmOffset = new Vec3i(0, -1, 0);

        register(context, END_FARM, NMTFeature.STRUCTURE_FEATURE.get(), new StructureFeatureConfiguration(NoMoreThings.rl("end_farm"), true, true, ignoredBlocks, endFarmOffset));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, NoMoreThings.rl(name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
