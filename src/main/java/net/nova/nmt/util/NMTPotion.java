package net.nova.nmt.util;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.Potion;

public class NMTPotion extends Potion {
    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return false;
    }
}
