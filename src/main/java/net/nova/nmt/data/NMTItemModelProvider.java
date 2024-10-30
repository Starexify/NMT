package net.nova.nmt.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.nmt.init.NMTItems;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTItemModelProvider extends ItemModelProvider {
    public NMTItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(NMTItems.OBSIDIAN_GLASS_BOTTLE.get());
        basicItem(NMTItems.LAVA_BOTTLE.get());
        basicItem(NMTItems.SPLASH_LAVA_BOTTLE.get());
        basicItem(NMTItems.LINGERING_LAVA_BOTTLE.get());
        basicItem(NMTItems.AWFULLY_POTION.get());
    }

    // Models
    public String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
