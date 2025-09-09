package bronit.iuextended.proxy;

import bronit.iuextended.handlers.ItemHandler;
import bronit.iuextended.handlers.MultiBlockSystemHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(final FMLPreInitializationEvent event) {

        ItemHandler.init();

    }

    public void init(final FMLInitializationEvent event) {

        MultiBlockSystemHandler.init();

    }

    public void postInit(final FMLPostInitializationEvent event) {

    }

}
