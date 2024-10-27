package net.nova.nmt.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.nmt.init.CreativeTab;
import net.nova.nmt.init.NMTBlocks;

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

        // Creative Tab
        add(CreativeTab.NO_MORE_THINGS_TAB_TITLE, "No More Things");
    }
}