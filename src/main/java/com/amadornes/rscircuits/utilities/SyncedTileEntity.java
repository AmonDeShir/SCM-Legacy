//Based on https://github.com/Creators-of-Create/Create/blob/5a664d6d8595dc9f7f83ac69694a9638bfe8975d/src/main/java/com/simibubi/create/foundation/tileEntity/SyncedTileEntity.java#L15
package com.amadornes.rscircuits.utilities;

import com.amadornes.rscircuits.SCM;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.apache.logging.log4j.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class SyncedTileEntity extends TileEntity {
    public SyncedTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public void fromTag(BlockState state, CompoundNBT tag){
        SCM.LOGGER.log(Level.INFO, "------------------ READ FROM TAG!!!");

        func_230337_a_(state, tag);
    }

    @Override
    public CompoundNBT getTileData() {
        SCM.LOGGER.log(Level.INFO, "------------------ getTileData!!!");


        return super.getTileData();
    }

    @Override
    public CompoundNBT getUpdateTag() {
        SCM.LOGGER.log(Level.INFO, "------------------ getUpdateTag!!!");

        return write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        SCM.LOGGER.log(Level.INFO, "------------------ handleUpdateTag!!!");

        fromTag(state, tag);
    }

    public void sendData() {
        SCM.LOGGER.log(Level.INFO, "------------------ sendData!!!");


        if (world != null)
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2 | 4 | 16);
    }

    public void causeBlockUpdate() {
        SCM.LOGGER.log(Level.INFO, "------------------ causeBlockUpdate!!!");


        if (world != null)
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 1);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        SCM.LOGGER.log(Level.INFO, "------------------ getUpdatePacket!!!");

        return new SUpdateTileEntityPacket(getPos(), 1, writeToClient(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        SCM.LOGGER.log(Level.INFO, "------------------ onDataPacket!!!");

        readClientUpdate(getBlockState(), pkt.getNbtCompound());
    }

    // Special handling for client update packets
    public void readClientUpdate(BlockState state, CompoundNBT tag) {
        SCM.LOGGER.log(Level.INFO, "------------------ readClientUpdate!!!");

        fromTag(state, tag);
    }

    // Special handling for client update packets
    public CompoundNBT writeToClient(CompoundNBT tag) {
        SCM.LOGGER.log(Level.INFO, "------------------ writeToClient!!!");

        return write(tag);
    }

    public void notifyUpdate() {
        markDirty();
        sendData();

        SCM.LOGGER.log(Level.INFO, "------------------ MARK DIRTY!!!");
    }
}