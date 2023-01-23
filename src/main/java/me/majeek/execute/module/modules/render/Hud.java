package me.majeek.execute.module.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import me.majeek.execute.ExecuteMod;
import me.majeek.execute.event.listeners.RenderListener;
import me.majeek.execute.module.Module;
import me.majeek.execute.module.ModuleName;
import me.majeek.execute.module.ModuleType;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

public class Hud extends Module implements RenderListener {
    public Hud() {
        super(ModuleName.HUD, GLFW.GLFW_KEY_UNKNOWN, ModuleType.RENDER);

        this.setToggled(true);
        this.setHidden(true);
    }

    @Override
    public void onEnable() {
        ExecuteMod.INSTANCE.getEventManager().add(RenderListener.class, this);
    }

    @Override
    public void onDisable() {
        ExecuteMod.INSTANCE.getEventManager().remove(RenderListener.class, this);
    }


    @Override
    public void onRender(RenderEvent event) {
        int index = 0;
        List<String> modules = ExecuteMod.INSTANCE.getModuleManager().getEnabledModules(false).stream().map(Module::getName).sorted().toList();

        for(String module : modules) {
            int x = CLIENT.getWindow().getScaledWidth() - CLIENT.textRenderer.getWidth(module);
            int y = index * (CLIENT.textRenderer.fontHeight + 1);

            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            event.getMatrices().push();

            event.getMatrices().translate(x - 4, y + 0.5F, 0);
            drawBox(event.getMatrices(), 0, 0, 1.5F, CLIENT.textRenderer.fontHeight - 0.5F, new Color(0x79f592));

            event.getMatrices().pop();
            GL11.glEnable(GL11.GL_CULL_FACE);

            CLIENT.textRenderer.drawWithShadow(event.getMatrices(), module, x, y + 1, 0xEEEEEE);

            index++;
        }
    }

    private void drawOutline(MatrixStack stack, float x1, float y1, float x2, float y2, Color color) {
        float[] rgb = new float[]{color.getRed(), color.getGreen(), color.getBlue()};

        Matrix4f matrix = stack.peek().getPositionMatrix();
        Tessellator tessellator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        // outline
        GL11.glLineWidth(1);
        RenderSystem.setShaderColor(rgb[0], rgb[1], rgb[2], 1.0F);
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION);
        {
            bufferBuilder.vertex(matrix, x1, y1, 0).next();
            bufferBuilder.vertex(matrix, x2, y1, 0).next();
            bufferBuilder.vertex(matrix, x2, y2, 0).next();
            bufferBuilder.vertex(matrix, x1, y2, 0).next();
            bufferBuilder.vertex(matrix, x1, y1, 0).next();
        }
        tessellator.draw();
    }

    private void drawBox(MatrixStack matrixStack, float x1, float y1, float x2, float y2, Color color) {
        float[] rgb = new float[]{color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F};

        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
        Tessellator tessellator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionShader);

        // color
        RenderSystem.setShaderColor(rgb[0], rgb[1], rgb[2], 1.0F);

        // box
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        {
            bufferBuilder.vertex(matrix, x1, y1, 0).next();
            bufferBuilder.vertex(matrix, x2, y1, 0).next();
            bufferBuilder.vertex(matrix, x2, y2, 0).next();
            bufferBuilder.vertex(matrix, x1, y2, 0).next();
        }
        tessellator.draw();
    }

}
