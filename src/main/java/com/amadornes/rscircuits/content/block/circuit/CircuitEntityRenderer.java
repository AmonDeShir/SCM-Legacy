package com.amadornes.rscircuits.content.block.circuit;

import com.amadornes.rscircuits.AllItems;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.client.model.data.EmptyModelData;


public class CircuitEntityRenderer extends TileEntityRenderer<CircuitTileEntity> {
    public CircuitEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private static void addLine(IVertexBuilder builder, Matrix4f positionMatrix, Vector3f pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        Vector4f blackColor = new Vector4f(0, 0, 0, 1);



        builder.pos(positionMatrix, pos.getX()+dx1, pos.getY()+dy1, pos.getZ()+dz1)
                .color(blackColor.getX(), blackColor.getY(), blackColor.getZ(), blackColor.getW())
                .endVertex();

        builder.pos(positionMatrix, pos.getX()+dx2, pos.getY()+dy2, pos.getZ()+dz2)
                .color(blackColor.getX(), blackColor.getY(), blackColor.getZ(), blackColor.getW())
                .endVertex();
    }

    public static void addBox(Vector3f position, Vector3f size, IRenderTypeBuffer buffer, MatrixStack matrixStack) {
        IVertexBuilder builder = buffer.getBuffer(RenderType.getLines());
        Matrix4f matrix = matrixStack.getLast().getMatrix();
        float size___zero = 0;

        matrixStack.push();

        addLine(builder, matrix, position, size___zero, size___zero, size___zero, size.getX(), size___zero, size___zero);
        addLine(builder, matrix, position, size___zero, size.getY(), size___zero, size.getX(), size.getY(), size___zero);
        addLine(builder, matrix, position, size___zero, size___zero, size.getZ(), size.getX(), size___zero, size.getZ());
        addLine(builder, matrix, position, size___zero, size.getY(), size.getZ(), size.getX(), size.getY(), size.getZ());

        addLine(builder, matrix, position, size___zero, size___zero, size___zero, size___zero, size___zero, size.getZ());
        addLine(builder, matrix, position, size.getX(), size___zero, size___zero, size.getX(), size___zero, size.getZ());
        addLine(builder, matrix, position, size___zero, size.getY(), size___zero, size___zero, size.getY(), size.getZ());
        addLine(builder, matrix, position, size.getX(), size.getY(), size___zero, size.getX(), size.getY(), size.getZ());

        addLine(builder, matrix, position, size___zero, size___zero, size___zero, size___zero, size.getY(), size___zero);
        addLine(builder, matrix, position, size.getX(), size___zero, size___zero, size.getX(), size.getY(), size___zero);
        addLine(builder, matrix, position, size___zero, size___zero, size.getZ(), size___zero, size.getY(), size.getZ());
        addLine(builder, matrix, position, size.getX(), size___zero, size.getZ(), size.getX(), size.getY(), size.getZ());

        matrixStack.pop();
    }

    void addItem(Item item, Vector3d positionTranslate, IRenderTypeBuffer buffer, MatrixStack matrixStack, CircuitTileEntity tileEntity, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5, 1.5, 0.5);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(item);

        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

        matrixStack.translate(positionTranslate.x, positionTranslate.y, positionTranslate.z);
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();

        BlockState state = Blocks.ENDER_CHEST.getDefaultState();
        blockRenderer.renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

        matrixStack.pop();
    }


    @Override
    public void render(CircuitTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Vector3f blockTopFace = new Vector3f(0, 0.125f, 0);

        addBox(blockTopFace, new Vector3f(0.1f, 0.1f, 0.1f), buffer, matrixStack);
        addItem(AllItems.SCREWDRIVER.get(), new Vector3d(-0.5, 1, -0.5), buffer, matrixStack, tileEntity, combinedLight, combinedOverlay);
    }
}
