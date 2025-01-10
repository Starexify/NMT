package net.nova.nmt.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.nova.nmt.init.NMTBlocks;
import org.w3c.dom.Text;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NMTBlockModelGenerator extends BlockModelGenerators {
    public NMTBlockModelGenerator(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        createBrewingStand(NMTBlocks.ENDER_BREWING_STAND.get());
        createGlassBlocks(NMTBlocks.OBSIDIAN_GLASS.get(), NMTBlocks.OBSIDIAN_GLASS_PANE.get());
        createCropBlock(NMTBlocks.ENDER_WART.get(), BlockStateProperties.AGE_3, 0, 1, 1, 2);
        createTrivialCube(NMTBlocks.ENDER_WART_BLOCK.get());
    }

    // Models
    public void createBrewingStand(Block block) {
        this.registerSimpleFlatItemModel(block.asItem());
        this.registerSimpleItemModel(block.asItem(), TextureMapping.getBlockTexture(block));
        this.blockStateOutput.accept(MultiPartGenerator.multiPart(block).with(Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_0, true), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_bottle0"))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_1, true), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_bottle1"))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_2, true), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_bottle2"))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_0, false), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_empty0"))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_1, false), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_empty1"))).with(Condition.condition().term(BlockStateProperties.HAS_BOTTLE_2, false), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(block, "_empty2"))));
    }
}
