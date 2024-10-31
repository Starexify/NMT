package net.nova.nmt.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetInstrumentFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTTags;

import java.util.function.BiConsumer;

public record ChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> pOutput) {
        pOutput.accept(NMTTags.ChestLootTags.END_TEMPLE, endTempleLoot());
        pOutput.accept(NMTTags.ChestLootTags.END_TOWER_SHULKER, endTowerShulkerLoot());
        pOutput.accept(NMTTags.ChestLootTags.END_TOWER_CHEST, endTowerChestLoot());
    }

    public LootTable.Builder endTempleLoot() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3.0F, 8.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
                        .add(LootItem.lootTableItem(NMTItems.ENDER_WART).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.SADDLE).setWeight(2))
                        .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR))
                        .add(LootItem.lootTableItem(Items.DIAMOND_SWORD).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_HELMET).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_PICKAXE).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_SHOVEL).setWeight(3).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(EmptyLootItem.emptyItem().setWeight(12))
                        .add(LootItem.lootTableItem(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1))
                );
    }

    public LootTable.Builder endTowerShulkerLoot() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(NMTItems.OBSIDIAN_POTION).setWeight(7))
                        .add(LootItem.lootTableItem(NMTItems.OBSIDIAN_POTION).setWeight(3))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))))
                        .add(LootItem.lootTableItem(NMTItems.ENDER_WART).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                );
    }

    public LootTable.Builder endTowerChestLoot() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(NMTItems.ENDER_WART).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                ).withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.ELYTRA))
                );
    }
}