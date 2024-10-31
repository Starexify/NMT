package net.nova.nmt.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.client.renderer.NMTItemProperties;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import java.util.concurrent.atomic.AtomicInteger;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItemModelProvider extends ItemModelProvider {
    public NMTItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(NMTItems.OBSIDIAN_GLASS_BOTTLE.get());
        potionItem(NMTItems.OBSIDIAN_POTION.get());
        potionItem(NMTItems.SPLASH_OBSIDIAN_POTION.get());
        potionItem(NMTItems.LINGERING_OBSIDIAN_POTION.get());
    }

    // Models
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

    public String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
