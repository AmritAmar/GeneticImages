package main.gene.shape;

import main.GeneticImages_SingleParent;

import java.awt.Graphics;


public interface Shape {

    enum ShapeType {
        ELLIPSE,
        CIRCLE,
        RECTANGLE,
        PIXEL,
        POLYGON
    }

    ShapeType getType();

    int getZ();
    void setColor(int[] color);
    int[] getColor();
    void draw(Graphics g, GeneticImages_SingleParent.Settings s);
    Shape copy();

}
