package bronit.iuextended.tile.mechanism.assembler.integration.mekanism.block;

import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockMekaAssembler;
import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerMain;

import bronit.iuextended.tile.mechanism.assembler.integration.mekanism.api.IAssemblerMekaHeat;
import com.denfop.api.multiblock.IMainMultiBlock;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.tiles.mechanism.multiblocks.base.TileEntityMultiBlockElement;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileAssemblerMekaHeat extends TileEntityMultiBlockElement implements IAssemblerMekaHeat {

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final List<String> tooltip) {
        super.addInformation(stack, tooltip);
        TileAssemblerMain.assemblerMainTooltip(tooltip);
    }

    public IMultiTileBlock getTeBlock() {
        return BlockMekaAssembler.assembler_meka_heat;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assemblerMeka;
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
        return true;
    }

}
