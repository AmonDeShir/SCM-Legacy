package com.amadornes.rscircuits.content.block.circuit;

import com.amadornes.rscircuits.AllTileEntites;
import com.amadornes.rscircuits.api.circuit.ICircuit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Circuit extends Block implements ICircuit {
    public Circuit(Properties properties) {
        super(Properties.from(Blocks.STONE_SLAB));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return AllTileEntites.CIRCUIT_TILE_ENTITY.create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isRemote)
            return ActionResultType.PASS;

        if(!(worldIn.getTileEntity(pos) instanceof CircuitTileEntity))
            return  ActionResultType.PASS;

        if(handIn == Hand.OFF_HAND)
            return ActionResultType.PASS;

        CircuitTileEntity circuitTileEntity = (CircuitTileEntity) worldIn.getTileEntity(pos);

        circuitTileEntity.increaseCounter();
        player.sendStatusMessage(new StringTextComponent("Counter: " + circuitTileEntity.getCounter()), true);

        return ActionResultType.SUCCESS;
    }
}
