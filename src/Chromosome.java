/**
 * Created by Evan_Nudd on 2/24/16.
 */


public class Chromosome {

    public static final double SIZE = 20;

    String bits;
    double fitness;

    public Chromosome() {
        bits =  "";
        fitness = 0.0;
    }

    public Chromosome(String bits) {
        this.bits = bits;
        fitness = 0.0;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}
