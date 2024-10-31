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
        addItem(NMTItems.ENDER_WART, "Ender Wart");

        /// Obsidian Glass Bottle
        addItem(NMTItems.OBSIDIAN_GLASS_BOTTLE, "Obsidian Glass Bottle");
        /// Obsidian Potions
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.LAVA, "Lava Bottle");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.AWFULLY, "Awfully Potion");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Potion of Fortifying");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.CECITY, "Potion of Cecity");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.DIMNESS, "Potion of Dimness");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.QUICKNESS, "Potion of Quickness");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.TITAN, "Potion of the Titan");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.STARVATION, "Potion of Starvation");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.FLOATING, "Potion of Floating");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.EXHAUSTION, "Potion of Exhaustion");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.SICKNESS, "Potion of Sickness");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.UNLUCK, "Potion of Unluck");
        addObsidianPotion(NMTItems.OBSIDIAN_POTION, NMTPotions.WITHERING, "Potion of Withering");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.LAVA, "Splash Lava Bottle");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.AWFULLY, "Splash Awfully Potion");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Splash Potion of Fortifying");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.CECITY, "Splash Potion of Cecity");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.DIMNESS, "Splash Potion of Dimness");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.QUICKNESS, "Splash Potion of Quickness");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.TITAN, "Splash Potion of the Titan");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.STARVATION, "Splash Potion of Starvation");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.FLOATING, "Splash Potion of Floating");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.EXHAUSTION, "Splash Potion of Exhaustion");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.SICKNESS, "Splash Potion of Sickness");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.UNLUCK, "Splash Potion of Unluck");
        addObsidianPotion(NMTItems.SPLASH_OBSIDIAN_POTION, NMTPotions.WITHERING, "Splash Potion of Withering");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.LAVA, "Lingering Lava Bottle");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.AWFULLY, "Lingering Awfully Potion");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.FORTIFYING, "Lingering Potion of Fortifying");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.CECITY, "Lingering Potion of Cecity");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.DIMNESS, "Lingering Potion of Dimness");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.QUICKNESS, "Lingering Potion of Quickness");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.TITAN, "Lingering Potion of the Titan");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.STARVATION, "Lingering Potion of Starvation");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.FLOATING, "Lingering Potion of Floating");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.EXHAUSTION, "Lingering Potion of Exhaustion");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.SICKNESS, "Lingering Potion of Sickness");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.UNLUCK, "Lingering Potion of Unluck");
        addObsidianPotion(NMTItems.LINGERING_OBSIDIAN_POTION, NMTPotions.WITHERING, "Lingering Potion of Withering");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.LAVA, "Arrow of Burning");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.AWFULLY, "Tipped Arrow");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.FORTIFYING, "Arrow of Fortifying");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.CECITY, "Arrow of Cecity");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.DIMNESS, "Arrow of Dimness");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.QUICKNESS, "Arrow of Quickness");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.TITAN, "Arrow of the Titan");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.STARVATION, "Arrow of Starvation");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.FLOATING, "Arrow of Floating");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.EXHAUSTION, "Arrow of Exhaustion");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.SICKNESS, "Arrow of Sickness");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.UNLUCK, "Arrow of Unluck");
        addObsidianPotion(NMTItems.OBSIDIAN_TIPPED_ARROW, NMTPotions.WITHERING, "Arrow of Withering");

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