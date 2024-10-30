package net.nova.nmt.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.nmt.data.worldgen.NMTBiomeModifiers;
import net.nova.nmt.data.worldgen.NMTConfiguredFeature;
import net.nova.nmt.data.worldgen.NMTPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.nova.nmt.NoMoreThings.MODID;

public class DatapackProvider extends DatapackBuiltinEntriesProvider {
    public DatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, NMTConfiguredFeature::bootstrap)
                        .add(Registries.PLACED_FEATURE, NMTPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NMTBiomeModifiers::bootstrap),
                Set.of(MODID));
    }
}
