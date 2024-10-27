package net.nova.nmt;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.nmt.NoMoreThings.MODID;

@Mod(MODID)
public class NoMoreThings {
    public static final String MODID = "nomorethings";
    public static final Logger logger = LoggerFactory.getLogger(NoMoreThings.class);

    public NoMoreThings(IEventBus bus) {

    }
}
