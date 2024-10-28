package net.nova.nmt.data;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.nmt.init.*;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Blocks
        addBlock(NMTBlocks.OBSIDIAN_GLASS, "Obsidian Glass");
        addBlock(NMTBlocks.OBSIDIAN_GLASS_PANE, "Obsidian Glass Pane");

        // Items
        addItem(NMTItems.OBSIDIAN_GLASS_BOTTLE, "Obsidian Glass Bottle");
        /// Obsidian Potions
        addObsidianPotion(NMTItems.LAVA_BOTTLE, NMTPotions.LAVA, "Lava Bottle");

        // Creative Tab
        add(CreativeTab.NO_MORE_THINGS_TAB_TITLE, "No More Things");

        // Mob Effects
        addEffect(NMTEffects.BURN::value, "Burn");
    }

    public void addObsidianPotion(Supplier<? extends Item> key, Holder<Potion> potionName, String name) {
        add(key.get().getDescriptionId() + ".effect." + potionName.getKey().location().getPath(), name);
    }
}