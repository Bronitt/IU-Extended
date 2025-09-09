package bronit.iuextended.container.assembler;

import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerFluidInput;
import com.denfop.container.ContainerFullInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ContainerAssemblerFluidInput extends ContainerFullInv<TileAssemblerFluidInput> {

    public ContainerAssemblerFluidInput(
            TileAssemblerFluidInput tileAssemblerFluidInput,
            EntityPlayer player
    ) {
        super(player, tileAssemblerFluidInput, 166);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

}
