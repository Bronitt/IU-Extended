package bronit.iuextended.api.tile;

import bronit.iuextended.IUECore;
import com.denfop.api.tile.IMultiTileBlock;
import net.minecraft.creativetab.CreativeTabs;

public interface IUEIMultiTileBlock extends IMultiTileBlock {

    @Override
    default CreativeTabs getCreativeTab() {
        return IUECore.IUETab;
    }

}
