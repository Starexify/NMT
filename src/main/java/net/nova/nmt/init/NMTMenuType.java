package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.gui.ender_brewing_stand.EnderBrewingStandMenu;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MODID);

    public static Supplier<MenuType<EnderBrewingStandMenu>> ENDER_BREWING_STAND = MENUS.register("ender_brewing_stand", () -> IMenuTypeExtension.create(EnderBrewingStandMenu::new));
}
