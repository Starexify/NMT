package net.nova.nmt.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.nova.nmt.client.renderer.ObsidianPotionRenderer;
import net.nova.nmt.gui.ender_brewing_stand.EnderBrewingStandScreen;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTMenuType;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NMTClientEventBus {
    // Connect Screen to Menu
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NMTMenuType.ENDER_BREWING_STAND.get(), EnderBrewingStandScreen::new);
    }

    /*    @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                NMTItemProperties.addCustomItemProperties();
            });
        }*/
    private static ObsidianPotionRenderer potionRenderer;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            potionRenderer = new ObsidianPotionRenderer(
                    Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                    Minecraft.getInstance().getEntityModels()
            );
        });
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return potionRenderer;
                    }
                }, NMTItems.LAVA_BOTTLE);
    }
}
