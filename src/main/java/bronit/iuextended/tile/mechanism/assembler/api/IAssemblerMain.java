package bronit.iuextended.tile.mechanism.assembler.api;

import com.denfop.api.multiblock.IMainMultiBlock;

public interface IAssemblerMain  extends IMainMultiBlock {

    IAssemblerHeat getHeat();

    void setHeat(IAssemblerHeat assemblerHeat);

    IAssemblerInputFluid getInputFluid();

    void setInputFluid(IAssemblerInputFluid blastInputFluid);

    double getProgress();

}
