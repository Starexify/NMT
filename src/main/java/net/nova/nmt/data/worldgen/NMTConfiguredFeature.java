package net.nova.nmt.data.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTFeature;
import net.nova.nmt.world.features.configurations.StructureFeatureConfiguration;

public class NMTConfiguredFeature {
    public static ResourceKey<ConfiguredFeature<?, ?>> END_FARM = createKey("end_farm");
    public static ResourceKey<ConfiguredFeature<?, ?>> END_TEMPLE = createKey("end_temple");
    public static ResourceKey<ConfiguredFeature<?, ?>> END_TOWER = createKey("end_tower");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderSet<Block> ignoredBlocks = HolderSet.direct(Block::builtInRegistryHolder);
        Vec3i underOffset = new Vec3i(0, -1, 0);
        Vec3i underThreeOffset = new Vec3i(0, -3, 0);

        register(context, END_FARM, NMTFeature.STRUCTURE_FEATURE.get(), new StructureFeatureConfiguration(NoMoreThings.rl("end_farm"), true, true, ignoredBlocks, underOffset));
        register(context, END_TEMPLE, NMTFeature.STRUCTURE_FEATURE.get(), new StructureFeatureConfiguration(NoMoreThings.rl("end_temple"), true, true, ignoredBlocks, underOffset));
        register(context, END_TOWER, NMTFeature.STRUCTURE_FEATURE.get(), new StructureFeatureConfiguration(NoMoreThings.rl("end_tower"), true, true, ignoredBlocks, underThreeOffset));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, NoMoreThings.rl(name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
