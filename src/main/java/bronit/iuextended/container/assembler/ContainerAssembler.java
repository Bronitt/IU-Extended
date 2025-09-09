package bronit.iuextended.container.assembler;

import bronit.iuextended.tile.mechanism.assembler.block.TileAssemblerMain;
import com.denfop.container.ContainerFullInv;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerAssembler extends ContainerFullInv<TileAssemblerMain> {

    public ContainerAssembler(EntityPlayer entityPlayer, TileAssemblerMain tileEntityAssemblerMain) {
        super(entityPlayer, tileEntityAssemblerMain, 166);
//        this.addSlotToContainer(new SlotInvSlot(tileEntityAssemblerMain.getInvSlot(), 0, 77, 34));
    }

    //TODO game stages xD
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
