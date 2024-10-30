package net.nova.nmt.data.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
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
                RarityFilter.onAverageOnceEvery(10),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), Blocks.END_STONE),
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.noFluid(),
                        BlockPredicate.insideWorld(new Vec3i(0, 0, 0))
                ))
        ));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NoMoreThings.rl(name));
    }
}
