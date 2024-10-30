package net.nova.nmt.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.nova.nmt.client.renderer.NMTItemProperties;
import net.nova.nmt.gui.ender_brewing_stand.EnderBrewingStandScreen;
import net.nova.nmt.init.NMTMenuType;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NMTClientEventBus {
    // Connect Screen to Menu
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NMTMenuType.ENDER_BREWING_STAND.get(), EnderBrewingStandScreen::new);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            NMTItemProperties.addCustomItemProperties();
        });
    }
}