package bronit.iuextended.api.gui.widgets;

import bronit.iuextended.IUECore.Constants;
import bronit.iuextended.api.gui.sync.FluidTankSyncHandler;
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
import com.cleanroommc.modularui.utils.Color;
import com.cleanroommc.modularui.value.sync.SyncHandler;
import com.cleanroommc.modularui.widget.Widget;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.Nullable;

public class FluidTankWidget extends Widget<FluidTankWidget> implements JeiGhostIngredientSlot<FluidStack>, JeiIngredientProvider {

    private final TextRenderer text_renderer = new TextRenderer();
    private final int width = 20;
    private final int height = 55;
    private final int x;
    private final int y;
    private @Nullable IDrawable overlayTexture = UITexture.fullImage(new ResourceLocation(Constants.MOD_ID, "textures/gui/fluid_tank.png"));
    private @Nullable IDrawable backgroundTexture = UITexture.fullImage(new ResourceLocation(Constants.MOD_ID, "textures/gui/fluid_tank_back.png"));
    private FluidTankSyncHandler sync_handler;

    public FluidTankWidget(int x, int y) {
        this.x = x;
        this.y = y;
        this.left(this.x).top(this.y);
        this.size(width, height);

        this.tooltip().setAutoUpdate(true);
        this.tooltipBuilder((tooltip) -> {

            IFluidTank tank = this.getFluidTank();
            if (this.sync_handler.getValue() != null) {

                FluidStack fluid = this.sync_handler.getValue();

                if (fluid != null) {
                    tooltip.addLine(IKey.str(fluid.getLocalizedName()));
                    tooltip.addLine(IKey.str(tank.getFluidAmount() + " / " + tank.getCapacity()));
                } else {
                    tooltip.addLine(IKey.str("0 / " + tank.getCapacity()));
                }
            }
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
//        IUECore.LOGGER.info(this.getFluidTank() + " aaaaa " + this.getFluidTank().getFluid());
        if (this.backgroundTexture != null) {
            this.backgroundTexture.draw(context, 4, 4, width - 8, height - 8, widgetTheme);
        }
        IFluidTank fluidTank = getFluidTank();
        FluidStack content = this.sync_handler.getFluidTank().getFluid();
        if (content != null) {
            float fluid_height = (float) this.height * content.amount / fluidTank.getCapacity();
            GuiDraw.drawFluidTexture(content, (int) 4, this.height - fluid_height, this.width - 8, fluid_height, 0);
        }
        if (this.overlayTexture != null) {
            this.overlayTexture.drawAtZero(context, getArea(), widgetTheme);
        }
    }

    public IFluidTank getFluidTank() {
        return this.sync_handler.getFluidTank();
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

    @Nullable
    public FluidStack getFluidStack() {
        return this.sync_handler.getFluidTank() == null ? null : this.sync_handler.getFluidTank().getFluid();
    }

    @Override
    public boolean isValidSyncHandler(SyncHandler syncHandler) {
        this.sync_handler = (FluidTankSyncHandler)this.castIfTypeElseNull(syncHandler, FluidTankSyncHandler.class);
        return this.sync_handler != null;
    }

    public FluidTankWidget syncHandler(IFluidTank fluidTank) {
        return this.syncHandler(new FluidTankSyncHandler(fluidTank));
    }

    public FluidTankWidget syncHandler(FluidTankSyncHandler syncHandler) {
        this.setSyncHandler(syncHandler);
        this.sync_handler = syncHandler;
        return this;
    }

}
