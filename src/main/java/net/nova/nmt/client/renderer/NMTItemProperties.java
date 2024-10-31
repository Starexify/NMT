package net.nova.nmt.client.renderer;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NMTItemProperties {
    public static ResourceLocation potionTypePredicate = NoMoreThings.rl("potion_type");

    public static void addCustomItemProperties() {
        makePotion(NMTItems.OBSIDIAN_POTION.get());
        makePotion(NMTItems.SPLASH_OBSIDIAN_POTION.get());
        makePotion(NMTItems.LINGERING_OBSIDIAN_POTION.get());
        makePotion(NMTItems.OBSIDIAN_TIPPED_ARROW.get());
    }

    public static void makePotion(Item item) {
        ItemProperties.register(item, potionTypePredicate, (stack, level, entity, seed) -> {
            PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
            if (potionContents == null) return 0.0F;

            AtomicInteger counter = new AtomicInteger(0);
            AtomicReference<Float> result = new AtomicReference<>(0.0F);

            NMTPotions.POTIONS.getEntries().forEach(potion -> {
                int currentId = counter.incrementAndGet();
                if (potionContents.is(potion)) {
                    result.set((float) currentId);
                }
            });

            return result.get();
        });
    }
}
