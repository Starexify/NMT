package net.nova.nmt.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.block.EnderBrewingStandBlock;
import net.nova.nmt.block.EnderWartCrop;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    // Obsidian Glass
    public static DeferredBlock<Block> OBSIDIAN_GLASS = registerBlock("obsidian_glass", () -> new StainedGlassBlock(DyeColor.PURPLE, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.HAT)
            .strength(25.0F, 1200.0F)
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .isValidSpawn(NMTBlocks::never)
            .isRedstoneConductor(NMTBlocks::never)
            .isSuffocating(NMTBlocks::never)
            .isViewBlocking(NMTBlocks::never)
    ));

    public static final DeferredBlock<Block> OBSIDIAN_GLASS_PANE = registerBlock("obsidian_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.PURPLE, BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.HAT)
            .strength(25.0F, 1200.0F)
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()
            .noOcclusion()
    ));

    public static final DeferredBlock<Block> ENDER_BREWING_STAND = registerBlock("ender_brewing_stand", () -> new EnderBrewingStandBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .requiresCorrectToolForDrops()
            .strength(0.5F)
            .lightLevel(light -> 12)
            .noOcclusion()
    ));

    // Ender Wart
    public static final DeferredBlock<Block> ENDER_WART = BLOCKS.register("ender_wart", () -> new EnderWartCrop(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollission()
            .randomTicks()
            .sound(SoundType.NETHER_WART)
            .pushReaction(PushReaction.DESTROY)
    ));

    public static final DeferredBlock<Block> ENDER_WART_BLOCK = registerBlock(
            "ender_wart_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.WART_BLOCK).lightLevel(light -> 8))
    );

    // Methods
    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }

    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    // Registers
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        NMTItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
