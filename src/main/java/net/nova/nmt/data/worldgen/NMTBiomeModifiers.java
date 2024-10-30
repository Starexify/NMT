package net.nova.nmt.data.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.nmt.NoMoreThings;

public class NMTBiomeModifiers {
    public static ResourceKey<BiomeModifier> END_FARM = createKey("end_farm");
    public static ResourceKey<BiomeModifier> END_TEMPLE = createKey("end_temple");
    public static ResourceKey<BiomeModifier> END_TOWER = createKey("end_tower");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(END_FARM, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_MIDLANDS)),
                HolderSet.direct(placedFeatures.getOrThrow(NMTPlacedFeatures.END_FARM)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(END_TEMPLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_MIDLANDS)),
                HolderSet.direct(placedFeatures.getOrThrow(NMTPlacedFeatures.END_TEMPLE)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(END_TOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_MIDLANDS)),
                HolderSet.direct(placedFeatures.getOrThrow(NMTPlacedFeatures.END_TOWER)),
                GenerationStep.Decoration.RAW_GENERATION
        ));
    }

    public static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NoMoreThings.rl(name));
    }
}
