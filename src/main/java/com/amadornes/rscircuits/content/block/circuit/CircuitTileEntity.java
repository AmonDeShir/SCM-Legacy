package com.amadornes.rscircuits.content.block.circuit;

import com.amadornes.rscircuits.AllTileEntites;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class CircuitTileEntity extends TileEntity {
    private int counter = 0;

    public CircuitTileEntity() {
        super(AllTileEntites.CIRCUIT_TILE_ENTITY.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        nbt.putInt("counter", this.getCounter());

        return  nbt;
    }

    public void read(BlockState blockState, CompoundNBT nbt) {
        this.setCounter(nbt.getInt("counter"));
    }

    //read
    @Override
    public void func_230337_a_(BlockState blockState, CompoundNBT nbt) {
        super.func_230337_a_(blockState, nbt);
        this.read(blockState, nbt);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {

        this.counter = counter;
    }

    public void increaseCounter() {
        setCounter(getCounter()+1);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1, 3, 1));
    }
}
