package net.nova.nmt.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.nova.nmt.init.NMTItems;
import net.nova.nmt.init.NMTPotions;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class NMTEventBus {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Obsidian Bottle/Lava Bottle DispenserItemBehaviour
            DispenserBlock.registerBehavior(NMTItems.OBSIDIAN_GLASS_BOTTLE.asItem(), new OptionalDispenseItemBehavior() {
                private ItemStack takeLiquid(BlockSource source, ItemStack emptyItem, ItemStack fullItem) {
                    source.level().gameEvent(null, GameEvent.FLUID_PICKUP, source.pos());
                    return this.consumeWithRemainder(source, emptyItem, fullItem);
                }

                @Override
                public ItemStack execute(BlockSource blockSource, ItemStack item) {
                    this.setSuccess(false);
                    ServerLevel serverlevel = blockSource.level();
                    BlockPos blockpos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                    if (serverlevel.getFluidState(blockpos).is(FluidTags.LAVA)) {
                        this.setSuccess(true);
                        return this.takeLiquid(blockSource, item, PotionContents.createItemStack(NMTItems.OBSIDIAN_POTION.get(), NMTPotions.LAVA));
                    } else {
                        return super.execute(blockSource, item);
                    }
                }
            });
            DispenserBlock.registerBehavior(NMTItems.OBSIDIAN_POTION.asItem(), new DefaultDispenseItemBehavior() {
                private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

                @Override
                public ItemStack execute(BlockSource blockSource, ItemStack item) {
                    PotionContents potioncontents = item.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                    if (!potioncontents.is(NMTPotions.LAVA) && !potioncontents.is(NMTPotions.LONG_LAVA) && !potioncontents.is(NMTPotions.STRONG_LAVA)) {
                        return this.defaultDispenseItemBehavior.dispense(blockSource, item);
                    } else {
                        ServerLevel serverlevel = blockSource.level();
                        BlockPos blockpos = blockSource.pos();
                        BlockPos blockpos1 = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                        if (!serverlevel.getBlockState(blockpos1).is(Blocks.NETHERRACK)) {
                            return this.defaultDispenseItemBehavior.dispense(blockSource, item);
                        } else {
                            if (!serverlevel.isClientSide) {
                                for (int i = 0; i < 5; i++) {
                                    serverlevel.sendParticles(
                                            ParticleTypes.LAVA,
                                            (double) blockpos.getX() + serverlevel.random.nextDouble(),
                                            blockpos.getY() + 1,
                                            (double) blockpos.getZ() + serverlevel.random.nextDouble(),
                                            1,
                                            0.0,
                                            0.0,
                                            0.0,
                                            1.0
                                    );
                                }
                            }

                            serverlevel.playSound(null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                            serverlevel.gameEvent(null, GameEvent.FLUID_PLACE, blockpos);
                            serverlevel.setBlockAndUpdate(blockpos1, Blocks.MAGMA_BLOCK.defaultBlockState());
                            return this.consumeWithRemainder(blockSource, item, new ItemStack(NMTItems.OBSIDIAN_GLASS_BOTTLE.get()));
                        }
                    }
                }
            });
            DispenserBlock.registerProjectileBehavior(NMTItems.SPLASH_OBSIDIAN_POTION);
            DispenserBlock.registerProjectileBehavior(NMTItems.LINGERING_OBSIDIAN_POTION);
            DispenserBlock.registerProjectileBehavior(NMTItems.OBSIDIAN_TIPPED_ARROW);

            // Obsidian Bottle/Lava Bottle CauldronInteractions
            CauldronInteraction.LAVA.map().put(NMTItems.OBSIDIAN_GLASS_BOTTLE.get(), (state, level, pos, player, hand, emptyStack) -> {
                if (!level.isClientSide) {
                    Item item = emptyStack.getItem();
                    player.setItemInHand(hand, ItemUtils.createFilledResult(emptyStack, player, new ItemStack(NMTItems.OBSIDIAN_POTION.get())));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            });
            CauldronInteraction.EMPTY.map().put(NMTItems.OBSIDIAN_POTION.get(), (state, level, pos, player, hand, emptyStack) -> {
                PotionContents potioncontents = emptyStack.get(DataComponents.POTION_CONTENTS);
                if (potioncontents != null && potioncontents.is(NMTPotions.LAVA)) {
                    if (!level.isClientSide) {
                        Item item = emptyStack.getItem();
                        player.setItemInHand(hand, ItemUtils.createFilledResult(emptyStack, player, new ItemStack(NMTItems.OBSIDIAN_GLASS_BOTTLE.get())));
                        player.awardStat(Stats.USE_CAULDRON);
                        player.awardStat(Stats.ITEM_USED.get(item));
                        level.setBlockAndUpdate(pos, Blocks.LAVA_CAULDRON.defaultBlockState());
                        level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                        level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                    }
                    return ItemInteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }
            });
        });
    }
}
