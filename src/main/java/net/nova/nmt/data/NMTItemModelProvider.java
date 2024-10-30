package net.nova.nmt.data;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.client.renderer.NMTItemProperties;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import java.util.List;
import java.util.Map;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItemModelProvider extends ItemModelProvider {
    public static final Map<String, Float> POTION_PREDICATES = Map.of(
            "lava", 1.0f,
            "awfully", 2.0f
    );

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

        String prefix = "";
        if (itemName.startsWith("splash_")) {
            prefix = "splash_";
        } else if (itemName.startsWith("lingering_")) {
            prefix = "lingering_";
        }

        String baseTexture = prefix + "lava_bottle";

        getBuilder(itemName)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", NoMoreThings.rl("item/" + baseTexture));

        // Sort potions to ensure lava (1.0) comes before awfully (2.0)
        List<DeferredHolder<Potion, ? extends Potion>> sortedPotions = NMTPotions.POTIONS.getEntries().stream()
                .sorted((a, b) -> {
                    float predA = POTION_PREDICATES.getOrDefault(a.getKey().location().getPath(), 0.0f);
                    float predB = POTION_PREDICATES.getOrDefault(b.getKey().location().getPath(), 0.0f);
                    return Float.compare(predA, predB); // Normal order to get 1.0 before 2.0
                })
                .toList();

        for (Holder<Potion> potionHolder : sortedPotions) {
            String potion = potionHolder.getKey().location().getPath();
            String potionName = switch (potion) {
                case "lava" -> prefix + potion + "_bottle";
                default -> prefix + potion + "_potion";
            };

            float predicate = POTION_PREDICATES.getOrDefault(potion, 0.0f);

            if (predicate > 0) {  // Only generate override for potions with predicates
                getBuilder(itemName).override()
                        .predicate(NMTItemProperties.potionTypePredicate, predicate)
                        .model(new ModelFile.UncheckedModelFile(NoMoreThings.rl("item/" + potionName)))
                        .end();

                getBuilder("item/" + potionName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", NoMoreThings.rl("item/" + potionName));
            }
        }
    }

    public String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
