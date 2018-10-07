package main.gene.mutate;

import main.gene.shape.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Mutator.java
public interface Mutator {

    Shape mutate(Shape shape, float probability);

}
