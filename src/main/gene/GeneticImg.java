package main.gene;

import main.GeneticImages_SingleParent;
import main.gene.shape.*;
import main.gene.shape.Polygon;
import main.gene.shape.Rectangle;
import main.gene.shape.Shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GeneticImg {

    Random rand;
    GeneticImages_SingleParent.Settings s;

    public GeneticImg(GeneticImages_SingleParent.Settings s) {
        this.s = s;
        this.rand = new Random();
    }

    public Individual newIndividual() {
        ArrayList<Shape> pool = new ArrayList<>();
        for (int i = 0; i < s.numOfGenes; i++) {
            pool.add(newShape());
        }
        return new Individual(pool, Double.MAX_VALUE);
    }

    public ArrayList<Individual> newPopulation() {
        ArrayList<Individual> population = new ArrayList<>();
        for (int i = 0; i < s.populationCount; i++) {
            population.add(newIndividual());
        }
        return population;
    }

    public void drawMe(Graphics g, Individual i) {
        g.setColor(Color.BLACK);
        g.clearRect(0,0,s.width,s.height);
        for (Shape shp : i.getDna()) {
            shp.draw(g, s);
        }
    }

    public Individual copy(Individual i) {
        ArrayList<Shape> copied = new ArrayList<>();
        for (Shape shp : i.getDna()) {
            copied.add(shp.copy());
        }
        return new Individual(copied, i.getFitness());
    }

    public int bound(int value, int min, int max) {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value;
    }

    public Shape newShape() {
        int shapeIndex = rand.nextInt(s.shapes.size());
        switch (s.shapes.get(shapeIndex)) {
            case CIRCLE: return newCircle();
            case RECTANGLE: return newRectangle();
            case ELLIPSE: return newEllipse();
            case PIXEL: return newPixel();
            default: return newPolygon();
        }
    }

    public int[] newColor() {
        int[] color = new int[4];
        color[0] = rand.nextInt(256);
        color[1] = rand.nextInt(256);
        color[2] = rand.nextInt(256);
        color[3] = rand.nextInt(256);
        return color;
    }

    public Ellipse newEllipse() {
        int x = rand.nextInt(s.width);
        int y = rand.nextInt(s.height);
        int z = rand.nextInt(1000);
        int w = bound(rand.nextInt(s.width / 4), 2, s.width - x);
        int h = bound(rand.nextInt(s.height / 4), 2, s.height - y);
        int[] c = newColor();
        return new Ellipse(c,x,y,z,w,h);
    }

    public Circle newCircle() {
        int x = rand.nextInt(s.width);
        int y = rand.nextInt(s.height);
        int z = rand.nextInt(1000);
        int r = bound(rand.nextInt(s.width / 4), 2, (s.width + s.height) / 2);
        int[] c = newColor();
        return new Circle(c, x, y, z, r);
    }

    public Pixel newPixel() {
        int x = rand.nextInt(s.width / s.pixelSize) * s.pixelSize;
        int y = rand.nextInt(s.height / s.pixelSize) * s.pixelSize;
        int z = rand.nextInt(1000);
        int[] c = newColor();
        return new Pixel(c,x,y,z,s.pixelSize);
    }

    public Rectangle newRectangle() {
        int x = rand.nextInt(s.width);
        int y = rand.nextInt(s.height);
        int w = bound(rand.nextInt(s.width / 4), 2, s.width - x);
        int h = bound(rand.nextInt(s.height / 4), 2, s.height - y);
        int z = rand.nextInt(1000);
        int[] c = newColor();
        return new Rectangle(c, x, y, z, w, h);
    }

    public Polygon newPolygon() {
        int numPoints = rand.nextInt(s.maxPolygonSize - 2) + 3;
        int[] x = new int[numPoints];
        int[] y = new int[numPoints];

        for (int i = 0; i < numPoints; i++) {
            x[i] = rand.nextInt(s.width);
            y[i] = rand.nextInt(s.height);
        }
        int z = rand.nextInt(1000);
        int[] c = newColor();
        return new Polygon(c, x, y, z);
    }

}
