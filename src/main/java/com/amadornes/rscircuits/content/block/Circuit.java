package com.amadornes.rscircuits.content.block;

import com.amadornes.rscircuits.api.circuit.ICircuit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class Circuit extends Block implements ICircuit {
    public Circuit(Properties properties) {
        super(Properties.from(Blocks.STONE_SLAB));
    }
}
