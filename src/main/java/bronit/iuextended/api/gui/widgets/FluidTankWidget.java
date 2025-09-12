package bronit.iuextended.api.gui.widgets;

import bronit.iuextended.Constants;
import com.cleanroommc.modularui.ModularUI;
import com.cleanroommc.modularui.api.ITheme;
import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.drawable.GuiDraw;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.drawable.text.TextRenderer;
import com.cleanroommc.modularui.integration.jei.JeiGhostIngredientSlot;
import com.cleanroommc.modularui.integration.jei.JeiIngredientProvider;
import com.cleanroommc.modularui.integration.jei.ModularUIJeiPlugin;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.theme.WidgetSlotTheme;
import com.cleanroommc.modularui.theme.WidgetTheme;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.utils.Color;
import com.cleanroommc.modularui.utils.NumberFormat;
import com.cleanroommc.modularui.widget.Widget;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

public class FluidTankWidget extends Widget<FluidTankWidget> implements JeiGhostIngredientSlot<FluidStack>, JeiIngredientProvider {

    private final TextRenderer text_renderer = new TextRenderer();
    private final IFluidTank fluid_tank;
    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private IDrawable overlayTexture = UITexture.fullImage(new ResourceLocation(Constants.MOD_ID, "textures/gui/fluid_tank.png"));

    public FluidTankWidget(FluidTank fluid_tank, int x, int y) {
        this.fluid_tank = fluid_tank;
        this.width = 20;
        this.height = 55;
        this.x = x;
        this.y = y;
        this.left(this.x).top(this.y);
        this.size(width, height);
        this.tooltip().setAutoUpdate(true);
        this.tooltipBuilder((tooltip) -> {
            FluidStack fluid = this.fluid_tank.getFluid();

            if (fluid != null) {
                tooltip.addLine(IKey.str(fluid.getLocalizedName()));
                tooltip.addLine(IKey.str(this.fluid_tank.getFluidAmount() + " / " + this.fluid_tank.getCapacity()));
            } else tooltip.addLine(IKey.str("0 / " + this.fluid_tank.getCapacity()));
        });

    }

    @Override
    public void onInit() {
        this.text_renderer.setShadow(true);
        this.text_renderer.setScale(0.5f);
        this.text_renderer.setColor(Color.WHITE.main);
        getContext().getJeiSettings().addJeiGhostIngredientSlot(this);
    }

    @Override
    public void draw(ModularGuiContext context, WidgetTheme widgetTheme) {
        IFluidTank fluidTank = getFluidTank();
        FluidStack content = this.fluid_tank.getFluid();
        if (content != null) {
            float fluid_height = this.height * content.amount * 1f / fluidTank.getCapacity();
            GuiDraw.drawFluidTexture(content, this.x - (int) (width / 2), this.y, this.width, fluid_height, 0);
        }
        if (this.overlayTexture != null) {
            this.overlayTexture.drawAtZero(context, getArea(), widgetTheme);
        }
    }

    public IFluidTank getFluidTank() {
        return this.fluid_tank;
    }

    @Override
    public void drawOverlay(ModularGuiContext context, WidgetTheme widgetTheme) {
        if (ModularUI.Mods.JEI.isLoaded() && (ModularUIJeiPlugin.draggingValidIngredient(this) || ModularUIJeiPlugin.hoveringOverIngredient(this))) {
            GlStateManager.colorMask(true, true, true, false);
            drawHighlight(getArea(), isHovering());
            GlStateManager.colorMask(true, true, true, true);
        } else if (isHovering()) {
            GlStateManager.colorMask(true, true, true, false);
            GuiDraw.drawRect(1, 1, getArea().w() - 2, getArea().h() - 2, getSlotHoverColor());
            GlStateManager.colorMask(true, true, true, true);
        }
    }

    public int getSlotHoverColor() {
        WidgetTheme theme = getWidgetTheme(getContext().getTheme());
        if (theme instanceof WidgetSlotTheme) {
            return ((WidgetSlotTheme) theme).getSlotHoverColor();
        }
        return ITheme.getDefault().getFluidSlotTheme().getSlotHoverColor();
    }

    //TODO
    @Override
    public void setGhostIngredient(FluidStack fluidStack) {

    }

    @Override
    public FluidStack castGhostIngredientIfValid(Object ingredient) {
        return (areAncestorsEnabled() && ingredient instanceof FluidStack) ? (FluidStack) ingredient : null;
    }

    @Override
    public Object getIngredient() {
        return getFluidStack();
    }

    public FluidStack getFluidStack() {
        return this.fluid_tank == null ? null : this.fluid_tank.getFluid();
    }

    public void drawTankBackground() {

    }

}
