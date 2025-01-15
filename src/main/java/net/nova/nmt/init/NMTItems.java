package net.nova.nmt.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TippedArrowItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.item.LingeringObsidianPotionItem;
import net.nova.nmt.item.ObsidianBottleItem;
import net.nova.nmt.item.ObsidianPotionItem;
import net.nova.nmt.item.SplashObsidianPotionItem;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Ender Wart
    public static final DeferredItem<BlockItem> ENDER_WART = ITEMS.registerItem("ender_wart", properties -> new BlockItem(NMTBlocks.ENDER_WART.get(), properties));

    // Obsidian Bottle
    public static final DeferredItem<Item> OBSIDIAN_GLASS_BOTTLE = ITEMS.registerItem("obsidian_glass_bottle", properties -> new ObsidianBottleItem(properties.fireResistant()));
    /// Obsidian Potions
    public static final DeferredItem<Item> OBSIDIAN_POTION = ITEMS.registerItem("obsidian_potion", properties -> new ObsidianPotionItem(properties.stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK).fireResistant()));

    public static final DeferredItem<Item> SPLASH_OBSIDIAN_POTION = ITEMS.registerItem("splash_obsidian_potion", properties -> new SplashObsidianPotionItem(properties.stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));
    public static final DeferredItem<Item> LINGERING_OBSIDIAN_POTION = ITEMS.registerItem("lingering_obsidian_potion", properties -> new LingeringObsidianPotionItem(properties.stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_TIPPED_ARROW = ITEMS.registerItem("obsidian_tipped_arrow", properties -> new TippedArrowItem(properties.component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA))));

    // Tear of the Nether
    public static final DeferredItem<Item> TEAR_OF_THE_NETHER = ITEMS.registerSimpleItem("tear_of_the_nether");
    public static final DeferredItem<Item> BLAZING_WITHER_SOUL = ITEMS.registerSimpleItem("blazing_wither_soul");
    public static final DeferredItem<Item> HEART_OF_THE_HELL = ITEMS.registerSimpleItem("heart_of_the_hell");
}
