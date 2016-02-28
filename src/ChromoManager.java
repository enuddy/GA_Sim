import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Evan_Nudd on 2/24/16.
 */
public abstract class ChromoManager {

    public static final float CROSSOVER_RATE = 0.7f;
    public static final float MUTATE_RATE = 01;


    public static void assignFitness(Chromosome chromos, char target) {
        int fitness = 0;

        for (int i = 0; i < chromos.getBits().length(); i++) {
            if (chromos.getBits().charAt(i) == target) {
                fitness++;
            }
        }


        if (fitness == Chromosome.SIZE)
            chromos.setFitness(999.0);
        else {
            double newFittness = (1 / (double)(Chromosome.SIZE - fitness));
          //  System.out.println("Fitnesss: " + fitness + " | NewFitness Value: " + newFittness);
            chromos.setFitness(newFittness);
        }
    }

    public static void CROSSOVER(Chromosome chrome1, Chromosome chrome2) {

        if (chrome1.getBits().length() != chrome2.getBits().length()) {
            System.out.println("Weird error, chromes are not the correct length");
            System.out.println(chrome1.getBits() + ": " + chrome1.getFitness());
            System.out.println(chrome2.getBits() + ": " + chrome2.getFitness());

        }
        else if (Math.random() < CROSSOVER_RATE) { //    Using random to decide to crossover or not.
            String c1 = chrome1.getBits();
            String c2 = chrome2.getBits();

            int crossIndex = (int)(Math.random() * c1.length());
            //System.out.println(c1.length() + " : " + c2.length() + "| Crossindex: " + crossIndex);
            String temp1 = c1.substring(0, crossIndex) + c2.substring(crossIndex);
            String temp2 = c2.substring(0, crossIndex) + c1.substring(crossIndex);

            chrome1.setBits(temp1);
            chrome2.setBits(temp2);
        }
    }

    public static void MUTATE(Chromosome chrome) {
        Random r = new Random();

        String bits = chrome.getBits();
        String newBits = new String();
        char bit;

        for (int i = 0; i < bits.length(); i++) {
            if (Math.random() < MUTATE_RATE)
                bit = (char)(r.nextInt(26) + 'a');
            else
                bit = bits.charAt(i);

            newBits+=bit;
        }

        chrome.setBits(newBits);
    }

    public static void RANDOM_GENERATE(ArrayList<Chromosome> chromes, final int size) {
        String bits = "";
        Random r = new Random();

        for (int i =0; i < size; i++){
            Chromosome chromo = new Chromosome();
            bits = "";

            for (int x = 0; x < Chromosome.SIZE; x++) {
                bits += (char)(r.nextInt(26) + 'a');
            }
            chromo.setBits(bits);
            chromes.add(chromo);
        }
    }

    public static Chromosome rouletteSelection(ArrayList<Chromosome> chromos, double totalFitness) {
        final double slicePoint = Math.random() * totalFitness;
        double currentFittness = 0.0;

        for (Chromosome chromo : chromos) {
            currentFittness += chromo.fitness;

            if (currentFittness >= slicePoint) {
                return chromo;
            }
        }

        return null;
    }

    public static Chromosome getHighestFitness(ArrayList<Chromosome> chromes) {
        Chromosome highestFitnessChromo = new Chromosome();
        highestFitnessChromo.setFitness(0.0);


        for (int i = 0; i < chromes.size(); i++) {
            if (chromes.get(i).getFitness() > highestFitnessChromo.getFitness()) {
                highestFitnessChromo = chromes.get(i);
            }
        }

        return highestFitnessChromo;
    }

    public static double getTotalFitness(ArrayList<Chromosome> chromos) {
        double fitness = 0.0;

        for (Chromosome chrome: chromos) {
            fitness += chrome.getFitness();
        }

        return fitness;
    }
}
