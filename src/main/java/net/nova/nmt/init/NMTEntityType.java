package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.NoMoreThings;
import net.nova.nmt.entity.projectile.ThrownObsidianPotion;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final Supplier<EntityType<ThrownObsidianPotion>> OBSIDIAN_POTION = register("obsidian_potion",
            EntityType.Builder.<ThrownObsidianPotion>of(ThrownObsidianPotion::new, MobCategory.MISC)
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

    public static <T extends Entity> Supplier<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(ResourceKey.create(Registries.ENTITY_TYPE, NoMoreThings.rl(name)).location().getPath(),
                () -> builder.build(ResourceKey.create(Registries.ENTITY_TYPE, NoMoreThings.rl(name)))
        );
    }
}
