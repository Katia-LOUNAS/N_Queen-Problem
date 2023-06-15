package sample;

import java.util.*;

public class GA {

    //----------------------------------------
    //             main	class
    //----------------------------------------

    public static void main(String[] args) {
        int n;
//        ArrayList<Noeud> N= new ArrayList<Noeud>();
//        int[] Etat=new int[n];
//        int[] Act =new int[] {n-1, n-1};
//        Noeud node =new Noeud(n,Etat , null, null, Act,  n-1, n-1);

//        int popsize=10;
//        Noeud best;
//        Double mutation_rate=0.1;
//        int iterations=1000;

        ArrayList<Integer> time = new ArrayList<>(Arrays.asList(10, 30, 60, 100));
        ArrayList<Float> rate = new ArrayList<>(Arrays.asList(0.1f, 0.3f, 0.5f, 0.7f, 0.9f));
        ArrayList<Integer> pop = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        ArrayList<Integer> iter = new ArrayList<>(Arrays.asList(200, 400, 600, 800, 100));
        Noeud best = null;

        for(int i:time) {
            for(Float j:rate) {
                long som=0;
                for(int k=1; k<=10; k++) {
                    n = i;
                    ArrayList<Noeud> N = new ArrayList<Noeud>();
                    int[] Etat = new int[n];
                    int[] Act = new int[]{n - 1, n - 1};
                    Noeud node = new Noeud(n, Etat, null, null, Act, n - 1, n - 1);

                    int popsize = 40;
                    Double mutation_rate = j.doubleValue();
                    int iterations = 100;

                    long startTime = System.nanoTime();
                    best = gaQueen(popsize, node, mutation_rate, iterations);
                    long endTime = System.nanoTime();
                    long timeElapsed = endTime - startTime;
                    som=som+timeElapsed;
                }

                som=som/10;
                som=best.Conflit(best, best.getN());
                System.out.print(som + ",");
            }
            System.out.println(" ");
            System.out.println(i);
        }






    }



    //----------------------------------------------------------------------
    // Class pour generer  le vecteur TR
    //----------------------------------------------------------------------

    public static boolean elementExists(int[] array, int element) {
        return Arrays.asList(array).contains(element);
    }
    //----------------------------------------------------------------------
    // Class pour generer  le vecteur Etat
    //----------------------------------------------------------------------
    public static int[] Generer_tableau_etat(int n) {
        int x,i=0;
        Random random = new Random();
        int[] Etat=new int[n];
        while (i<Etat.length){
            x= random.nextInt(n);
            List<int[]> liste = Arrays.asList(Etat);
            boolean existe = liste.contains(x);

            if (!existe) {
                Etat[i]=x ; i++;
            }

        }
        return Etat;
    }
    //----------------------------------------------------------------------
    // Class pour generer une population
    //----------------------------------------------------------------------
    public static ArrayList<Noeud> Generer_population(int nbpopulations, Noeud courant) {
        ArrayList<Noeud> population=new ArrayList<Noeud>();

        for(int i=0;i<nbpopulations;i++) {
            int[] Etat=new int[courant.getN()];
            Etat=Generer_tableau_etat(courant.getN());

            int[] Act =new int[] {courant.getN()-1, courant.getN()-1};
            Noeud newNode =new Noeud(courant.getN(),Etat , courant, null, Act,  courant.getN()-1, courant.getN()-1);

            population.add(newNode);
        }
        return population;
    }

    //----------------------------------------------------------------------
    // Class pour selectionner deux parents
    //----------------------------------------------------------------------

    public static ArrayList<Noeud> selectionner_parent12( ArrayList<Noeud> population){

        ArrayList<Noeud> parents=new ArrayList<Noeud>();
        Double[] weights=new Double[population.size()];// pour stocker le poid de chaque population
        Double sum=0.0;
        //calcul de la somme des differences de tous les elements de la population
        for (int i=0;i <= (population.size()-1);i++){
            Double conflit=Double.valueOf(population.get(i).Conflit(population.get(i), population.get(i).getN())) ;
            weights[i]=conflit;
            sum += conflit;

        }

        //poid = min/sum
        for (int i=0;i< population.size();i++){
            weights[i]=weights[i]/sum;
        }

        Noeud parent1=WeightedRandomNumberGenerator(population,weights);
        parents.add(parent1);
        Noeud parent2=WeightedRandomNumberGenerator(population,weights);
        parents.add(parent2);

        return parents;

    }

