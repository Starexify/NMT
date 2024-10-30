package net.nova.nmt.client.renderer;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

public class NMTItemProperties {
    public static ResourceLocation potionTypePredicate = NoMoreThings.rl("potion_type");

    public static void addCustomItemProperties() {
        makePotion(NMTItems.OBSIDIAN_POTION.get());
    }

    private static void makePotion(Item item) {
        ItemProperties.register(item, potionTypePredicate, (stack, level, entity, seed) -> {
            PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
            if (potionContents != null) {
                if (potionContents.is(NMTPotions.LAVA)) {
                    return 1.0F;
                } else if (potionContents.is(NMTPotions.AWFULLY)) {
                    return 2.0F;
                }
            }
            return 0.0F;
        });
    }
}
