package net.nova.nmt.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownLingeringPotion;
import net.minecraft.world.entity.projectile.ThrownSplashPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.nmt.init.NMTItems;

public class ThrownLingeringObsidianPotion extends ThrownLingeringPotion {
    public ThrownLingeringObsidianPotion(EntityType<? extends ThrownLingeringObsidianPotion> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownLingeringObsidianPotion(Level level, LivingEntity owner, ItemStack item) {
        super(level, owner, item);
    }

    public ThrownLingeringObsidianPotion(Level level, double x, double y, double z, ItemStack item) {
        super(level, x, y, z, item);
    }

    @Override
    protected Item getDefaultItem() {
        return NMTItems.LINGERING_OBSIDIAN_POTION.get();
    }
}
