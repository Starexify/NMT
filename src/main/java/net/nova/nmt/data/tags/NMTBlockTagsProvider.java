package net.nova.nmt.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.nmt.init.NMTBlocks;

import java.util.concurrent.CompletableFuture;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTBlockTagsProvider extends BlockTagsProvider {
    public NMTBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(NMTBlocks.OBSIDIAN_GLASS.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(NMTBlocks.OBSIDIAN_GLASS.get());

        tag(BlockTags.MINEABLE_WITH_AXE).add(NMTBlocks.ENDER_WART.get(), NMTBlocks.ENDER_WART_BLOCK.get());
        tag(BlockTags.SWORD_EFFICIENT).add(NMTBlocks.ENDER_WART.get());
        tag(BlockTags.WART_BLOCKS).add(NMTBlocks.ENDER_WART_BLOCK.get());
    }
}