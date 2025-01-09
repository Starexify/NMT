package net.nova.nmt.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.nmt.data.loot.NMTLootTableProvider;
import net.nova.nmt.data.models.NMTModelProvider;
import net.nova.nmt.data.recipe.NMTRecipeProvider;
import net.nova.nmt.data.tags.NMTBlockTagsProvider;
import net.nova.nmt.data.tags.NMTItemTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new LangProvider(output));

        event.addProvider(new NMTModelProvider(output));

        event.addProvider(new NMTRecipeProvider.Runner(output, lookupProvider));

        NMTBlockTagsProvider modBlockTagsProvider = new NMTBlockTagsProvider(output, lookupProvider);
        event.addProvider(modBlockTagsProvider);
        event.addProvider(new NMTItemTagsProvider(output, lookupProvider, modBlockTagsProvider));

        event.addProvider(new NMTLootTableProvider(output, lookupProvider));

        event.addProvider(new NMTDataMapProvider(output, lookupProvider));

        event.addProvider(new DatapackProvider(output, lookupProvider));
    }
}
