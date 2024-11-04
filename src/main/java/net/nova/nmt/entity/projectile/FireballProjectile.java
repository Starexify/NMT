package net.nova.nmt.entity.projectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireballProjectile extends AbstractHurtingProjectile {
    public FireballProjectile(EntityType<? extends FireballProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public FireballProjectile(EntityType<? extends FireballProjectile> entityType, double x, double y, double z, Vec3 movement, Level level) {
        super(entityType, x, y, z, movement, level);
    }

    public FireballProjectile(EntityType<? extends FireballProjectile> entityType, LivingEntity owner, Vec3 movement, Level level) {
        super(entityType, owner, movement, level);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
}
