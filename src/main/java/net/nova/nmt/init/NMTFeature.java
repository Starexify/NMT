package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.world.features.StructureFeature;
import net.nova.nmt.world.features.configurations.StructureFeatureConfiguration;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MODID);

    public static final DeferredHolder<Feature<?>, StructureFeature> STRUCTURE_FEATURE = FEATURES.register("structure_feature", () -> new StructureFeature(StructureFeatureConfiguration.CODEC));
}
