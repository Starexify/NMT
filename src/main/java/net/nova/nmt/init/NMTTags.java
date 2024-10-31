package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.nova.nmt.NoMoreThings;

import java.util.HashSet;
import java.util.Set;

public class NMTTags {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();

    public static class ChestLootTags {
        public static final ResourceKey<LootTable> END_TEMPLE = register("shulkers/end_temple");
        public static final ResourceKey<LootTable> END_TOWER_SHULKER = register("shulkers/end_tower");
        public static final ResourceKey<LootTable> END_TOWER_CHEST = register("chests/end_tower");
    }

    public static ResourceKey<LootTable> register(String name) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, NoMoreThings.rl(name)));
    }

    public static ResourceKey<LootTable> register(ResourceKey<LootTable> pName) {
        if (LOCATIONS.add(pName)) {
            return pName;
        } else {
            throw new IllegalArgumentException(pName.location() + " is already a registered built-in loot table");
        }
    }
}
