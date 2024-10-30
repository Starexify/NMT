package net.nova.nmt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.nmt.entity.projectile.ThrownObsidianPotion;

import java.util.function.Supplier;

import static net.nova.nmt.NoMoreThings.MODID;

public class NMTEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final Supplier<EntityType<ThrownObsidianPotion>> OBSIDIAN_POTION = ENTITY_TYPES.register("obsidian_potion",
            () -> EntityType.Builder.<ThrownObsidianPotion>of(ThrownObsidianPotion::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("obsidian_potion")
    );
}
