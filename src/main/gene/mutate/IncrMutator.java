package main.gene.mutate;

import main.GeneticImages_SingleParent;
import main.*;
import main.gene.GeneticImg;
import main.gene.Individual;
import main.gene.shape.*;
import main.gene.shape.Polygon;
import main.gene.shape.Rectangle;
import main.gene.shape.Shape;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IncrMutator implements Mutator {

    GeneticImages_SingleParent.Settings s;
    Random rand;

    float maxDelta;
    float halfDelta;

    float maxColorDelta;
    float halfMaxColorDelta;

    float maxPixelWidth;
    float maxPixelHeight;

    public IncrMutator(GeneticImages_SingleParent.Settings s) {
        this.s = s;
        setup();
    }

    void setup() {
        rand = new Random();
        maxDelta = ((s.width + s.height) / 2) / 2;
        maxColorDelta = 100;

        halfDelta = maxDelta / 2 + 2;
        halfMaxColorDelta = maxColorDelta / 2;

        maxPixelHeight = s.height / s.pixelSize;
        maxPixelWidth = s.width / s.pixelSize;
    }

    @Override
    public Shape mutate(Shape shape, float probability) {
        if (rand.nextDouble() > probability) { return shape; }

        switch (shape.getType()) {
            case PIXEL: return mutatePixel((Pixel) shape);
            case CIRCLE: return mutateCircle((Circle) shape);
            case RECTANGLE: return mutateRectangle((Rectangle) shape);
            case ELLIPSE: return mutateEllipse((Ellipse) shape);
            case POLYGON: return mutatePolygon((Polygon) shape);
        }
        return shape;
    }

    public Individual mutateI(Individual individual, float probability) {
        ArrayList<Shape> mutated = new ArrayList<Shape>();

        for (Shape s : individual.getDna()) {
            mutated.add(mutate(s, probability));
        }
        mutated.sort((o1, o2) -> Double.compare(o1.getZ(), o2.getZ()));
        return new Individual(mutated, Double.MAX_VALUE);
    }

    public int bound(int value, int min, int max) {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value;
    }

    private void mutateColor(Shape s, int i) {
        s.getColor()[i] = bound(s.getColor()[i] + rand.nextInt((int)maxColorDelta) - (int)halfMaxColorDelta, 0, 255);
    }

    private Pixel mutatePixel(Pixel p) {
        int mutationType = rand.nextInt(7);
        switch (mutationType) {
            case 0: p.setX( bound((int)((p.getX() / s.pixelSize) + rand.nextInt((int)(maxPixelWidth / 2)) - maxPixelWidth / 4), 0, (int) maxPixelWidth) * s.pixelSize);
            case 1: p.setY( bound((int)((p.getY() / s.pixelSize) + rand.nextInt((int)(maxPixelHeight / 2)) - maxPixelHeight / 4), 0, (int) maxPixelHeight) * s.pixelSize);
            case 2: p.setZ( rand.nextInt(1000));
            case 3: mutateColor(p, 0); //R
            case 4: mutateColor(p, 1); //G
            case 5: mutateColor(p, 2); //B
            case 6: mutateColor(p, 3); //A
        }
        return p;
    }

    private Rectangle mutateRectangle(Rectangle r) {
        int mutationType = rand.nextInt(9);
        switch (mutationType) {
            case 0: r.setX( bound(r.getX() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.width - r.getW()));
            case 1: r.setY( bound(r.getY() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.height - r.getH()));
            case 2: r.setZ(rand.nextInt(1000));
            case 3: r.setW( bound(r.getW() + rand.nextInt((int)maxDelta) - (int)halfDelta, 5, s.width));
            case 4: r.setH( bound(r.getH() + rand.nextInt((int)maxDelta) - (int)halfDelta, 5, s.height));
            case 5: mutateColor(r, 0); //R
            case 6: mutateColor(r, 1); //G
            case 7: mutateColor(r, 2); //B
            case 8: mutateColor(r, 3); //A
        }
        return r;
    }

    private Ellipse mutateEllipse(Ellipse e) {
        int mutationType = rand.nextInt(9);
        switch (mutationType) {
            case 0: e.setX( bound(e.getX() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.width - e.getW()));
            case 1: e.setY( bound(e.getY() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.height - e.getH()));
            case 2: e.setZ(rand.nextInt(1000));
            case 3: e.setW( bound(e.getW() + rand.nextInt((int)maxDelta) - (int)halfDelta, 5, s.width));
            case 4: e.setH( bound(e.getH() + rand.nextInt((int)maxDelta) - (int)halfDelta, 5, s.height));
            case 5: mutateColor(e, 0); //R
            case 6: mutateColor(e, 1); //G
            case 7: mutateColor(e, 2); //B
            case 8: mutateColor(e, 3); //A
        }
        return e;
    }

    private Circle mutateCircle(Circle c) {

        int mutationType = rand.nextInt(8);
        switch (mutationType) {
            case 0: c.setX( bound(c.getX() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.width - c.getR()));
            case 1: c.setY( bound(c.getY() + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.height - c.getR()));
            case 2: c.setZ( rand.nextInt(1000));
            case 3: c.setR( bound(c.getR() + rand.nextInt((int)maxDelta) - (int)halfDelta, 5, s.width));
            case 4: mutateColor(c, 0); //R
            case 5: mutateColor(c, 1); //G
            case 6: mutateColor(c, 2); //B
            case 7: mutateColor(c, 3); //A
        }
        return c;
    }

    private Polygon mutatePolygon(Polygon p) {
        if (rand.nextBoolean()) {
            int colorMutation = rand.nextInt(4);
            switch (colorMutation) {
                case 0: mutateColor(p, 0);
                case 1: mutateColor(p, 1);
                case 2: mutateColor(p, 2);
                case 3: mutateColor(p, 3);
            }
        } else {
            if (rand.nextBoolean()) {
                int position = rand.nextInt(p.getX().length);
                p.getX()[position] = bound(p.getX()[position] + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.width);
                p.getY()[position] = bound(p.getY()[position] + rand.nextInt((int)maxDelta) - (int)halfDelta, 0, s.height);
            } else {
                p.setZ(rand.nextInt(1000));
            }
        }
        return p;
    }

}
