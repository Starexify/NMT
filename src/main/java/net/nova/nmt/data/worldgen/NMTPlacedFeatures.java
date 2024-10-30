package net.nova.nmt.data.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.nova.nmt.NoMoreThings;

import java.util.List;

public class NMTPlacedFeatures {
    public static ResourceKey<PlacedFeature> END_FARM = createKey("end_farm");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> holder1 = holdergetter.getOrThrow(NMTConfiguredFeature.END_FARM);

        register(context, END_FARM, holder1, List.of(
                RarityFilter.onAverageOnceEvery(2000),
                SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 8, 80),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        ));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NoMoreThings.rl(name));
    }
}