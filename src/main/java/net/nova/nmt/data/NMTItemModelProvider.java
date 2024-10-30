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

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItemModelProvider extends ItemModelProvider {
    public NMTItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(NMTItems.OBSIDIAN_GLASS_BOTTLE.get());
        potionItem(NMTItems.OBSIDIAN_POTION.get());
        basicItem(NMTItems.SPLASH_LAVA_BOTTLE.get());
        basicItem(NMTItems.LINGERING_LAVA_BOTTLE.get());
    }

    // Models
    public void potionItem(Item item) {
        // Get the base item name
        String itemName = getItemName(item);

        // Create the base model builder
        getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", NoMoreThings.rl("item/lava_bottle"))
                // Add override for Lava potion
                .override()
                .predicate(NMTItemProperties.potionTypePredicate, 1.0f)
                .model(new ModelFile.UncheckedModelFile(NoMoreThings.rl("item/lava_bottle")))
                .end()
                // Add override for Awfully potion
                .override()
                .predicate(NMTItemProperties.potionTypePredicate, 2.0f)
                .model(new ModelFile.UncheckedModelFile(NoMoreThings.rl("item/awfully_potion")))
                .end();
    }

    public String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
