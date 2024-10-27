package net.nova.nmt.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.item.ObsidianBottleItem;
import net.nova.nmt.item.ObsidianPotionItem;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static DeferredItem<Item> OBSIDIAN_GLASS_BOTTLE = ITEMS.register("obsidian_glass_bottle", () -> new ObsidianBottleItem(new Item.Properties()));
    public static DeferredItem<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle", () -> new ObsidianPotionItem(new Item.Properties()));
}
