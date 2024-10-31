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
    public static final Holder<Potion> STRONG_FORTIFYING = POTIONS.register("strong_fortifying", () -> new Potion("fortifying", new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1)) {
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
    public static final Holder<Potion> QUICKNESS = POTIONS.register("quickness", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 3600)) {
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
    public static final Holder<Potion> LONG_QUICKNESS = POTIONS.register("long_quickness", () -> new Potion("quickness", new MobEffectInstance(MobEffects.DIG_SPEED, 9600)) {
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
    public static final Holder<Potion> STRONG_QUICKNESS = POTIONS.register("strong_quickness", () -> new Potion("quickness", new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 1)) {
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
    public static final Holder<Potion> TITAN = POTIONS.register("titan", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600)) {
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
    public static final Holder<Potion> LONG_TITAN = POTIONS.register("long_titan", () -> new Potion("titan", new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600)) {
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
    public static final Holder<Potion> STRONG_TITAN = POTIONS.register("strong_titan", () -> new Potion("titan", new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 1)) {
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
    public static final Holder<Potion> STARVATION = POTIONS.register("starvation", () -> new Potion(new MobEffectInstance(MobEffects.HUNGER, 1800)) {
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
    public static final Holder<Potion> LONG_STARVATION = POTIONS.register("long_starvation", () -> new Potion("starvation", new MobEffectInstance(MobEffects.HUNGER, 4800)) {
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
    public static final Holder<Potion> STRONG_STARVATION = POTIONS.register("strong_starvation", () -> new Potion("starvation", new MobEffectInstance(MobEffects.HUNGER, 400, 1)) {
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
    public static final Holder<Potion> FLOATING = POTIONS.register("floating", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 1800)) {
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
    public static final Holder<Potion> LONG_FLOATING = POTIONS.register("long_floating", () -> new Potion("floating", new MobEffectInstance(MobEffects.LEVITATION, 4800)) {
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
    public static final Holder<Potion> STRONG_FLOATING = POTIONS.register("strong_floating", () -> new Potion("floating", new MobEffectInstance(MobEffects.LEVITATION, 400, 1)) {
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
    public static final Holder<Potion> EXHAUSTION = POTIONS.register("exhaustion", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1800)) {
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
    public static final Holder<Potion> LONG_EXHAUSTION = POTIONS.register("long_exhaustion", () -> new Potion("exhaustion", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 4800)) {
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
    public static final Holder<Potion> STRONG_EXHAUSTION = POTIONS.register("strong_exhaustion", () -> new Potion("exhaustion", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 1)) {
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
    public static final Holder<Potion> SICKNESS = POTIONS.register("sickness", () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 1800)) {
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
    public static final Holder<Potion> LONG_SICKNESS = POTIONS.register("long_sickness", () -> new Potion("sickness", new MobEffectInstance(MobEffects.CONFUSION, 4800)) {
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
    public static final Holder<Potion> UNLUCK = POTIONS.register("unluck", () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 1800)) {
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
    public static final Holder<Potion> LONG_UNLUCK = POTIONS.register("long_unluck", () -> new Potion("unluck", new MobEffectInstance(MobEffects.UNLUCK, 4800)) {
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
    public static final Holder<Potion> STRONG_UNLUCK = POTIONS.register("strong_unluck", () -> new Potion("unluck", new MobEffectInstance(MobEffects.UNLUCK, 400, 1)) {
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
