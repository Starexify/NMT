package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

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

                // Items
                output.accept(NMTItems.OBSIDIAN_GLASS_BOTTLE);
                output.accept(NMTItems.LAVA_BOTTLE);
                output.accept(NMTItems.AWFULLY_POTION);

                // Ender Wart
                output.accept(NMTBlocks.ENDER_WART_BLOCK);
                output.accept(NMTItems.ENDER_WART);

            }).build()
    );
}