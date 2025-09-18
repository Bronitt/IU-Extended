package bronit.iuextended.tile.mechanism.assembler.gui;

import bronit.iuextended.api.gui.widgets.FluidTankWidget;
import bronit.iuextended.container.assembler.ContainerAssemblerFluidInput;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerFluidInput;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.denfop.Localization;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class GuiAssemblerFluidInputBus {

    protected final ContainerAssemblerFluidInput container;
    private TileAssemblerFluidInput tile_entity;

    public GuiAssemblerFluidInputBus(ContainerAssemblerFluidInput guiContainer) {
        this.container = guiContainer;
        this.tile_entity = container.base;

    }

    //TODO Слоты для вёдер добавить надо, мб чутка изменить размер интерфейса. НУ и инвентарь не забыть (желательно без костылей)
    public ModularPanel createGUI() {
        ModularPanel panel = ModularPanel.defaultPanel("assembler_fluid_input_bus_panel", 195, 166);
        panel
                .child(IKey.str(Localization.translate(this.tile_entity.getName())).asWidget().center().top(7))
                .child(new FluidTankWidget(this.tile_entity.tank1, 10, 18))
                .child(new FluidTankWidget(this.tile_entity.tank2, 60, 18))
                .child(new FluidTankWidget(this.tile_entity.tank3, 110, 18))
                .child(new FluidTankWidget(this.tile_entity.tank4, 160, 18));
        return panel;
    }

}
