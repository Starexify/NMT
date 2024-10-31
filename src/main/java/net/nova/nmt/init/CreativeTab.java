package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class CreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static String NO_MORE_THINGS_TAB_TITLE = MODID + ".creativetab";

    public static final Supplier<CreativeModeTab> NO_MORE_THINGS_TAB = CREATIVE_TAB.register("big_swords_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(NMTBlocks.OBSIDIAN_GLASS.get()))
            .title(Component.translatable(NO_MORE_THINGS_TAB_TITLE))
            .displayItems((itemDisplayParameters, output) -> {
                // Blocks
                output.accept(NMTBlocks.OBSIDIAN_GLASS);
                output.accept(NMTBlocks.OBSIDIAN_GLASS_PANE);
                output.accept(NMTBlocks.ENDER_BREWING_STAND);

                // Ender Wart
                output.accept(NMTBlocks.ENDER_WART_BLOCK);
                output.accept(NMTItems.ENDER_WART);

                // Obsidian Glass Bottle
                output.accept(NMTItems.OBSIDIAN_GLASS_BOTTLE);
                /// Obsidian Pots
                itemDisplayParameters.holders().lookup(Registries.POTION)
                        .ifPresent(builder -> {
                            generatePotionEffectTypes(output, NMTItems.OBSIDIAN_POTION.get());
                            generatePotionEffectTypes(output, NMTItems.SPLASH_OBSIDIAN_POTION.get());
                            generatePotionEffectTypes(output, NMTItems.LINGERING_OBSIDIAN_POTION.get());
                        });

            }).build()
    );

    private static void generatePotionEffectTypes(CreativeModeTab.Output output, Item item) {
        NMTPotions.POTIONS.getEntries().stream()
                .map(potion -> PotionContents.createItemStack(item, potion))
                .forEach(itemStack -> output.accept(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }
}