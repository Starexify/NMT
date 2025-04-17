package net.nova.nmt.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.entity.projectile.ThrownLingeringObsidianPotion;
import net.nova.nmt.entity.projectile.ThrownSplashObsidianPotion;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTEntityType {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownSplashObsidianPotion>> SPLASH_OBSIDIAN_POTION = ENTITY_TYPES.registerEntityType("splash_obsidian_potion",
            ThrownSplashObsidianPotion::new, MobCategory.MISC, builder -> builder
                    .noLootTable()
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownLingeringObsidianPotion>> LINGERING_OBSIDIAN_POTION = ENTITY_TYPES.registerEntityType("lingering_obsidian_potion",
            ThrownLingeringObsidianPotion::new, MobCategory.MISC, builder -> builder
                    .noLootTable()
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
    );

/*    public static final Supplier<EntityType<FireballProjectile>> FIREBALL_PROJECTILE = ENTITY_TYPES.register("fireball_projectile",
            () -> EntityType.Builder.<FireballProjectile>of(FireballProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("fireball_projectile")
    );*/
}
