package net.nova.nmt.item;

import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.nmt.entity.projectile.ThrownLingeringObsidianPotion;

public class LingeringObsidianPotionItem extends ThrowableObsidianPotionItem {
    public LingeringObsidianPotionItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.LINGERING_POTION_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        return super.use(level, player, hand);
    }

    @Override
    protected AbstractThrownPotion createPotion(ServerLevel serverLevel, LivingEntity livingEntity, ItemStack stack) {
        return new ThrownLingeringObsidianPotion(serverLevel, livingEntity, stack);
    }

    @Override
    protected AbstractThrownPotion createPotion(Level level, Position pos, ItemStack stack) {
        return new ThrownLingeringObsidianPotion(level, pos.x(), pos.y(), pos.z(), stack);
    }
}
