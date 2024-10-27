package net.nova.nmt.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.data.loot.NMTLootTableProvider;
import net.nova.nmt.data.recipe.NMTRecipeProvider;
import net.nova.nmt.data.tags.NMTBlockTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            generator.addProvider(true, new LangProvider(output));

            generator.addProvider(true, new NMTItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new BlockStateAndModelProvider(output, existingFileHelper));

            generator.addProvider(true, new NMTRecipeProvider(output, lookupProvider));

            NMTBlockTagsProvider modBlockTagsProvider = new NMTBlockTagsProvider(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);

            generator.addProvider(true, new NMTLootTableProvider(output, lookupProvider));

        } catch (RuntimeException e) {
            NoMoreThings.logger.error("No More Things failed to gather data", e);
        }
    }
}
