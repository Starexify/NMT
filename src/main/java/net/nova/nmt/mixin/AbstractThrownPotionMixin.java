package net.nova.nmt.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.projectile.AbstractThrownPotion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.nova.nmt.init.NMTPotions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractThrownPotion.class)
public abstract class AbstractThrownPotionMixin {
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;levelEvent(ILnet/minecraft/core/BlockPos;I)V"), index = 2)
    private int applyAwfullyColor(int color, @Local PotionContents potioncontents) {
        if (potioncontents.is(NMTPotions.AWFULLY)) return TextColor.parseColor("#f36000").getOrThrow().getValue();
        return color;
    }
}
