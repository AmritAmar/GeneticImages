package main.gene.shape;

import main.GeneticImages_SingleParent;

import java.awt.*;
import java.util.Arrays;

public final class Polygon implements Shape {

    private final ShapeType type;
    private int[] color;
    private int[] x;
    private int[] y;
    private int z;

    public ShapeType getType() {
        return this.type;
    }

    public void draw(Graphics g, GeneticImages_SingleParent.Settings s) {
        if (s.useAlpha) {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2], this.getColor()[3]));
        } else {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2]));
        }

        g.fillPolygon(this.x, this.y, this.x.length);
    }

    public Shape copy() {
        int[] copyColor = this.getColor().clone();
        int[] copyX = this.getX().clone();
        int[] copyY = this.getY().clone();
        Polygon newPoly = new Polygon(copyColor, copyX, copyY, this.z);
        return newPoly;
    }

    public int[] getColor() {
        return this.color;
    }

    public void setColor(int[] c) {
        this.color = c;
    }

    public final int[] getX() {
        return this.x;
    }

    public final void setX(int[] x) {
        this.x = x;
    }

    public final int[] getY() {
        return this.y;
    }

    public final void setY(int[] y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Polygon(int[] color, int[] x, int[] y, int z) {
        super();
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = ShapeType.POLYGON;
    }
}
