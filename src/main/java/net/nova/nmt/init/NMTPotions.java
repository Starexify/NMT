package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MODID);

    public static final Holder<Potion> BURN = POTIONS.register("lava", () -> new Potion(new MobEffectInstance(NMTEffects.BURN, 3600)));
}