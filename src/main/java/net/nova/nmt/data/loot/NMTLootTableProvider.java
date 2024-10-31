package net.nova.nmt.data.loot;

import com.google.common.collect.Sets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


import static net.nova.nmt.NoMoreThings.MODID;

public class NMTLootTableProvider extends LootTableProvider {
    public NMTLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST)
        ), registries);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
        var modLootTablesId = BuiltInLootTables.all()
                .stream()
                .filter(id -> id.registry().getNamespace().equals(MODID))
                .collect(Collectors.toSet());
        for (var id : Sets.difference(modLootTablesId, writableregistry.keySet())) {
            validationcontext.reportProblem("Missing build-in table:" + id);
        }

        writableregistry.forEach(lootTable -> lootTable.validate(validationcontext));
    }
}