package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MODID);

    public static final Holder<Potion> AWFULLY = registerPotion("awfully");

    public static final Holder<Potion> LAVA = registerPotion("lava", new MobEffectInstance(NMTEffects.BURN, 900));

    // Method
    public static Holder<Potion> registerPotion(String name, MobEffectInstance... effects) {
        return POTIONS.register(name, () -> new Potion(effects) {
            @Override
            public boolean isEnabled(FeatureFlagSet enabledFeatures) {
                return false;
            }
        });
    }
}
