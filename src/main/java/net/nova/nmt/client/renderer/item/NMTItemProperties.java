package net.nova.nmt.client.renderer.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

@OnlyIn(Dist.CLIENT)
public class NMTItemProperties {
    public static ResourceLocation potionTypePredicate = NoMoreThings.rl("potion_type");

    public static void addCustomItemProperties() {
        makeObsidianPotions(NMTItems.LAVA_BOTTLE.get());
    }

    private static void makeObsidianPotions(Item item) {
        ItemProperties.register(item, potionTypePredicate, (stack, world, entity, seed) -> {
                    PotionContents potionContents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

                    if (potionContents.is(NMTPotions.LAVA)) return 1.0F;
                    if (potionContents.is(NMTPotions.AWFULLY)) return 2.0F;
                    // Add more potion type checks as needed
                    return 0.0F;
                }
        );
    }
}
