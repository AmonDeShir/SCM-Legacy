package com.amadornes.rscircuits;

import com.amadornes.rscircuits.content.block.circuit.CircuitTileEntity;
import com.amadornes.rscircuits.registrate.SCMRegistrate;
import com.tterrag.registrate.builders.TileEntityBuilder;
import com.tterrag.registrate.util.entry.TileEntityEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.NonNullFunction;

public class AllTileEntites {
    private static final SCMRegistrate REGISTRATE = SCM.registrate().itemGroup(() -> SCM.createTab);

    public static final TileEntityEntry<CircuitTileEntity> CIRCUIT_TILE_ENTITY = REGISTRATE
            .object("circuit_tile_entity")
            .tileEntity(CircuitTileEntity::new)
            .validBlock(AllBlocks.CIRCUIT)
            .register();

    public static void register() {}
}