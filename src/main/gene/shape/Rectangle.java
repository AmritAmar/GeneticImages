package main.gene.shape;

import main.GeneticImages_SingleParent;
import java.awt.*;
import java.util.Arrays;

public class Rectangle implements Shape {
    private final ShapeType type;
    private int[] color;
    private int x;
    private int y;
    private int z;
    private int w;
    private int h;

    public ShapeType getType() {
        return this.type;
    }

    public void draw(Graphics g, GeneticImages_SingleParent.Settings s) {
        if (s.useAlpha) {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2], this.getColor()[3]));
        } else {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2]));
        }

        g.fillRect(this.x, this.y, this.w, this.h);
    }

    public Shape copy() {
        int[] copyColor = this.getColor().clone();

        Rectangle newR = new Rectangle(copyColor, this.x, this.y, this.z, this.w, this.h);
        return newR;
    }

    public int[] getColor() {
        return this.color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Rectangle(int[] color, int x, int y, int z, int w, int h) {
        super();
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.type = ShapeType.RECTANGLE;
    }
}
