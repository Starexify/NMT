package net.nova.nmt.data;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
        createBrewingStand(NMTBlocks.ENDER_BREWING_STAND.get());
        createCropBlock(NMTBlocks.ENDER_WART.get(), BlockStateProperties.AGE_3, 0, 1, 1, 2);
        normalBlock(NMTBlocks.ENDER_WART_BLOCK.get());
    }

    // Methods
    public void createCropBlock(Block pCropBlock, IntegerProperty pAgeProperty, int... pAgeToVisualStageMapping) {
        if (pAgeProperty.getPossibleValues().size() != pAgeToVisualStageMapping.length) {
            throw new IllegalArgumentException("Number of ages and visual stages must match");
        }

        getVariantBuilder(pCropBlock)
                .forAllStates(state -> {
                    int age = state.getValue(pAgeProperty);
                    String stageName = "stage" + pAgeToVisualStageMapping[age];
                    return ConfiguredModel.builder()
                            .modelFile(models().crop(name(pCropBlock) + "_" + stageName, modLoc("block/" + name(pCropBlock) + "_" + stageName)).renderType(RenderType.CUTOUT.name))
                            .build();
                });

        itemModels().getBuilder(name(pCropBlock))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc("item/" + name(pCropBlock)));
    }

    public void createBrewingStand(Block block) {
        itemModels().basicItem(block.asItem());

        getMultipartBuilder(block)
                .part().modelFile(models().withExistingParent(name(block), mcLoc("brewing_stand"))
                        .texture("particle", "block/" + name(block))
                        .texture("base", "block/" + name(block) + "_base")
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel()
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_bottle0", mcLoc("brewing_stand").withSuffix("_bottle0"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_0, true)
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_bottle1", mcLoc("brewing_stand").withSuffix("_bottle1"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_1, true)
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_bottle2", mcLoc("brewing_stand").withSuffix("_bottle2"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_2, true)
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_empty0", mcLoc("brewing_stand").withSuffix("_empty0"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_0, false)
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_empty1", mcLoc("brewing_stand").withSuffix("_empty1"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_1, false)
                .end()
                .part().modelFile(models().withExistingParent(name(block) + "_empty2", mcLoc("brewing_stand").withSuffix("_empty2"))
                        .texture("particle", "block/" + name(block))
                        .texture("stand", "block/" + name(block)).renderType(RenderType.CUTOUT.name))
                .addModel().condition(BlockStateProperties.HAS_BOTTLE_2, false)
                .end();
    }

    public void createGlassBlock(Block glassBlock, Block paneBlock) {
        ResourceLocation glassBlockTexture = modLoc("block/" + name(glassBlock));
        ResourceLocation paneTexture = modLoc("block/" + name(paneBlock) + "_top");
        simpleBlockWithItem(glassBlock, models().cubeAll(name(glassBlock), glassBlockTexture).renderType(RenderType.TRANSLUCENT.name));

        ModelBuilder<?> postModel = models().panePost(name(paneBlock) + "_post", glassBlockTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> sideModel = models().paneSide(name(paneBlock) + "_side", glassBlockTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> sideAltModel = models().paneSideAlt(name(paneBlock) + "_side_alt", glassBlockTexture, paneTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> noSideModel = models().paneNoSide(name(paneBlock) + "_noside", glassBlockTexture).renderType(RenderType.TRANSLUCENT.name);
        ModelBuilder<?> noSideAltModel = models().paneNoSideAlt(name(paneBlock) + "_noside_alt", glassBlockTexture).renderType(RenderType.TRANSLUCENT.name);

        itemModels().getBuilder(name(paneBlock))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", glassBlockTexture).renderType(RenderType.TRANSLUCENT.name);

        getMultipartBuilder(paneBlock)
                .part().modelFile(postModel).addModel()
                .end()
                .part().modelFile(sideModel)
                .addModel().condition(BlockStateProperties.NORTH, true)
                .end()
                .part().modelFile(sideModel).rotationY(90)
                .addModel().condition(BlockStateProperties.EAST, true)
                .end()
                .part().modelFile(sideAltModel)
                .addModel().condition(BlockStateProperties.SOUTH, true)
                .end()
                .part().modelFile(sideAltModel).rotationY(90)
                .addModel().condition(BlockStateProperties.WEST, true)
                .end()
                .part().modelFile(noSideModel)
                .addModel().condition(BlockStateProperties.NORTH, false)
                .end()
                .part().modelFile(noSideAltModel)
                .addModel().condition(BlockStateProperties.EAST, false)
                .end()
                .part().modelFile(noSideAltModel).rotationY(90)
                .addModel().condition(BlockStateProperties.SOUTH, false)
                .end()
                .part().modelFile(noSideModel).rotationY(270)
                .addModel().condition(BlockStateProperties.WEST, false)
                .end();
    }

    public void normalBlock(Block block) {
        simpleBlockWithItem(block, models().cubeAll(name(block), modLoc("block/" + name(block))));
    }


    // Other stuff
    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}