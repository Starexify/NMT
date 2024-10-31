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
        addBlock(NMTBlocks.ENDER_BREWING_STAND, "Ender Brewing Stand");
        addBlock(NMTBlocks.ENDER_WART, "Ender Wart");
        addBlock(NMTBlocks.ENDER_WART_BLOCK, "Ender Wart Block");

        // Items
        addItem(NMTItems.OBSIDIAN_GLASS_BOTTLE, "Obsidian Glass Bottle");
        /// Obsidian Potions
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.LAVA, "Lava Bottle");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.AWFULLY, "Awfully Potion");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Potion of Fortifying");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.CECITY, "Potion of Cecity");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.DIMNESS, "Potion of Dimness");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.LAVA, "Splash Lava Bottle");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.AWFULLY, "Splash Awfully Potion");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Splash Potion of Fortifying");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.CECITY, "Splash Potion of Cecity");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.DIMNESS, "Splash Potion of Dimness");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.LAVA, "Lingering Lava Bottle");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.AWFULLY, "Lingering Awfully Potion");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Lingering Potion of Fortifying");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.CECITY, "Lingering Potion of Cecity");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.DIMNESS, "Lingering Potion of Dimness");

        addItem(NMTItems.ENDER_WART, "Ender Wart");

        // Creative Tab
        add(CreativeTab.MAIN_TAB_TITLE, "No More Things");
        add(CreativeTab.POTIONS_TAB_TITLE, "No More Things Potions");

        // Mob Effects
        addEffect(NMTEffects.BURN::value, "Burn");

        // Block Entity
        add("container.ender_brewing", "Ender Brewing Stand");
    }

    public void addObsidianPotion(Supplier<? extends Item> key, Holder<Potion> potionName, String name) {
        add(key.get().getDescriptionId() + ".effect." + potionName.getKey().location().getPath(), name);
    }
}