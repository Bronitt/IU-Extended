package bronit.iuextended.tile.mechanism.assembler.integration.mekanism.api;

import bronit.iuextended.components.mekanism.MekaGasses;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerPart;
import mekanism.api.gas.GasTank;

public interface IAssemblerMekaGasInput extends IAssemblerPart {

    GasTank getGasTank();

    MekaGasses getGas();

}
