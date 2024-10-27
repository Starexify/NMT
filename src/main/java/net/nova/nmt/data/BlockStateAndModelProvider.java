package net.nova.nmt.data;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.nmt.init.NMTBlocks;

import static net.nova.nmt.NoMoreThings.MODID;

public class BlockStateAndModelProvider extends BlockStateProvider {
    public BlockStateAndModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createGlassBlock(NMTBlocks.OBSIDIAN_GLASS.get(), NMTBlocks.OBSIDIAN_GLASS_PANE.get());
    }

    public void createGlassBlock(Block glassBlock, Block paneBlock) {
        simpleBlockWithItem(glassBlock, models().cubeAll(name(glassBlock), modLoc("block/" + name(glassBlock))).renderType(RenderType.TRANSLUCENT.name));

        ResourceLocation glassBlockTexture = modLoc("block/" + name(glassBlock));
        ResourceLocation paneTexture = modLoc("block/" + name(paneBlock) + "_top");
        ModelBuilder<?> postModel = models().panePost(name(paneBlock) + "_post", paneTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> sideModel = models().paneSide(name(paneBlock) + "_side", paneTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> sideAltModel = models().paneSideAlt(name(paneBlock) + "_side_alt", paneTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> noSideModel = models().paneNoSide(name(paneBlock) + "_noside", paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> noSideAltModel = models().paneNoSideAlt(name(paneBlock) + "_noside_alt", paneTexture).renderType(RenderType.TRANSLUCENT.name);

        itemModels().getBuilder(name(paneBlock))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", glassBlockTexture).renderType(RenderType.TRANSLUCENT.name);

        getMultipartBuilder(paneBlock)
                .part().modelFile(postModel).addModel().end()
                .part().modelFile(sideModel)
                .addModel()
                .condition(BlockStateProperties.NORTH, true)
                .end()
                .part().modelFile(sideModel)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.EAST, true)
                .end()
                .part().modelFile(sideAltModel)
                .addModel()
                .condition(BlockStateProperties.SOUTH, true)
                .end()
                .part().modelFile(sideAltModel)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.WEST, true)
                .end()
                .part().modelFile(noSideModel)
                .addModel()
                .condition(BlockStateProperties.NORTH, false)
                .end()
                .part().modelFile(noSideAltModel)
                .addModel()
                .condition(BlockStateProperties.EAST, false)
                .end()
                .part().modelFile(noSideAltModel)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.SOUTH, false)
                .end()
                .part().modelFile(noSideModel)
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.WEST, false)
                .end();
    }

    // Other stuff
    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}