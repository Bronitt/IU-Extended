package bronit.iuextended.container.assembler;

import bronit.iuextended.tile.mechanism.assembler.integration.mekanism.block.TileAssemblerMekaHeat;
import com.denfop.container.ContainerFullInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ContainerAssemblerMekaHeat extends ContainerFullInv<TileAssemblerMekaHeat> {

    public ContainerAssemblerMekaHeat(
            TileAssemblerMekaHeat tileAssemblerMekaHeat,
            EntityPlayer player
    ) {
        super(player, tileAssemblerMekaHeat, 166);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

}
