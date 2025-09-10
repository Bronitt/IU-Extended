package bronit.iuextended.tile.mechanism.assembler.gui;

import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAssemblerMain extends CustomModularScreen {

    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        ModularPanel panel = ModularPanel.defaultPanel("main_panel");
        return panel;
    }

}
