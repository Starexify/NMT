package net.nova.nmt.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.alchemy.PotionContents;
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
    public static final DeferredItem<Item> ENDER_WART = ITEMS.register("ender_wart", () -> new ItemNameBlockItem(NMTBlocks.ENDER_WART.get(), new Item.Properties()));

    // Obsidian Bottle
    public static final DeferredItem<Item> OBSIDIAN_GLASS_BOTTLE = ITEMS.register("obsidian_glass_bottle", () -> new ObsidianBottleItem(new Item.Properties().fireResistant()));
    /// Obsidian Potions
    public static final DeferredItem<Item> OBSIDIAN_POTION = ITEMS.register("obsidian_potion", () -> new ObsidianPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));
    public static final DeferredItem<Item> SPLASH_OBSIDIAN_POTION = ITEMS.register("splash_obsidian_potion", () -> new SplashObsidianPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));
    public static final DeferredItem<Item> LINGERING_OBSIDIAN_POTION = ITEMS.register("lingering_obsidian_potion", () -> new LingeringObsidianPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));

}
