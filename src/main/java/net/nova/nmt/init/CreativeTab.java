package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class CreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static String MAIN_TAB_TITLE = MODID + ".creativetab.main";
    public static String POTIONS_TAB_TITLE = MODID + ".creativetab.potions";

    public static final Holder<CreativeModeTab> MAIN_TAB = CREATIVE_TAB.register("no_more_things_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(NMTBlocks.ENDER_WART_BLOCK.get())).title(Component.translatable(MAIN_TAB_TITLE))
            .withTabsAfter(CreativeTab.POTIONS_TAB.getKey())
            .displayItems((itemDisplayParameters, output) -> {
                // Blocks
                output.accept(NMTBlocks.OBSIDIAN_GLASS);
                output.accept(NMTBlocks.OBSIDIAN_GLASS_PANE);
                output.accept(NMTBlocks.ENDER_BREWING_STAND);

                // Ender Wart
                output.accept(NMTBlocks.ENDER_WART_BLOCK);
                output.accept(NMTItems.ENDER_WART);

            }).build()
    );

    public static final Holder<CreativeModeTab> POTIONS_TAB = CREATIVE_TAB.register("no_more_things_potions_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(NMTItems.OBSIDIAN_GLASS_BOTTLE.get())).title(Component.translatable(POTIONS_TAB_TITLE))
            .displayItems((itemDisplayParameters, output) -> {
                // Obsidian Glass Bottle
                output.accept(NMTItems.OBSIDIAN_GLASS_BOTTLE);
                /// Obsidian Pots
                itemDisplayParameters.holders().lookup(Registries.POTION)
                        .ifPresent(builder -> {
                            generatePotionEffectTypes(output, NMTItems.OBSIDIAN_POTION.get());
                            generatePotionEffectTypes(output, NMTItems.SPLASH_OBSIDIAN_POTION.get());
                            generatePotionEffectTypes(output, NMTItems.LINGERING_OBSIDIAN_POTION.get());
                            generatePotionEffectTypes(output, NMTItems.OBSIDIAN_TIPPED_ARROW.get());
                        });

            }).build()
    );

    private static void generatePotionEffectTypes(CreativeModeTab.Output output, Item item) {
        NMTPotions.POTIONS.getEntries().stream()
                .map(potion -> PotionContents.createItemStack(item, potion))
                .forEach(itemStack -> output.accept(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }
}