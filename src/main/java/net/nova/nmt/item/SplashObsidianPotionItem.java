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
import net.nova.nmt.entity.projectile.ThrownSplashObsidianPotion;

public class SplashObsidianPotionItem extends ThrowableObsidianPotionItem {
    public SplashObsidianPotionItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SPLASH_POTION_THROW,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        return super.use(level, player, hand);
    }

    @Override
    protected AbstractThrownPotion createPotion(ServerLevel serverLevel, LivingEntity livingEntity, ItemStack stack) {
        return new ThrownSplashObsidianPotion(serverLevel, livingEntity, stack);
    }

    @Override
    protected AbstractThrownPotion createPotion(Level level, Position pos, ItemStack stack) {
        return new ThrownSplashObsidianPotion(level, pos.x(), pos.y(), pos.z(), stack);
    }
}
