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

    public static final Holder<Potion> LAVA = POTIONS.register("lava", () -> new Potion("lava", new MobEffectInstance(NMTEffects.BURN, 900)) {
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
    public static final Holder<Potion> LONG_LAVA = POTIONS.register("long_lava", () -> new Potion("long_lava", new MobEffectInstance(NMTEffects.BURN, 1800)) {
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
    public static final Holder<Potion> STRONG_LAVA = POTIONS.register("strong_lava", () -> new Potion("strong_lava", new MobEffectInstance(NMTEffects.BURN, 432, 1)) {
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
    public static final Holder<Potion> AWFULLY = POTIONS.register("awfully", () -> new Potion("awfully") {
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
    public static final Holder<Potion> FORTIFYING = POTIONS.register("fortifying", () -> new Potion("fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 3600)) {
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
    public static final Holder<Potion> LONG_FORTIFYING = POTIONS.register("long_fortifying", () -> new Potion("long_fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 9600)) {
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
    public static final Holder<Potion> STRONG_FORTIFYING = POTIONS.register("strong_fortifying", () -> new Potion("strong_fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1)) {
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
    public static final Holder<Potion> CECITY = POTIONS.register("cecity", () -> new Potion("cecity", new MobEffectInstance(MobEffects.BLINDNESS, 900)) {
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
    public static final Holder<Potion> LONG_CECITY = POTIONS.register("long_cecity", () -> new Potion("long_cecity", new MobEffectInstance(MobEffects.BLINDNESS, 1800)) {
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
    public static final Holder<Potion> DIMNESS = POTIONS.register("dimness", () -> new Potion("dimness", new MobEffectInstance(MobEffects.DARKNESS, 900)) {
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
    public static final Holder<Potion> LONG_DIMNESS = POTIONS.register("long_dimness", () -> new Potion("long_dimness", new MobEffectInstance(MobEffects.DARKNESS, 1800)) {
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
    public static final Holder<Potion> QUICKNESS = POTIONS.register("quickness", () -> new Potion("quickness", new MobEffectInstance(MobEffects.DIG_SPEED, 3600)) {
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
    public static final Holder<Potion> LONG_QUICKNESS = POTIONS.register("long_quickness", () -> new Potion("long_quickness", new MobEffectInstance(MobEffects.DIG_SPEED, 9600)) {
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
    public static final Holder<Potion> STRONG_QUICKNESS = POTIONS.register("strong_quickness", () -> new Potion("strong_quickness", new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 1)) {
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
    public static final Holder<Potion> TITAN = POTIONS.register("titan", () -> new Potion("titan", new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600)) {
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
    public static final Holder<Potion> LONG_TITAN = POTIONS.register("long_titan", () -> new Potion("long_titan", new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600)) {
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
    public static final Holder<Potion> STRONG_TITAN = POTIONS.register("strong_titan", () -> new Potion("strong_titan", new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 1)) {
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
    public static final Holder<Potion> STARVATION = POTIONS.register("starvation", () -> new Potion("starvation", new MobEffectInstance(MobEffects.HUNGER, 1800)) {
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
    public static final Holder<Potion> LONG_STARVATION = POTIONS.register("long_starvation", () -> new Potion("long_starvation", new MobEffectInstance(MobEffects.HUNGER, 4800)) {
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
    public static final Holder<Potion> STRONG_STARVATION = POTIONS.register("strong_starvation", () -> new Potion("strong_starvation", new MobEffectInstance(MobEffects.HUNGER, 400, 1)) {
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
    public static final Holder<Potion> FLOATING = POTIONS.register("floating", () -> new Potion("floating", new MobEffectInstance(MobEffects.LEVITATION, 1800)) {
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
    public static final Holder<Potion> LONG_FLOATING = POTIONS.register("long_floating", () -> new Potion("long_floating", new MobEffectInstance(MobEffects.LEVITATION, 4800)) {
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
    public static final Holder<Potion> STRONG_FLOATING = POTIONS.register("strong_floating", () -> new Potion("strong_floating", new MobEffectInstance(MobEffects.LEVITATION, 400, 1)) {
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
    public static final Holder<Potion> EXHAUSTION = POTIONS.register("exhaustion", () -> new Potion("exhaustion", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1800)) {
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
    public static final Holder<Potion> LONG_EXHAUSTION = POTIONS.register("long_exhaustion", () -> new Potion("long_exhaustion", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 4800)) {
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
    public static final Holder<Potion> STRONG_EXHAUSTION = POTIONS.register("strong_exhaustion", () -> new Potion("strong_exhaustion", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 1)) {
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
    public static final Holder<Potion> SICKNESS = POTIONS.register("sickness", () -> new Potion("sickness", new MobEffectInstance(MobEffects.CONFUSION, 1800)) {
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
    public static final Holder<Potion> LONG_SICKNESS = POTIONS.register("long_sickness", () -> new Potion("long_sickness", new MobEffectInstance(MobEffects.CONFUSION, 4800)) {
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
    public static final Holder<Potion> UNLUCK = POTIONS.register("unluck", () -> new Potion("unluck", new MobEffectInstance(MobEffects.UNLUCK, 6000)) {
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
    public static final Holder<Potion> WITHERING = POTIONS.register("withering", () -> new Potion("withering", new MobEffectInstance(MobEffects.WITHER, 1800)) {
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
    public static final Holder<Potion> LONG_WITHERING = POTIONS.register("long_withering", () -> new Potion("long_withering", new MobEffectInstance(MobEffects.WITHER, 4800)) {
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
    public static final Holder<Potion> STRONG_WITHERING = POTIONS.register("strong_withering", () -> new Potion("strong_withering", new MobEffectInstance(MobEffects.WITHER, 400, 1)) {
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
