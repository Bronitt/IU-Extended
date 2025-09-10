package bronit.iuextended.tile.mechanism.assembler.gui;

import bronit.iuextended.container.assembler.ContainerAssemblerFluidInput;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerFluidInput;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.screen.ModularPanel;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class GuiAssemblerFluidInputBus {

    protected final ContainerAssemblerFluidInput container;
    private TileAssemblerFluidInput tile_entity;

    public GuiAssemblerFluidInputBus(ContainerAssemblerFluidInput guiContainer) {
        this.container = guiContainer;
        this.tile_entity = container.base;

    }

    public ModularPanel createGUI() {
        ModularPanel panel = ModularPanel.defaultPanel("assembler_fluid_input_bus_panel");
        panel.child(IKey.str("Test").asWidget().top(7).left(7));
        return panel;
    }

}
