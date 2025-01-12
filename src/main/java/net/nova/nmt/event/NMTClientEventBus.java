package net.nova.nmt.event;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.client.renderer.item.PotionContentsProperty;
import net.nova.nmt.gui.ender_brewing_stand.EnderBrewingStandScreen;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTMenuType;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NMTClientEventBus {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(NMTBlocks.ENDER_BREWING_STAND.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(NMTBlocks.ENDER_WART.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(NMTBlocks.OBSIDIAN_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(NMTBlocks.OBSIDIAN_GLASS_PANE.get(), RenderType.translucent());
    }


    // Connect Screen to Menu
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NMTMenuType.ENDER_BREWING_STAND.get(), EnderBrewingStandScreen::new);
    }

    // Item Property
    @SubscribeEvent
    public static void addItemProperty(RegisterSelectItemModelPropertyEvent event) {
        event.register(NoMoreThings.rl("potion_content"), PotionContentsProperty.TYPE);
    }
}