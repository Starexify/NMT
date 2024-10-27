package net.nova.nmt;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.nmt.data.DataGenerators;
import net.nova.nmt.init.CreativeTab;
import net.nova.nmt.init.NMTBlocks;
import net.nova.nmt.init.NMTItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.nmt.NoMoreThings.MODID;

@Mod(MODID)
public class NoMoreThings {
    public static final String MODID = "nmt";
    public static final Logger logger = LoggerFactory.getLogger(NoMoreThings.class);

    public NoMoreThings(IEventBus bus) {
        NMTItems.ITEMS.register(bus);
        NMTBlocks.BLOCKS.register(bus);
        CreativeTab.CREATIVE_TAB.register(bus);

        bus.addListener(DataGenerators::gatherData);
    }
}