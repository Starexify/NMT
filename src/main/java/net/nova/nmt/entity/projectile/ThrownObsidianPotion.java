package net.nova.nmt.entity.projectile;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.nova.nmt.init.NMTItems;

public class ThrownObsidianPotion extends ThrownPotion {
    public ThrownObsidianPotion(EntityType<? extends ThrownPotion> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownObsidianPotion(Level level, Player player) {
        super(level, player);
    }

    public ThrownObsidianPotion(Level level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            ItemStack itemstack = this.getItem();
            PotionContents potioncontents = itemstack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            if (potioncontents.hasEffects()) {
                if (this.isLingering()) {
                    this.makeAreaOfEffectCloud(potioncontents);
                }

                int i = potioncontents.potion().isPresent() && potioncontents.potion().get().value().hasInstantEffects() ? 2007 : 2002;
                this.level().levelEvent(i, this.blockPosition(), potioncontents.getColor());
                this.discard();
            }
        }
    }

    private void makeAreaOfEffectCloud(PotionContents potionContents) {
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        if (this.getOwner() instanceof LivingEntity livingentity) {
            areaeffectcloud.setOwner(livingentity);
        }

        areaeffectcloud.setRadius(3.0F);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.setPotionContents(potionContents);
        this.level().addFreshEntity(areaeffectcloud);
    }

    private boolean isLingering() {
        return this.getItem().is(NMTItems.LINGERING_OBSIDIAN_POTION);
    }
}
