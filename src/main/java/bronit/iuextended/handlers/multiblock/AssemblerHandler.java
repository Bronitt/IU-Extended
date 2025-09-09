package bronit.iuextended.handlers.multiblock;

import bronit.iuextended.IUEItem;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerCasing;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerHeat;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerInputFluid;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerMain;
import com.denfop.api.multiblock.MultiBlockStructure;
import com.denfop.api.multiblock.MultiBlockSystem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class AssemblerHandler {

    public static MultiBlockStructure assemblerMultiBlock;

    public static void init() {
        assemblerMultiBlock =
                MultiBlockSystem.instance
                        .add("Assembler")
                        .setMain(IAssemblerMain.class)
                        .setHasActivatedItem(false)
//                        .setActivateItem(new ItemStack(IUItem.ForgeHammer))
                        .setIgnoreMetadata(true)
//                        .setUniqueModel()
        ;

        ItemStack assembler_casing = new ItemStack(IUEItem.assembler, 1, 1);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos(), IAssemblerMain.class, new ItemStack(IUEItem.assembler, 1, 0), EnumFacing.NORTH);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos().add(0, -1, 0), IAssemblerCasing.class, assembler_casing);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos().add(0, 0, 1), IAssemblerInputFluid.class, new ItemStack(IUEItem.assembler, 1, 2), EnumFacing.EAST);
        assemblerMultiBlock.add(assemblerMultiBlock.getPos().add(0, -1, 1), IAssemblerHeat.class, new ItemStack(IUEItem.assembler, 1, 3), EnumFacing.EAST);

    }
}
