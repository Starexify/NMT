package net.nova.nmt.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.item.ObsidianBottleItem;
import net.nova.nmt.item.ObsidianPotionItem;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Obsidian Bottle
    public static DeferredItem<Item> OBSIDIAN_GLASS_BOTTLE = ITEMS.register("obsidian_glass_bottle", () -> new ObsidianBottleItem(new Item.Properties().fireResistant()));
    /// Obsidian Potions
    public static DeferredItem<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle", () -> new ObsidianPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.LAVA)).fireResistant()));
    public static DeferredItem<Item> AWFULLY_POTION = ITEMS.register("awfully_potion", () -> new ObsidianPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, new PotionContents(NMTPotions.AWFULLY)).fireResistant()));
}
