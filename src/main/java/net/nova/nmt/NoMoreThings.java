package net.nova.nmt;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.nova.nmt.data.DataGenerators;
import net.nova.nmt.init.*;
import net.nova.nmt.recipe.EnderPotionBrewing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.nmt.NoMoreThings.MODID;

@Mod(MODID)
public class NoMoreThings {
    public static final String MODID = "nmt";
    public static final Logger logger = LoggerFactory.getLogger(NoMoreThings.class);

    public static EnderPotionBrewing INSTANCE;
    private boolean itemsRegistered = false;
    private boolean potionsRegistered = false;

    public NoMoreThings(IEventBus bus) {
        NMTItems.ITEMS.register(bus);
        NMTBlocks.BLOCKS.register(bus);
        CreativeTab.CREATIVE_TAB.register(bus);
        NMTPotions.POTIONS.register(bus);
        NMTEffects.MOB_EFFECTS.register(bus);
        NMTBlockEntities.BLOCK_ENTITIES.register(bus);
        NMTMenuType.MENUS.register(bus);
        NMTFeature.FEATURES.register(bus);
        NMTEntityType.ENTITY_TYPES.register(bus);
        NMTRecipeSerializers.RECIPE_SERIALIZERS.register(bus);

        bus.addListener(DataGenerators::gatherData);
        bus.addListener(this::onRegisterComplete);
    }

    private void onRegisterComplete(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            itemsRegistered = true;
        } else if (event.getRegistryKey().equals(Registries.POTION)) {
            potionsRegistered = true;
        }

        if (itemsRegistered && potionsRegistered) {
            init();
        }
    }

    private void init() {
        if (INSTANCE == null) {
            INSTANCE = EnderPotionBrewing.bootstrap(FeatureFlags.DEFAULT_FLAGS);
            logger.info("Initialized EnderPotionBrewing system");
        }
    }

    public static EnderPotionBrewing getEnderBrewing() {
        if (INSTANCE == null) {
            throw new IllegalStateException("EnderPotionBrewing not initialized yet! This is likely due to accessing it too early in the mod lifecycle.");
        }
        return INSTANCE;
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
