package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.mob_effect.BurnMobEffect;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);

    public static final Holder<MobEffect> BURN = MOB_EFFECTS.register("burn", () -> new BurnMobEffect(MobEffectCategory.HARMFUL, TextColor.parseColor("#f36000").getOrThrow().getValue()));
}
