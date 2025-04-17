package net.nova.nmt.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class OrbModel extends Model {
    public static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/item/heart_of_the_hell.png");
    public final ModelPart heart;

    public OrbModel(ModelPart root) {
        super(root, RenderType::entitySolid);
        this.heart = root.getChild("heart");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition heart = partdefinition.addOrReplaceChild("heart", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 8);
    }
}
