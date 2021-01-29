package com.amadornes.rscircuits.content.tab;

import com.amadornes.rscircuits.SCM;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SCMCreativeTab extends ItemGroup {

    public SCMCreativeTab() {
        super(getGroupCountSafe(), SCM.MOD_ID + "." + "base");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.ACACIA_BUTTON);
    }
}
