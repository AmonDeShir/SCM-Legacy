package com.amadornes.rscircuits.content.block.circuit;

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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.client.model.data.EmptyModelData;

public class CircuitEntityRenderer extends TileEntityRenderer<CircuitTileEntity> {
    private final float margin = 0.0625f;
    private final float circuitSize = 1f;
    private final float circuitSizeWithoutMargin = circuitSize - 2 * margin;
    private final int cellCount = 7;
    private final float cellSize = circuitSizeWithoutMargin / cellCount;


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
        Vector3d hitPosition = getSelectedByPlayerBlockPosition();
        Vector3d blockPosition = blockPosToVector3d(tileEntity.getPos());
        Vector3d inBlockPosition = absVector(subtractVectors(absVector(hitPosition), absVector(blockPosition)));

        if(!isBlockSelected( blockPosition, hitPosition))
            return;

        Vector3f circuitInsideMargin = new Vector3f(margin, 2 * margin, margin);
        Vector3f boxPosition = sumVectors(circuitInsideMargin, calcBoxPosition(inBlockPosition));
        Vector3f boxSize = new Vector3f(cellSize, 0.1f, cellSize);

        addBox(boxPosition, boxSize, buffer, matrixStack);
    }

    Vector3d getSelectedByPlayerBlockPosition(){
        RayTraceResult lookingAt = Minecraft.getInstance().objectMouseOver;

        if (lookingAt == null && lookingAt.getType() != RayTraceResult.Type.BLOCK)
            return null;

        return lookingAt.getHitVec();
    }

    Vector3d blockPosToVector3d(BlockPos blockPos) {
        return new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    private Vector3d absVector(Vector3d vector) {
        return new Vector3d(Math.abs(vector.getX()), Math.abs(vector.getY()), Math.abs(vector.getZ()));
    }

    private Vector3d subtractVectors(Vector3d vecA, Vector3d vecB) {
        return new Vector3d(
                vecA.getX() - vecB.getX(),
                vecA.getY() - vecB.getY(),
                vecA.getZ() - vecB.getZ()
        );
    }

    boolean isBlockSelected(Vector3d block, Vector3d hit) {
        return hit.getX() >= block.getX() && hit.getX() <= block.getX()+1 &&
                hit.getY() >= block.getY() && hit.getY() <= block.getY()+1 &&
                hit.getZ() >= block.getZ() && hit.getZ() <= block.getZ()+1;
    }

    Vector3f sumVectors(Vector3f vecA, Vector3f vecB) {
        Vector3f result = new Vector3f();

        result.add(vecA);
        result.add(vecB);

        return  result;
    }

    Vector3f calcBoxPosition(Vector3d inBlockPosition) {
        Vector3d hitPosition = inBlockPosition != null ? inBlockPosition : new Vector3d(0, 0, 0);

        int cellIdX = (int)((hitPosition.getX() - margin) / cellSize);
        int cellIdZ = (int)((hitPosition.getZ() - margin) / cellSize);

        cellIdX = Math.min(cellIdX, cellCount - 1);
        cellIdZ = Math.min(cellIdZ, cellCount - 1);

        float cellPositionX = cellIdX * cellSize;
        float cellPositionZ = cellIdZ * cellSize;

        return new Vector3f(cellPositionX, 0, cellPositionZ);
    }
}
