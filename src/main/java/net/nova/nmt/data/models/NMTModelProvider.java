package net.nova.nmt.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTModelProvider extends ModelProvider {
    public NMTModelProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new NMTBlockModelGenerator(blockModels.blockStateOutput, blockModels.itemModelOutput, blockModels.modelOutput).run();
        new NMTItemModelGenerator(itemModels.itemModelOutput, itemModels.modelOutput).run();
    }
}
