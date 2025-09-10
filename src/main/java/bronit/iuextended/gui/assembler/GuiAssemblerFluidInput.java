package bronit.iuextended.gui.assembler;

import bronit.iuextended.Constants;
import bronit.iuextended.container.assembler.ContainerAssemblerFluidInput;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerFluidInput;
import com.denfop.Localization;
import com.denfop.api.gui.Area;
import com.denfop.gui.GuiIU;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.ArrayList;
import java.util.List;

public class GuiAssemblerFluidInput extends GuiIU<ContainerAssemblerFluidInput> {

    protected final ContainerAssemblerFluidInput container;
    private TileAssemblerFluidInput tile_entity;
    private final ResourceLocation background = new ResourceLocation(
            Constants.MOD_ID,
            "textures/gui/assembler_controller.png"
    );

    public GuiAssemblerFluidInput(ContainerAssemblerFluidInput guiContainer) {
        super(guiContainer);
        this.container = guiContainer;
        this.tile_entity = container.base;
        this.componentList.clear();

        this.addElement(new Area(this, 10, 15, 20, 55) {
            @Override
            protected List<String> getToolTip() {
                FluidTank tank = tile_entity.tank1;
                List<String> stringList = new ArrayList<>();
                if (tank == null) {
                    stringList.add("Tank error! Please report to issues");
                } else {
                    int tank_capacity = tank.getCapacity();
                    FluidStack fs = tank.getFluid();
                    if (fs == null || fs.amount <= 0) {
                        stringList.add("0 / " + tank_capacity + "mB");
                    } else {
                        Fluid fluid = fs.getFluid();
                        if (fluid == null) {
                            stringList.add(Localization.translate("iuextended.invalid_fluid_stack"));
                        } else {
                            stringList.add(fluid.getLocalizedName(fs));
                            stringList.add(fs.amount + " / " + tank_capacity + "mB");
                        }
                    }
                }
                return stringList;
            }
        });

        this.addElement(new Area(this, 35, 15, 20, 55) {
            @Override
            protected List<String> getToolTip() {
                FluidTank tank = tile_entity.tank2;
                List<String> stringList = new ArrayList<>();
                if (tank == null) {
                    stringList.add("Tank error! Please report to issues");
                } else {
                    int tank_capacity = tank.getCapacity();
                    FluidStack fs = tank.getFluid();
                    if (fs == null || fs.amount <= 0) {
                        stringList.add("0 / " + tank_capacity + "mB");
                    } else {
                        Fluid fluid = fs.getFluid();
                        if (fluid == null) {
                            stringList.add(Localization.translate("iuextended.invalid_fluid_stack"));
                        } else {
                            stringList.add(fluid.getLocalizedName(fs));
                            stringList.add(fs.amount + " / " + tank_capacity + "mB");
                        }
                    }
                }
                return stringList;
            }
        });

        this.addElement(new Area(this, 60, 15, 20, 55) {
            @Override
            protected List<String> getToolTip() {
                FluidTank tank = tile_entity.tank3;
                List<String> stringList = new ArrayList<>();
                if (tank == null) {
                    stringList.add("Tank error! Please report to issues");
                } else {
                    int tank_capacity = tank.getCapacity();
                    FluidStack fs = tank.getFluid();
                    if (fs == null || fs.amount <= 0) {
                        stringList.add("0 / " + tank_capacity + "mB");
                    } else {
                        Fluid fluid = fs.getFluid();
                        if (fluid == null) {
                            stringList.add(Localization.translate("iuextended.invalid_fluid_stack"));
                        } else {
                            stringList.add(fluid.getLocalizedName(fs));
                            stringList.add(fs.amount + " / " + tank_capacity + "mB");
                        }
                    }
                }
                return stringList;
            }
        });

        this.addElement(new Area(this, 85, 15, 20, 55) {
            @Override
            protected List<String> getToolTip() {
                FluidTank tank = tile_entity.tank4;
                List<String> stringList = new ArrayList<>();
                if (tank == null) {
                    stringList.add("Tank error! Please report to issues");
                } else {
                    int tank_capacity = tank.getCapacity();
                    FluidStack fs = tank.getFluid();
                    if (fs == null || fs.amount <= 0) {
                        stringList.add("0 / " + tank_capacity + "mB");
                    } else {
                        Fluid fluid = fs.getFluid();
                        if (fluid == null) {
                            stringList.add(Localization.translate("iuextended.invalid_fluid_stack"));
                        } else {
                            stringList.add(fluid.getLocalizedName(fs));
                            stringList.add(fs.amount + " / " + tank_capacity + "mB");
                        }
                    }
                }
                return stringList;
            }
        });
    }

    @Override
    protected void drawBackgroundAndTitle(final float partialTicks, final int mouseX, final int mouseY) {
        this.bindTexture();
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawForegroundLayer(final int par1, final int par2) {
        super.drawForegroundLayer(par1, par2);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }

    @Override
    protected ResourceLocation getTexture() {
        return new ResourceLocation(Constants.MOD_ID, "textures/gui/guismeltery_fuel.png");
    }

}
