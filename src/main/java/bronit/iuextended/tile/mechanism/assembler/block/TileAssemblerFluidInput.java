package bronit.iuextended.tile.mechanism.assembler.block;

import bronit.iuextended.IUECore;
import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockAssembler;
import bronit.iuextended.container.assembler.ContainerAssemblerFluidInput;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerInputFluid;
import bronit.iuextended.tile.mechanism.assembler.gui.GuiAssemblerFluidInputBus;
import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.GuiFactories;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.componets.Fluids;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerFluidInput extends TileEntityMultiBlockElement implements IAssemblerInputFluid, IGuiHolder<PosGuiData> {

    private final Fluids fluids = this.addComponent(new Fluids(this));
    public List<EntityPlayer> entityPlayerList;
    public final Fluids.InternalFluidTank tank1;
    public final Fluids.InternalFluidTank tank2;
    public final Fluids.InternalFluidTank tank3;
    public final Fluids.InternalFluidTank tank4;

    public TileAssemblerFluidInput() {
        this.tank1 = this.fluids.addTank("tank1", 100000);
        this.tank2 = this.fluids.addTank("tank2", 100000);
        this.tank3 = this.fluids.addTank("tank3", 100000);
        this.tank4 = this.fluids.addTank("tank4", 100000);
    }

    public IMultiTileBlock getTeBlock() {
        return BlockAssembler.assembler_input_fluid;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assembler;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip) {
        super.addInformation(stack, tooltip);
        TileAssemblerMain.assemblerMainTooltip(tooltip);
    }

    @Override
    public Fluids.InternalFluidTank getFluidTank() {
        return this.getFluidTank(1);
    }

    public Fluids.InternalFluidTank getFluidTank(int index) {
        switch (index) {
            case 1:
                return this.tank1;
            case 2:
                return this.tank2;
            case 3:
                return this.tank3;
            case 4:
                return this.tank4;
        }
        IUECore.LOGGER.error("Illegal index tank!");
        return null;
    }

    public Fluids getFluid() {
        return this.fluids;
    }

    @Override
    public boolean hasOwnInventory() {
        return true;
    }

    @Override
    public ContainerAssemblerFluidInput getGuiContainer(final EntityPlayer entityPlayer) {
        return new ContainerAssemblerFluidInput(this, entityPlayer);
    }

    @Override
    public boolean onActivated(
            final EntityPlayer player,
            final EnumHand hand,
            final EnumFacing side,
            final float hitX,
            final float hitY,
            final float hitZ
    ) {
        if (this.getWorld().isRemote) {
            return false;
        }
        if (this.getMain().wasActivated() || this.getMain().isFull()) {
            ItemStack heldItem = player.getHeldItem(hand);
            if (heldItem
                    .hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
                return FluidUtil.interactWithFluidHandler(player, hand,
                        this.getFluid()
                                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)
                );
            } else {
                GuiFactories.tileEntity().open(player, pos);
                return true;
            }
        }

        return super.onActivated(player, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        GuiAssemblerFluidInputBus gui = new GuiAssemblerFluidInputBus(this.getGuiContainer(guiData.getPlayer()));
        return gui.createGUI();
    }

}
