package bronit.iuextended.tile.mechanism.assembler.integration.mekanism.block;

import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockMekaAssembler;
import bronit.iuextended.components.mekanism.MekaGasses;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerMain;
import bronit.iuextended.tile.mechanism.assembler.integration.mekanism.api.IAssemblerMekaGasInput;
import com.denfop.IUItem;
import com.denfop.Localization;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.invslot.InvSlot;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerGasInput extends TileEntityMultiBlockElement implements IAssemblerMekaGasInput {
    private final MekaGasses gasses = (MekaGasses)this.addComponent(new MekaGasses(this));
    GasTank tank;

    public TileAssemblerGasInput() {
        this.tank = this.gasses.addTank("tank", 100000, InvSlot.TypeItemSlot.INPUT, MekaGasses.gasPredicate(new Gas[]{}));
    }

    public IMultiTileBlock getTeBlock() {
        return BlockMekaAssembler.assembler_gas_input;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assemblerMeka;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip) {
        super.addInformation(stack, tooltip);
        TileAssemblerMain.assemblerMainTooltip(tooltip);
    }

    public GasTank getGasTank() {
        return this.tank;
    }

    public MekaGasses getGas() {
        return this.gasses;
    }

    @Override
    public boolean hasOwnInventory() {
        return true;
    }

}