    //Pour générer des nombres aléatoires avec une probabilité pondérée
    // weights contient  les poids des différentes population
    //----------------------------------------------------------------------
    public static Noeud WeightedRandomNumberGenerator(ArrayList<Noeud> population, Double[] weights) {
        Random random = new Random();
        Double sum = 0.0;

        for (Double weight : weights) {
            sum += weight;


        }

        Noeud randomNumber=null ;
        Double randomWeight =  random.nextDouble();// le cout pour arriver à la meilleure solution

        for (int i = 0; i < population.size(); i++) {
            randomWeight -= weights[i];// à chaque itération on soustrait le cout de la population courante
            if (randomWeight < 0.0) {
                randomNumber = population.get(i);// si on atteint la solution
                break;
            }
        }
        return randomNumber;
    }
    //----------------------------------------------------------------------
    // Fonction qui croise deux parents pour generer deux enfants
    //----------------------------------------------------------------------
    public static ArrayList<Noeud> crossover(ArrayList<Noeud> parents){
        Random random = new Random();
        ArrayList<Noeud> enfants=new ArrayList<Noeud>();
        int[] Act =new int[] {parents.get(0).getN()-1, parents.get(0).getN()-1};
        int crossover_point = random.nextInt((parents.get(0).getN()-1)) + 1;
        int[] Etat1=new int[parents.get(0).getN()];

        // Copie les éléments du premier tableau dans le nouveau tableau
        System.arraycopy(parents.get(0).getT(), 0, Etat1, 0,crossover_point+1);

        // Copie les éléments du deuxième tableau dans le nouveau tableau
        System.arraycopy(parents.get(1).getT(),crossover_point+1 , Etat1, crossover_point+1,( (parents.get(0).getN()-1)-(crossover_point)));
        Noeud enfant1=new Noeud(parents.get(0).getN(), Etat1, parents.get(0), null,Act , parents.get(0).getN()-1, parents.get(0).getN()-1);

        int[] Etat2=new int[parents.get(0).getN()];
        // Copie les éléments du premier tableau dans le nouveau tableau
        System.arraycopy(parents.get(1).getT(), 0, Etat2, 0,crossover_point+1);
        // Copie les éléments du deuxième tableau dans le nouveau tableau
        System.arraycopy(parents.get(0).getT(),crossover_point+1 , Etat2, crossover_point+1, ( (parents.get(0).getN()-1)-(crossover_point)));
        Noeud enfant2=new Noeud(parents.get(0).getN(), Etat2, parents.get(1), null, Act, parents.get(0).getN()-1, parents.get(0).getN()-1);


        enfants.add(enfant1);
        enfants.add(enfant2);


        return enfants;
    }



    //----------------------------------------------------------------------
    // Fonction qui effectue une mutation aléatoire sur une solution
    //----------------------------------------------------------------------

    public static Noeud mutation(Noeud noeud,Double mutation_rate){
        // Créer une nouvelle instance de la classe Random
        Random random = new Random();
        int x;
        int[] Etat1=new int[noeud.getN()];
        for(int j=0;j<noeud.getT().length;j++){
            Etat1[j]=noeud.getT()[j];
        }

        for (int i=0;i<noeud.getT().length;i++){
            // Générer un nombre aléatoire entre 0 et 1
            double randomNumber = random.nextDouble();

            if (randomNumber < mutation_rate){
                x=((int) (Math.random() * ((noeud.getN()+1) - 1)));
                if (!Arrays.asList(Etat1).contains(x)){
                    Etat1[i]=x ;}

            }
        }
        noeud.setT(Etat1);
        return noeud;
    }

    //----------------------------------------------------------------------
    // Fonction qui résout le problème de la partition à l'aide de l'algorithme génétique
    //----------------------------------------------------------------------

    public static Noeud gaQueen( int popsize, Noeud courant,Double mutation_rate,int iterations){

        ArrayList<Noeud> population=new ArrayList<Noeud>();
        PriorityQueue<Noeud> bestsolution = new PriorityQueue<Noeud>();

        //Générer population
        population=Generer_population(popsize,courant);


        for (int i=0;i< iterations;i++){

            ArrayList<Noeud> newpopulation=new ArrayList<Noeud>();
            for (int j=0;j<popsize/2;j++){

                //Selection
                ArrayList<Noeud> parents= selectionner_parent12(population);


                //croisement
                ArrayList<Noeud> enfants= crossover(parents);
                Noeud enfant1=enfants.get(0);
                Noeud enfant2=enfants.get(1);

                //mutation
                enfant1=mutation(enfant1,mutation_rate);
                enfant2=mutation(enfant2,mutation_rate);
                enfants.set(0,enfant1);
                enfants.set(1,enfant2);
                newpopulation.add(enfant1);
                newpopulation.add(enfant2);

                bestsolution.add(enfant1);
                bestsolution.add(enfant2);


            }
            //remplacement
            population=newpopulation;

        }
        return bestsolution.poll();


    }
    public static void print(ArrayList<Noeud> liste){
        for (int i=0;i<liste.size();i++){
            for (int j=0;j<liste.get(i).getT().length;j++){
                System.out.print(" "+liste.get(i).getT()[j]);
            } System.out.println();
        }

    }
}
