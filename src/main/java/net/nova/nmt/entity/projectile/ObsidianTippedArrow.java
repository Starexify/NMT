package net.nova.nmt.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class ObsidianTippedArrow extends Arrow {
    public ObsidianTippedArrow(EntityType<? extends Arrow> entityType, Level level) {
        super(entityType, level);
    }
}
