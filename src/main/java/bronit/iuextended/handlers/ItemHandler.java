package bronit.iuextended.handlers;

import bronit.iuextended.IUECore.Constants;
import bronit.iuextended.IUEItem;
import bronit.iuextended.blocks.multiblock.assembler.BlockAssembler;
import bronit.iuextended.blocks.multiblock.assembler.BlockMekaAssembler;
import com.denfop.blocks.TileBlockCreator;

public class ItemHandler {

    public static void init() {

        IUEItem.assembler = TileBlockCreator.instance.create(BlockAssembler.class);
        if (Constants.MEKA_LOADED) {
            IUEItem.assemblerMeka = TileBlockCreator.instance.create(BlockMekaAssembler.class);
        }

    }

}
