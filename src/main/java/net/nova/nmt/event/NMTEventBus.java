package net.nova.nmt.event;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.nova.nmt.init.NMTItems;

import static net.nova.nmt.NoMoreThings.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class NMTEventBus {

    @SubscribeEvent
    public static void registerCauldronInteractions(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction.LAVA.map().put(
                    NMTItems.OBSIDIAN_GLASS_BOTTLE.get(),
                    (state, level, pos, player, hand, emptyStack) -> {
                        if (!level.isClientSide) {
                            Item item = emptyStack.getItem();
                            player.setItemInHand(hand, ItemUtils.createFilledResult(emptyStack, player, new ItemStack(NMTItems.LAVA_BOTTLE.get())));
                            player.awardStat(Stats.USE_CAULDRON);
                            player.awardStat(Stats.ITEM_USED.get(item));
                            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                            level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
                        }

                        return ItemInteractionResult.sidedSuccess(level.isClientSide);
                    }
            );
            CauldronInteraction.EMPTY.map().put(
                    NMTItems.LAVA_BOTTLE.get(),
                    (state, level, pos, player, hand, emptyStack) -> {
                        /*PotionContents potioncontents = p_329830_.get(DataComponents.POTION_CONTENTS);
                        if (potioncontents != null && potioncontents.is(Potions.WATER)) {*/
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
                    }
            );
        });
    }
}
