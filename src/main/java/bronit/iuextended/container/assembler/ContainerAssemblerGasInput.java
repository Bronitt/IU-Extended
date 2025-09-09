package bronit.iuextended.container.assembler;

import bronit.iuextended.tile.mechanism.assembler.integration.mekanism.block.TileAssemblerGasInput;
import com.denfop.container.ContainerFullInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ContainerAssemblerGasInput extends ContainerFullInv<TileAssemblerGasInput> {

    public ContainerAssemblerGasInput(
            TileAssemblerGasInput tileAssemblerGasInput,
            EntityPlayer player
    ) {
        super(player, tileAssemblerGasInput, 166);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

}
