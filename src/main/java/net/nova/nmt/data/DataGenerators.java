package net.nova.nmt.data;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.nmt.data.loot.NMTLootTableProvider;
import net.nova.nmt.data.models.NMTModelProvider;
import net.nova.nmt.data.recipe.NMTRecipeProvider;
import net.nova.nmt.data.tags.NMTBlockTagsProvider;
import net.nova.nmt.data.tags.NMTItemTagsProvider;
import net.nova.nmt.data.worldgen.NMTBiomeModifiers;
import net.nova.nmt.data.worldgen.NMTConfiguredFeature;
import net.nova.nmt.data.worldgen.NMTPlacedFeatures;

import java.util.Set;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(LangProvider::new);
        event.createProvider(NMTModelProvider::new);
        event.createProvider(NMTRecipeProvider.Runner::new);
        event.createBlockAndItemTags(NMTBlockTagsProvider::new, NMTItemTagsProvider::new);
        event.createProvider(NMTLootTableProvider::new);
        event.createProvider(NMTDataMapProvider::new);
        event.createDatapackRegistryObjects(new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, NMTConfiguredFeature::bootstrap)
                        .add(Registries.PLACED_FEATURE, NMTPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NMTBiomeModifiers::bootstrap),
                Set.of(MODID));
    }
}
