package net.nova.nmt.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MODID);

    public static final Holder<Potion> LAVA = POTIONS.register("lava", () -> new Potion(new MobEffectInstance(NMTEffects.BURN, 900)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> LONG_LAVA = POTIONS.register("long_lava", () -> new Potion("lava", new MobEffectInstance(NMTEffects.BURN, 1800)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> STRONG_LAVA = POTIONS.register("strong_lava", () -> new Potion("lava", new MobEffectInstance(NMTEffects.BURN, 432, 1)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> AWFULLY = POTIONS.register("awfully", () -> new Potion() {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> FORTIFYING = POTIONS.register("fortifying", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 3600)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> LONG_FORTIFYING = POTIONS.register("long_fortifying", () -> new Potion("fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 9600)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> STRONG_FORTIFYING = POTIONS.register("strong_fortifying", () -> new Potion("fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1)));
    public static final Holder<Potion> CECITY = POTIONS.register("cecity", () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 900)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> LONG_CECITY = POTIONS.register("long_cecity", () -> new Potion("cecity", new MobEffectInstance(MobEffects.BLINDNESS, 1800)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> DIMNESS = POTIONS.register("dimness", () -> new Potion(new MobEffectInstance(MobEffects.DARKNESS, 900)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
    public static final Holder<Potion> LONG_DIMNESS = POTIONS.register("long_dimness", () -> new Potion("dimness", new MobEffectInstance(MobEffects.DARKNESS, 1800)) {
        @Override
        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("EnderPotionBrewing")) {
                    return true;
                }
            }
            return false;
        }
    });
}
