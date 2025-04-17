package net.nova.nmt.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.nova.nmt.init.NMTItems;

import java.util.concurrent.CompletableFuture;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItemTagsProvider extends ItemTagsProvider {
    public NMTItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.ARROWS).add(NMTItems.OBSIDIAN_TIPPED_ARROW.get());
    }
}
