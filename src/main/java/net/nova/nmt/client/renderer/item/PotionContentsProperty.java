package net.nova.nmt.client.renderer.item;

import com.mojang.serialization.Codec;
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
import net.nova.nmt.init.NMTPotions;
import org.jetbrains.annotations.Nullable;

public record PotionContentsProperty() implements SelectItemModelProperty<ResourceKey<Potion>> {
    public static final Codec<ResourceKey<Potion>> VALUE_CODEC = ResourceKey.codec(Registries.POTION);
    public static final SelectItemModelProperty.Type<PotionContentsProperty, ResourceKey<Potion>> TYPE = SelectItemModelProperty.Type.create(MapCodec.unit(new PotionContentsProperty()), VALUE_CODEC);

    @Nullable
    public ResourceKey<Potion> get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null || contents.potion().isEmpty()) {
            return null;
        }
        return contents.potion()
                .filter(holder -> NMTPotions.POTIONS.getEntries().contains(holder))
                .flatMap(holder -> {
                    ResourceKey<Potion> key = holder.getKey();
                    String potionPath = key.location().getPath();
                    if (potionPath.startsWith("long_") || potionPath.startsWith("strong_")) {
                        String baseName = potionPath.substring(potionPath.indexOf('_') + 1);
                        return NMTPotions.POTIONS.getEntries().stream()
                                .filter(p -> p.getKey().location().getPath().equals(baseName))
                                .findFirst()
                                .map(h -> h.unwrapKey().orElse(key));
                    }
                    return holder.unwrapKey();
                })
                .orElse(null);
    }

    @Override
    public SelectItemModelProperty.Type<PotionContentsProperty, ResourceKey<Potion>> type() {
        return TYPE;
    }

    @Override
    public Codec<ResourceKey<Potion>> valueCodec() {
        return VALUE_CODEC;
    }
}
