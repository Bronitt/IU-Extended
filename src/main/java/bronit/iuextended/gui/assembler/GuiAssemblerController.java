package bronit.iuextended.gui.assembler;

import bronit.iuextended.IUECore.Constants;
import bronit.iuextended.container.assembler.ContainerAssemblerController;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerMain;
import com.denfop.Localization;
import com.denfop.api.gui.Area;
import com.denfop.api.gui.Component;
import com.denfop.api.gui.EnumTypeComponent;
import com.denfop.api.gui.GuiComponent;
import com.denfop.componets.ComponentSoundButton;
import com.denfop.componets.HeatComponent;
import com.denfop.gui.GuiIU;
import com.denfop.utils.ModUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiAssemblerController extends GuiIU<ContainerAssemblerController> {

    protected final ContainerAssemblerController container;
    private final ResourceLocation background = new ResourceLocation(
            Constants.MOD_ID,
            "textures/gui/assembler_controller.png"
    );
    private final TileAssemblerMain tile_entity;

    public GuiAssemblerController(ContainerAssemblerController container) {
        super(container);
        this.container = container;
        this.tile_entity = container.base;
        this.componentList.clear();
//        this.ySize = 200;
//        this.xSize = 200;

        this.addComponent(new GuiComponent(this, 160, 14, EnumTypeComponent.SOUND_BUTTON,
                new Component<>(new ComponentSoundButton(this.tile_entity, 10, this.tile_entity))
        ));

        this.addElement(new Area(this, 160, 32, 15, 50) {
            @Override
            protected List<String> getToolTip() {
                final HeatComponent component = tile_entity.heat;
                List<String> stringList = new ArrayList<>();
                stringList.add(ModUtils.getString(component
                        .getEnergy()) + "°C" + "/" + ModUtils.getString(component.getCapacity()) + "°C");
                if (component.need) {
                    stringList.add(Localization.translate("iuextended.need_heat"));
                }
                return stringList;
            }

        });

        this.addElement(new Area(this, 10, 15, 20, 60) {
            @Override
            protected List<String> getToolTip() {
                FluidTank tank = tile_entity.tank1;
                List<String> stringList = new ArrayList<>();
                if (tank == null) {
                    stringList.add(Localization.translate("iuextended.empty_tank"));
                } else {
                    FluidStack fs = tank.getFluid();
                    if (fs == null || fs.amount <= 0) {
                        stringList.add(Localization.translate("iuextended.empty_tank"));
                    } else {
                        Fluid fluid = fs.getFluid();
                        if (fluid == null) {
                            stringList.add(Localization.translate("iuextended.invalid_fluid_stack"));
                        } else {
                            stringList.add(fluid.getLocalizedName(fs) + ": " + fs.amount + " mB");
                        }
                    }
                }
                return stringList;
            }

        });
    }

    @Override
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);


    }

    public ResourceLocation getTexture() {
        return background;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }

}
