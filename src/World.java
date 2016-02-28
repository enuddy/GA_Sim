import java.util.ArrayList;

/**
 * Created by Evan_Nudd on 2/25/16.
 */
public class World {

    public static final int POP_SIZE = 100;

    private Window mCurrentWindow;

    public World(Window window) {
        mCurrentWindow = window;
    }

    public void runExperiment(char target) {
        boolean solutionFound = false;
        int     totalGenerations = 0;
        int maxGenerations = 1000;

        // create random chromes of populations size
        ArrayList<Chromosome> chromes = new ArrayList<>();
        ArrayList<Chromosome> newPopulation = new ArrayList<>();
        ChromoManager.RANDOM_GENERATE(chromes, POP_SIZE);

        do {
            newPopulation.clear();

            totalGenerations++;

            // assign the fitnesses
            for (int i = 0; i < chromes.size(); i++)
                ChromoManager.assignFitness(chromes.get(i), 'e');

            // calculate total fitness
            double totalFitness = ChromoManager.getTotalFitness(chromes);

            // display data to the window
            Chromosome bestChromo = ChromoManager.getHighestFitness(chromes);
            mCurrentWindow.setGenerationTo(totalGenerations);
            mCurrentWindow.changeLabelTo(bestChromo.getBits());
            System.out.println("Generation: " + totalGenerations + " |   Best Chromosome: " + bestChromo.getBits() + " |   total fittness: " + totalFitness);

            if (bestChromo.getFitness() == 999.0) {
                solutionFound = true;
            }

            // crossover to create the new population
            while (newPopulation.size() < POP_SIZE) {
                Chromosome chrome1 = ChromoManager.rouletteSelection(chromes, totalFitness);
                Chromosome chrome2 = ChromoManager.rouletteSelection(chromes, totalFitness);

                ChromoManager.CROSSOVER(chrome1, chrome2);
                ChromoManager.MUTATE(chrome1);
                ChromoManager.MUTATE(chrome2);

                newPopulation.add(chrome1);
                newPopulation.add(chrome2);
            }

            // reassign the population to the new population.
            chromes.clear();
            chromes.addAll(newPopulation);


            // sleep the thread so we can actually watch it work.
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } while (!solutionFound && maxGenerations > totalGenerations);
    }

}
