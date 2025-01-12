package net.nova.nmt.data.models;

import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngle;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngleState;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
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
                    String potionPath = potionHolder.unwrapKey().get().location().getPath();
                    return !potionPath.startsWith("long_") && !potionPath.startsWith("strong_");
                })
                .forEach(potionHolder -> {
                    String potion = potionHolder.unwrapKey().get().location().getPath();

                    String potionName = switch (potion) {
                        case "lava" -> prefix + potion + "_bottle";
                        default -> prefix + potion + "_potion";
                    };

                    ItemModel.Unbaked potionModel = ItemModelUtils.plainModel(
                            ModelTemplates.FLAT_ITEM.create(NoMoreThings.rl(potionName), TextureMapping.layer0(NoMoreThings.rl(potionName)), this.modelOutput));
                    list.add(ItemModelUtils.when(potionHolder.unwrapKey().get(), potionModel));
                });

        ItemModel.Unbaked itemmodel$unbaked1;
        ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), this.modelOutput);
        itemmodel$unbaked1 = ItemModelUtils.plainModel(resourcelocation);

        this.itemModelOutput.accept(potionItem, ItemModelUtils.select(new PotionContentsProperty(), itemmodel$unbaked1, list));
    }

    public void generateEnderTippedArrow(Item arrowItem) {
        generateFlatItem(arrowItem, ModelTemplates.FLAT_ITEM);
    }

/*
    public void potionItem(Item item) {
        String itemName = getItemName(item);
        float[] potionId = {0.0f};

        String baseTexture = prefix + "lava_bottle";

        getBuilder(itemName)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", NoMoreThings.rl("item/" + baseTexture));

        NMTPotions.POTIONS.getEntries().forEach(potionHolder -> {
            String potion = potionHolder.getKey().location().getPath();
            if (potion.startsWith("long_") || potion.startsWith("strong_")) {
                potion = potion.substring(potion.indexOf('_') + 1);
            }

            String potionName = switch (potion) {
                case "lava" -> prefix + potion + "_bottle";
                default -> prefix + potion + "_potion";
            };

            getBuilder(itemName).override()
                    .predicate(NMTItemProperties.potionTypePredicate, potionId[0] + 1.0f)
                    .model(new ModelFile.UncheckedModelFile(NoMoreThings.rl("item/" + potionName)))
                    .end();

            getBuilder("item/" + potionName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", NoMoreThings.rl("item/" + potionName));

            potionId[0] += 1.0f;
        });
    }
    public void tippedArrowItem(Item item) {
        float[] potionId = {0.0f};

        String baseTexture = "lava_tipped_arrow";

        getBuilder(itemName)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", NoMoreThings.rl("item/" + baseTexture));

        NMTPotions.POTIONS.getEntries().forEach(potionHolder -> {
            String potion = potionHolder.getKey().location().getPath();
            if (potion.startsWith("long_") || potion.startsWith("strong_")) {
                potion = potion.substring(potion.indexOf('_') + 1);
            }

            String potionName = potion + "_tipped_arrow";

            getBuilder(itemName).override()
                    .predicate(NMTItemProperties.potionTypePredicate, potionId[0] + 1.0f)
                    .model(new ModelFile.UncheckedModelFile(NoMoreThings.rl("item/" + potionName)))
                    .end();

            getBuilder("item/" + potionName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", NoMoreThings.rl("item/" + potionName));

            potionId[0] += 1.0f;
        });
    }

    */
}
