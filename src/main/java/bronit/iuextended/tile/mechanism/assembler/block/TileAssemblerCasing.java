package bronit.iuextended.tile.mechanism.assembler.block;

import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockAssembler;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerCasing;
import com.denfop.api.multiblock.IMainMultiBlock;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerCasing extends TileEntityMultiBlockElement implements IAssemblerCasing {

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final List<String> tooltip) {
        super.addInformation(stack, tooltip);
        TileAssemblerMain.assemblerMainTooltip(tooltip);
    }

    public IMultiTileBlock getTeBlock() {
        return BlockAssembler.assembler_casing;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assembler;
    }

    @Override
    public boolean isMain() {
        return false;
    }

    @Override
    public int getBlockLevel() {
        return super.getBlockLevel();
    }

    @Override
    public boolean canCreateSystem(IMainMultiBlock mainMultiBlock) {
        return super.canCreateSystem(mainMultiBlock);
    }

    @Override
    public boolean hasOwnInventory() {
        return super.hasOwnInventory();
    }
}
