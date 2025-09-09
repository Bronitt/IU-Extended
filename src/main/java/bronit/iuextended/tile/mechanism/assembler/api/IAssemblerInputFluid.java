package bronit.iuextended.tile.mechanism.assembler.api;

import com.denfop.componets.Fluids;

public interface IAssemblerInputFluid extends IAssemblerPart {

    Fluids.InternalFluidTank getFluidTank();
    Fluids.InternalFluidTank getFluidTank(int index);

    Fluids getFluid();

}