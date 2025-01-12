package net.nova.nmt.client.renderer.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.nmt.init.NMTPotions;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public record PotionContentsProperty() implements SelectItemModelProperty<ResourceKey<Potion>> {
    public static final SelectItemModelProperty.Type<PotionContentsProperty, ResourceKey<Potion>> TYPE;

    public PotionContentsProperty() {
    }

    @Nullable
    public ResourceKey<Potion> get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null || contents.potion().isEmpty()) {
            return null;
        }
        return contents.potion().get().unwrapKey().orElse(null);
    }

    public SelectItemModelProperty.Type<PotionContentsProperty, ResourceKey<Potion>> type() {
        return TYPE;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new PotionContentsProperty()), ResourceKey.codec(Registries.POTION));
    }
}
