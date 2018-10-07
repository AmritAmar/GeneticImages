package main.gene.shape;

import main.GeneticImages_SingleParent;

import java.awt.*;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Circle implements Shape {
    private final ShapeType type;
    private int[] color;
    private int x;
    private int y;
    private int z;
    private int r;

    public ShapeType getType() {
        return this.type;
    }

    public void draw( Graphics g, GeneticImages_SingleParent.Settings s) {
        if (s.useAlpha) {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2], this.getColor()[3]));
        } else {
            g.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2]));
        }

        g.fillOval(this.x, this.y, this.r, this.r);
    }

    public Shape copy() {
        int[] copyColor = this.getColor().clone();

        Circle newC = new Circle(copyColor, this.x, this.y, this.z, this.r);
        return newC;
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

    public void setY(int y) { this.y = y; }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Circle(int[] color, int x, int y, int z, int r) {
        super();
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.type = ShapeType.CIRCLE;
    }
}

