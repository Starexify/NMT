package net.nova.nmt.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import java.util.function.BiConsumer;

public class NMTItemModelGenerator extends ItemModelGenerators {
    public NMTItemModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(NMTItems.OBSIDIAN_GLASS_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        generateEnderPotion(NMTItems.OBSIDIAN_POTION.get());
        generateEnderPotion(NMTItems.SPLASH_OBSIDIAN_POTION.get());
        generateEnderPotion(NMTItems.LINGERING_OBSIDIAN_POTION.get());
        generateEnderTippedArrow(NMTItems.OBSIDIAN_TIPPED_ARROW.get());
        /*basicItem(NMTItems.TEAR_OF_THE_NETHER.get());
        basicItem(NMTItems.BLAZING_WITHER_SOUL.get());*/
    }

    public void generateEnderPotion(Item potionItem) {
        generateFlatItem(potionItem, ModelTemplates.FLAT_ITEM);
        /*ResourceLocation resourcelocation =
                this.generateLayeredItem(potionItem, ModelLocationUtils.decorateItemModelLocation("potion_overlay"), ModelLocationUtils.getModelLocation(potionItem));
        this.addPotionTint(potionItem, resourcelocation);*/
    }

    public void generateEnderTippedArrow(Item arrowItem) {
        generateFlatItem(arrowItem, ModelTemplates.FLAT_ITEM);
        /*ResourceLocation resourcelocation = this.generateLayeredItem(
                arrowItem, ModelLocationUtils.getModelLocation(arrowItem, "_head"), ModelLocationUtils.getModelLocation(arrowItem, "_base")
        );
        this.addPotionTint(arrowItem, resourcelocation);*/
    }

/*    public void tippedArrowItem(Item item) {
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

    public void potionItem(Item item) {
        String itemName = getItemName(item);
        float[] potionId = {0.0f};

        String prefix;
        if (itemName.startsWith("splash_")) {
            prefix = "splash_";
        } else if (itemName.startsWith("lingering_")) {
            prefix = "lingering_";
        } else {
            prefix = "";
        }

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
    }*/
}
