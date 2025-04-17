package net.nova.nmt.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.nova.nmt.client.model.OrbModel;
import net.nova.nmt.init.NMTModelLayers;

public class OrbSpecialRenderer implements NoDataSpecialModelRenderer {
    public final OrbModel model;

    public OrbSpecialRenderer(OrbModel model) {
        this.model = model;
    }

    @Override
    public void render(ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(bufferSource, this.model.renderType(OrbModel.TEXTURE), false, hasFoilType);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<OrbSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new OrbSpecialRenderer.Unbaked());

        @Override
        public MapCodec<OrbSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
            return new OrbSpecialRenderer(new OrbModel(modelSet.bakeLayer(NMTModelLayers.HEART_OF_THE_HELL)));
        }
    }
}
