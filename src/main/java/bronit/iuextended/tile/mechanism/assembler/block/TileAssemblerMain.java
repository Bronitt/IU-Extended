package bronit.iuextended.tile.mechanism.assembler.block;

import bronit.iuextended.IUEItem;
import bronit.iuextended.audio.EnumSound;
import bronit.iuextended.blocks.multiblock.assembler.BlockAssembler;
import bronit.iuextended.container.assembler.ContainerAssemblerController;
import bronit.iuextended.gui.assembler.GuiAssemblerController;
import bronit.iuextended.handlers.multiblock.AssemblerHandler;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerHeat;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerInputFluid;
import bronit.iuextended.tile.mechanism.assembler.api.IAssemblerMain;
import bronit.iuextended.tile.mechanism.assembler.api.InvSlotAssembler;
import com.denfop.Localization;
import com.denfop.api.audio.EnumTypeAudio;
import com.denfop.api.audio.IAudioFixer;
import com.denfop.api.multiblock.IMainMultiBlock;
import com.denfop.api.recipe.InvSlotOutput;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.componets.Fluids;
import com.denfop.componets.HeatComponent;
import com.denfop.invslot.InvSlot.TypeItemSlot;
import com.denfop.invslot.InvSlotFluidByList;
import com.denfop.network.DecoderHandler;
import com.denfop.network.EncoderHandler;
import com.denfop.network.IUpdatableTileEvent;
import com.denfop.network.packet.CustomPacketBuffer;
import com.denfop.network.packet.PacketStopSound;
import com.denfop.network.packet.PacketUpdateFieldTile;
import com.denfop.tiles.base.TileEntityInventory;
import com.denfop.tiles.mechanism.multiblocks.base.TileMultiBlockBase;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.mutable.MutableObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileAssemblerMain extends TileMultiBlockBase
        implements IAssemblerMain,
                   IUpdatableTileEvent,
                   IAudioFixer {

    public FluidTank tank1;
    public FluidTank tank2;
    public FluidTank tank3;
    public FluidTank tank4;
    public Predicate<Fluid> acceptedFluids = Fluids.fluidPredicate(); //TODO

    public final InvSlotOutput output1;
    public final InvSlotFluidByList fluidSlot;
    public final HeatComponent heat;
    public boolean load = false;
    public InvSlotAssembler invSlotAssembler = new InvSlotAssembler(this);
    public InvSlotOutput output = new InvSlotOutput(this, 1);

    public IAssemblerHeat assemblerHeat;
    public static IAssemblerInputFluid assemblerInputFluid;
    public List<EntityPlayer> entityPlayerList;
    public double progress = 0;
    public int bar = 1;
    public EnumTypeAudio typeAudio = EnumTypeAudio.OFF;
    public EnumTypeAudio[] valuesAudio = EnumTypeAudio.values();

    private boolean sound = true;

    public TileAssemblerMain() {
        super(AssemblerHandler.assemblerMultiBlock);
        this.full = false;
        final Fluids fluids = this.addComponent(new Fluids(this));
        this.tank1 = fluids.addTank("tank1", 100000, TypeItemSlot.INPUT_OUTPUT, Predicates.alwaysTrue() /*acceptedFluids*/);   //TODO
        this.tank2 = fluids.addTank("tank2", 100000, TypeItemSlot.INPUT_OUTPUT, Predicates.alwaysTrue() /*acceptedFluids*/);   //TODO
        this.tank3 = fluids.addTank("tank3", 100000, TypeItemSlot.INPUT_OUTPUT, Predicates.alwaysTrue() /*acceptedFluids*/);   //TODO
        this.tank4 = fluids.addTank("tank4", 100000, TypeItemSlot.INPUT_OUTPUT, Predicates.alwaysTrue() /*acceptedFluids*/);   //TODO
        this.entityPlayerList = new ArrayList<>();
        this.fluidSlot = new InvSlotFluidByList(this, 1, FluidRegistry.WATER);
        this.output1 = new InvSlotOutput(this, 1);
        this.heat = this.addComponent(HeatComponent.asBasicSink(this, 1000));
    }

    public IMultiTileBlock getTeBlock() {
        return BlockAssembler.assembler_main;
    }

    public BlockTileEntity getBlock() {
        return IUEItem.assembler;
    }

    @Override
    public void readContainerPacket(final CustomPacketBuffer customPacketBuffer) {
        super.readContainerPacket(customPacketBuffer);
        try {
            full = (boolean) DecoderHandler.decode(customPacketBuffer);
            tank1 = (FluidTank) DecoderHandler.decode(customPacketBuffer);
            tank2 = (FluidTank) DecoderHandler.decode(customPacketBuffer);
            tank3 = (FluidTank) DecoderHandler.decode(customPacketBuffer);
            tank4 = (FluidTank) DecoderHandler.decode(customPacketBuffer);
            progress = (double) DecoderHandler.decode(customPacketBuffer);
            bar = (int) DecoderHandler.decode(customPacketBuffer);
            sound = (boolean) DecoderHandler.decode(customPacketBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CustomPacketBuffer writeContainerPacket() {
        final CustomPacketBuffer packet = super.writeContainerPacket();
        try {
            EncoderHandler.encode(packet, full);
            EncoderHandler.encode(packet, tank1);
            EncoderHandler.encode(packet, tank2);
            EncoderHandler.encode(packet, progress);
            EncoderHandler.encode(packet, bar);
            EncoderHandler.encode(packet, sound);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packet;
    }

    public static void assemblerMainTooltip(final List<String> tooltip) {
        tooltip.add(Localization.translate("iuextended.assembler.info1") + " " + Localization.translate(new ItemStack(
                IUEItem.assembler,
                1,
                0
        ).getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final List<String> tooltip) {
        super.addInformation(stack, tooltip);
        assemblerMainTooltip(tooltip);
    }

    public EnumTypeAudio getType() {
        return typeAudio;
    }

    public void setType(EnumTypeAudio type) {
        typeAudio = type;
    }

    @Override
    public boolean getEnable() {
        return this.sound;
    }

    @Override
    public SoundEvent getSound() {
        return EnumSound.assembler.getSoundEvent();
    }

    public void initiate(int soundEvent) {
        if (this.getType() == valuesAudio[soundEvent % valuesAudio.length]) {
            return;
        }

        setType(valuesAudio[soundEvent % valuesAudio.length]);
        if (!getEnable()) {
            return;
        }
        if (getSound() == null) {
            return;
        }
        if (soundEvent == 0) {
            this.getWorld().playSound(null, this.pos, getSound(), SoundCategory.BLOCKS, 1F, 1);
        } else if (soundEvent == 1) {
            new PacketStopSound(getWorld(), this.pos);
            this.getWorld().playSound(null, this.pos, EnumSound.InterruptOne.getSoundEvent(), SoundCategory.BLOCKS, 1F, 1);
        } else {
            new PacketStopSound(getWorld(), this.pos);
        }
    }

    @Override
    public void updateAfterAssembly() {
        List<BlockPos> pos1 = this
                .getMultiBlockStucture()
                .getPosFromClass(this.getFacing(), this.getBlockPos(),
                        IAssemblerInputFluid.class
                );
        this.setInputFluid((IAssemblerInputFluid) this.getWorld().getTileEntity(pos1.get(0)));
        pos1 = this
                .getMultiBlockStucture()
                .getPosFromClass(this.getFacing(), this.getBlockPos(),
                        IAssemblerHeat.class
                );
        this.setHeat((IAssemblerHeat) this.getWorld().getTileEntity(pos1.get(0)));
    }

    @Override
    public void usingBeforeGUI() {

    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        if (!this.getWorld().isRemote) {
            new PacketUpdateFieldTile(this, "sound", this.sound);
        }

    }

    //TODO
    @Override
    public void updateEntityServer() {
        super.updateEntityServer();
        if (!this.full) {
            if (this.getActive()) {
                this.setActive(false);
                initiate(2);
            }
            return;
        }

        MutableObject<ItemStack> output1 = new MutableObject<>();
        if (
                (
                    this.fluidSlot.transferToTank(this.tank1, output1, true)
                    && this.fluidSlot.transferToTank(this.tank2, output1, true)
                    && this.fluidSlot.transferToTank(this.tank3, output1, true)
                    && this.fluidSlot.transferToTank(this.tank3, output1, true)
            ) && (output1.getValue() == null || this.output1.canAdd(output1.getValue()))) {
            this.fluidSlot.transferToTank(this.tank1, output1, false);
            if (output1.getValue() != null) {
                this.output1.add(output1.getValue());
            }
        }

    }

    @Override
    public IAssemblerHeat getHeat() {
        return assemblerHeat;
    }

    @Override
    public void setHeat(final IAssemblerHeat blastHeat) {
        this.assemblerHeat = blastHeat;
        try {
            this.heat.onUnloaded();
        } catch (Exception ignored) {
        }
        if (this.assemblerHeat != null) {
            this.heat.setParent((TileEntityInventory) blastHeat);
            this.heat.onLoaded();
        }
    }

    @Override
    public IAssemblerInputFluid getInputFluid() {
        return assemblerInputFluid;
    }

    //TODO
    @Override
    public void setInputFluid(final IAssemblerInputFluid assemblerInputFluid) {
        this.assemblerInputFluid = assemblerInputFluid;
        if (this.assemblerInputFluid == null) {
            this.tank1 = null;
            this.tank2 = null;
            this.tank3 = null;
            this.tank4 = null;
        } else {
            this.tank1 = this.assemblerInputFluid.getFluidTank(1);
            this.tank2 = this.assemblerInputFluid.getFluidTank(2);
            this.tank3 = this.assemblerInputFluid.getFluidTank(3);
            this.tank4 = this.assemblerInputFluid.getFluidTank(4);
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.sound = nbttagcompound.getBoolean("sound");

    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setBoolean("sound", this.sound);
        return nbttagcompound;
    }

    @Override
    public double getProgress() {
        return this.progress;
    }

    //TODO
    @Override
    public void updateTileServer(final EntityPlayer entityPlayer, final double i) {
        switch ((int) i) {
            case 0:
                this.bar = Math.min(this.bar + 1, 5);
                break;
            case 1:
                this.bar = Math.max(1, this.bar - 1);
                break;
            case 10:

                sound = !sound;
                new PacketUpdateFieldTile(this, "sound", this.sound);

                if (!sound) {
                    if (this.getType() == EnumTypeAudio.ON) {
                        setType(EnumTypeAudio.OFF);
                        initiate(2);

                    }
                }
                break;
        }
    }

    public void updateField(String name, CustomPacketBuffer is) {

        if (name.equals("sound")) {
            try {
                this.sound = (boolean) DecoderHandler.decode(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.updateField(name, is);
    }

    @Override
    public boolean onActivated(
            final EntityPlayer player,
            final EnumHand hand,
            final EnumFacing side,
            final float hitX,
            final float hitY,
            final float hitZ
    ) {
        if (this.getWorld().isRemote) {
            return false;
        }
        if (this.full || this.activate) {

        }

        return super.onActivated(player, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public ContainerAssemblerController getGuiContainer(final EntityPlayer entityPlayer) {
        if (!this.entityPlayerList.contains(entityPlayer)) {
            this.entityPlayerList.add(entityPlayer);
        }
        return new ContainerAssemblerController(this, entityPlayer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(final EntityPlayer entityPlayer, final boolean b) {
        return new GuiAssemblerController(this.getGuiContainer(entityPlayer));
    }

    @Override
    public IMainMultiBlock getMain() {
        return this;
    }

    @Override
    public void setMainMultiElement(final IMainMultiBlock main) {

    }

    @Override
    public boolean isMain() {
        return true;
    }


    @Override
    public void onNetworkEvent(final int var1) {

    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[]{};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        ItemStack itemStackIn1 = itemStackIn.copy();
        itemStackIn1.setCount(1);
        ItemStack invSlot = this.invSlotAssembler.get(index);
        ItemStack invSlot1 = invSlot.copy();
        invSlot1.setCount(1);
        if (itemStackIn1 == invSlot1) {
            if (itemStackIn.getCount() + invSlot.getCount() >= 64) {
                return false;
            } else return true;
        } else if (!itemStackIn.isEmpty()) {
            return false;
        } else return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return !this.invSlotAssembler.get(index).isEmpty();
    }

    @Override
    public EnumTypeAudio getTypeAudio() {
        return this.typeAudio;
    }

    @Override
    public int getSizeInventory() {
        return this.size_inventory;
    }

    @Override
    public boolean isEmpty() {
        return this.invSlotAssembler.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.invSlotAssembler.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        invSlotAssembler.get(index).setCount(count);
        return invSlotAssembler.get(index);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        invSlotAssembler.get(index).setCount(0);
        return invSlotAssembler.get(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        invSlotAssembler.set(index, stack);
    }

    @Override
    public int getInventoryStackLimit() {
        return invSlotAssembler.getStackSizeLimit();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return invSlotAssembler.get(index).equals(stack);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public int getFieldCount() {
        return super.getFieldCount();
    }


}
