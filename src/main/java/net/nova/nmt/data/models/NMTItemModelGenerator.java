package net.nova.nmt.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.client.renderer.item.PotionContentsProperty;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class NMTItemModelGenerator extends ItemModelGenerators {
    public NMTItemModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(NMTItems.OBSIDIAN_GLASS_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        generateEnderPotion(NMTItems.OBSIDIAN_POTION.get(), "");
        generateEnderPotion(NMTItems.SPLASH_OBSIDIAN_POTION.get(), "splash_");
        generateEnderPotion(NMTItems.LINGERING_OBSIDIAN_POTION.get(), "lingering_");
        generateEnderTippedArrow(NMTItems.OBSIDIAN_TIPPED_ARROW.get());
        /*basicItem(NMTItems.TEAR_OF_THE_NETHER.get());
        basicItem(NMTItems.BLAZING_WITHER_SOUL.get());*/
    }

    public void generateEnderPotion(Item potionItem, String prefix) {
        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(potionItem);
        ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(potionItem);
        List<SelectItemModel.SwitchCase<ResourceKey<Potion>>> list = new ArrayList<>(NMTPotions.POTIONS.getEntries().size());

        NMTPotions.POTIONS.getEntries().stream()
                .filter(potionHolder -> {
                    String potionPath = potionHolder.getKey().location().getPath();
                    return !potionPath.startsWith("long_") && !potionPath.startsWith("strong_");
                })
                .forEach(potionHolder -> {
                    String potion = potionHolder.getKey().location().getPath();

                    String potionName = switch (potion) {
                        case "lava" -> prefix + potion + "_bottle";
                        default -> prefix + potion + "_potion";
                    };

                    ItemModel.Unbaked potionModel = ItemModelUtils.plainModel(
                            ModelTemplates.FLAT_ITEM.create(NoMoreThings.rl(potionName), TextureMapping.layer0(NoMoreThings.rl(potionName).withPrefix("item/")), this.modelOutput));
                    list.add(ItemModelUtils.when(potionHolder.getKey(), potionModel));
                });

        ItemModel.Unbaked basicModel;
        ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), this.modelOutput);
        basicModel = ItemModelUtils.plainModel(resourcelocation);

        this.itemModelOutput.accept(potionItem, ItemModelUtils.select(new PotionContentsProperty(), basicModel, list));
    }

    public void generateEnderTippedArrow(Item arrowItem) {
        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(arrowItem);
        ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(arrowItem);
        List<SelectItemModel.SwitchCase<ResourceKey<Potion>>> list = new ArrayList<>(NMTPotions.POTIONS.getEntries().size());

        NMTPotions.POTIONS.getEntries().stream()
                .filter(potionHolder -> {
                    String potionPath = potionHolder.getKey().location().getPath();
                    return !potionPath.startsWith("long_") && !potionPath.startsWith("strong_");
                })
                .forEach(potionHolder -> {
                    String potion = potionHolder.getKey().location().getPath();
                    String potionName = potion + "_tipped_arrow";

                    ItemModel.Unbaked potionModel = ItemModelUtils.plainModel(
                            ModelTemplates.FLAT_ITEM.create(NoMoreThings.rl(potionName), TextureMapping.layer0(NoMoreThings.rl(potionName).withPrefix("item/")), this.modelOutput));
                    list.add(ItemModelUtils.when(potionHolder.getKey(), potionModel));
                });

        ItemModel.Unbaked basicModel;
        ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), this.modelOutput);
        basicModel = ItemModelUtils.plainModel(resourcelocation);

        this.itemModelOutput.accept(arrowItem, ItemModelUtils.select(new PotionContentsProperty(), basicModel, list));
    }
}
