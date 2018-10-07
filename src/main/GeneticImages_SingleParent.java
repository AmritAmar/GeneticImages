package main;

import java.awt.*;
import java.util.ArrayList;

import main.gene.GeneticImg;
import main.gene.Individual;
import main.fitness.ImageCompare;
import main.gene.mutate.IncrMutator;
import main.gene.shape.Shape;
import main.gene.shape.Shape.ShapeType;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GeneticImages_SingleParent {

    public static Individual mostFit;

    public static class Settings {
        public int width;
        public int height;
        public int numOfGenes;
        public int populationCount;
        public float mutationProbability;
        public ArrayList<ShapeType> shapes;
        public int pixelSize;
        public boolean useAlpha;
        public int maxPolygonSize;

        public Settings (int width, int height, int numOfGenes, int populationCount, float mutationProbability,
                         ArrayList<ShapeType> shapes, int pixelSize, boolean useAlpha, int maxPolygonSize) {
            this.width = width;
            this.height = height;
            this.numOfGenes = numOfGenes;
            this.populationCount = populationCount;
            this.mutationProbability = mutationProbability;
            this.shapes = shapes;
            this.pixelSize = pixelSize;
            this.useAlpha = useAlpha;
            this.maxPolygonSize = maxPolygonSize;
        }
    }

    public static void main(String args[]) throws IOException {

        //Enter FileName Here
        String filename = "eiffel2.png";
        File file = new File(filename);

        BufferedImage targetImage = ImageIO.read(file);

        //Adjust shapes in the program here
        ArrayList<ShapeType> s = new ArrayList<>();
        s.add(ShapeType.RECTANGLE);
        s.add(ShapeType.ELLIPSE);
        s.add(ShapeType.PIXEL);
        //s.add(ShapeType.CIRCLE);
        //s.add(ShapeType.POLYGON);

        //Adjust this for your simulation
        Settings curSet = new Settings(
                targetImage.getWidth(),
                targetImage.getHeight(),
                256,
                20,
                0.01f,
                s,
                8,
                true,
                3);

        IncrMutator m = new IncrMutator(curSet);
        GeneticImg genetic = new GeneticImg(curSet);

        ImageCompare fitness = new ImageCompare(2);

        BufferedImage canvas = new BufferedImage(curSet.width, curSet.height, BufferedImage.TYPE_INT_ARGB);
        Graphics canvasG = canvas.getGraphics();
        BufferedImage Fittestcanvas = new BufferedImage(curSet.width, curSet.height, BufferedImage.TYPE_INT_ARGB);
        Graphics FittestcanvasG = Fittestcanvas.getGraphics();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(curSet.width + 20, curSet.height + 20);
        frame.setVisible(true);

        mostFit = genetic.newIndividual();
        double FittestScore = Double.MAX_VALUE;
        //Random r = new Random();
        int i = 0;

        JPanel panel = new JPanel() {
            //overriding method here
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                genetic.drawMe(FittestcanvasG, mostFit);
                g.drawImage(Fittestcanvas, 0,0, curSet.width, curSet.height, this);
            }
        };
        frame.add(panel);
        panel.revalidate();

        do {
            Individual child = m.mutateI(genetic.copy(mostFit), curSet.mutationProbability);

            // evaluate fitness
            canvasG.setColor(Color.BLACK);
            canvasG.clearRect(0,0,curSet.width,curSet.height);
            genetic.drawMe(canvasG, child);

            double fitVal = fitness.compare(canvas, targetImage);

            if (fitVal <= FittestScore) {
                System.out.println(i + ", " + fitVal);
                mostFit = child;
                FittestScore = fitVal;
            }
            panel.repaint(); // must redraw as that's what actually draws to the canvas
            i++;
        } while (FittestScore > 0);
        ImageIO.write(Fittestcanvas, "png",  new File("/tmp/evolved.png"));
    }
}
