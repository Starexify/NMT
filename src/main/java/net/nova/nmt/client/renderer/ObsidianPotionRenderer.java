package net.nova.nmt.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.nova.nmt.NoMoreThings;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Optional;

public class ObsidianPotionRenderer extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation BASE_TEXTURE = NoMoreThings.rl("textures/item/obsidian_potion.png");
    private static final ResourceLocation OVERLAY_TEXTURE = NoMoreThings.rl("textures/item/obsidian_potion_overlay.png");

    public ObsidianPotionRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // Get the vanilla item renderer
        poseStack.pushPose();

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Render base potion bottle
        BakedModel baseModel = itemRenderer.getItemModelShaper().getModelManager()
                .getModel(new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(stack.getItem()), "inventory"));

        itemRenderer.render(stack, transformType, false, poseStack, buffer, combinedLight,
                combinedOverlay, baseModel);

        // Get potion color from contents
        PotionContents contents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        int color = getPotionColor(contents);

        // Render colored overlay
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(OVERLAY_TEXTURE));
        Matrix4f matrix = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal();

        poseStack.popPose();
    }

    private int getPotionColor(PotionContents contents) {
        Optional<Holder<Potion>> potionType = contents.potion();
        if (potionType.isPresent()) {
            ResourceLocation potionKey = potionType.get().unwrapKey()
                    .map(ResourceKey::location)
                    .orElse(ResourceLocation.withDefaultNamespace("empty"));

            return switch (potionKey.toString()) {
                case "nmt:burn" -> 0xFF5500; // Orange color for lava
                case "minecraft:fire_resistance" -> 0xE49A3A;
                default -> 0x385DC6;
            };
        }
        return 0x385DC6;
    }

}
