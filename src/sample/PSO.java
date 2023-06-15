package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class PSO {
    //----------------------------------------
    //             main	class
    //----------------------------------------

    public static void main(String[] args) {


        int size=10;
        int sizePopulation=30;
        Noeud best;



        best=pso(sizePopulation,size);

    }
    public static Noeud pso(int size,int sizePopulation){

        //initialisation de la population
        ArrayList<Noeud> population=new ArrayList<Noeud>();
        PriorityQueue<Noeud> bestsolution = new PriorityQueue<Noeud>();
        population=genererPopulation(sizePopulation,size);
        print(population);

        //Initialisation de GBest et Pbest
        Noeud GBest = population.get(0);
        ArrayList<Noeud> PBest = new ArrayList<Noeud>();
        for (Noeud n : population) {
            PBest.add(n);
        }

        //generation des deux vecteurs V et X
        ArrayList<Noeud> X = new ArrayList<Noeud>();
        ArrayList<Noeud> V = new ArrayList<Noeud>();
        for (Noeud p : population) {
            Noeud v = new Noeud(size, genererTableauEtat(size), null, null, null, size-1, size-1);
            V.add(v);
            Noeud x = new Noeud(size, genererTableauEtat(size), null, null, null, size-1, size-1);
            X.add(x);
        }





        return bestsolution.poll();

    }



    //----------------------------------------------------------------------
    // Class pour generer  le vecteur Etat


    public static int[] genererTableauEtat(int n) {
        int x,i=0;
        Random random = new Random();
        int[] Etat=new int[n];
        while (i<Etat.length){
            x= random.nextInt(n );
            Etat[i]=x ; i++;
        }
        return Etat;
    }



    //----------------------------------------------------------------------
    // Class pour generer une population


    public static ArrayList<Noeud> genererPopulation(int POPULATION_SIZE, int size) {

        ArrayList<Noeud> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int[] etat = genererTableauEtat(size);
            Noeud noeud = new Noeud(size, etat, null, null, null, -1, -1);
            population.add(noeud);
        }
        return population;
    }





    public static void print(ArrayList<Noeud> liste){
        for (int i=0;i<liste.size();i++){
            for (int j=0;j<liste.get(i).getT().length;j++){
                System.out.print(" "+liste.get(i).getT()[j]);
            } System.out.println();
        }

    }
}
