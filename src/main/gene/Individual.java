package main.gene;

import main.gene.shape.Shape;
import java.util.ArrayList;

public class Individual {
    private ArrayList<Shape> dna;
    private double fitness;

    public final ArrayList<Shape> getDna() {
        return this.dna;
    }

    public final double getFitness() {
        return this.fitness;
    }

    public final void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Individual(ArrayList<Shape> dna, double fitness) {
        this.dna = dna;
        this.fitness = fitness;
    }
}