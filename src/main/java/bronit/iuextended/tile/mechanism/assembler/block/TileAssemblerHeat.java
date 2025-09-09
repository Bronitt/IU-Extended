package bronit.iuextended.tile.mechanism.assembler.block;

import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockAssembler;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerHeat;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerHeat extends TileEntityMultiBlockElement implements IAssemblerHeat {

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final List<String> tooltip) {
        super.addInformation(stack, tooltip);
        TileAssemblerMain.assemblerMainTooltip(tooltip);
    }

    public IMultiTileBlock getTeBlock() {
        return BlockAssembler.assembler_heat;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assembler;
    }


}
