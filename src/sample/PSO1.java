package sample;

import java.util.*;

public class PSO1 {
    public static void main(String[] args) {
//        int  N = 20;
//        int  num_particles = 20;
//        int max_iter = 1000;
//        double w = 0.25;
//        double c1 = 1;
//        double c2 = 1;
//
//
//        int[] gbest_position = new int[N];
//        int[][] particles = new int[num_particles][N];
//        int[][] velocity = new int[num_particles][N];
//        int[] pbest_score = new int[num_particles];
//        int[][] pbest_position = new int[num_particles][N];
//        int gbest_score = Integer.MAX_VALUE;
//
//
//        //----------------------------------------------------------------------
//        //        Génerer  particle
//
//            ArrayList<Noeud> particle = new ArrayList<>();
//
//            for (int i = 0; i < num_particles; i++) {
//                int[] etat = genererTableauEtat(N);
//
//                Noeud noeud = new Noeud(N, etat, null, null, null, N-1, N-1);
//
//                particle.add(noeud);
//                for (int j = 0; j < N; j++) {
//
//                    velocity[i][j] = 0;
//                }
//
//
//                pbest_score[i] = Integer.MAX_VALUE;
//            }
//            gbest_position=PSOrecherche2(particle,velocity,pbest_score,pbest_position,gbest_score,gbest_position,max_iter,w,c1,c2);
           // System.out.println("sol "+calculate_fitness(gbest_position));

        ArrayList<Integer> time = new ArrayList<>(Arrays.asList( 10, 30, 60, 100));
        ArrayList<Float> rate = new ArrayList<>(Arrays.asList(0.1f, 0.3f, 0.5f, 0.7f, 0.9f));
        ArrayList<Integer> pop = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        ArrayList<Integer> iter = new ArrayList<>(Arrays.asList(200, 400, 600, 800, 100));

        Noeud noeud=null;

        for(int ii:time) {
            for(float jj:rate) {
                long som=0;
                for(int k=1; k<=2;k++) {
                    int  N = ii;
                    int  num_particles = 20;
                    int max_iter = 1000;
                    double w = jj;
                    double c1 = 1;
                    double c2 = 1;


                    int[] gbest_position = new int[N];
                    int[][] particles = new int[num_particles][N];
                    int[][] velocity = new int[num_particles][N];
                    int[] pbest_score = new int[num_particles];
                    int[][] pbest_position = new int[num_particles][N];
                    int gbest_score = Integer.MAX_VALUE;


                    //----------------------------------------------------------------------
                    //        Génerer  particle

                    ArrayList<Noeud> particle = new ArrayList<>();

                    for (int i = 0; i < num_particles; i++) {
                        int[] etat = genererTableauEtat(N);

                        noeud = new Noeud(N, etat, null, null, null, N-1, N-1);

                        particle.add(noeud);
                        for (int j = 0; j < N; j++) {

                            velocity[i][j] = 0;
                        }


                        pbest_score[i] = Integer.MAX_VALUE;
                    }


                    long startTime = System.nanoTime();
                    gbest_position=PSOrecherche2(particle,velocity,pbest_score,pbest_position,gbest_score,gbest_position,max_iter,w,c1,c2);
                    long endTime = System.nanoTime();
                    long timeElapsed = endTime - startTime;
                    som=som+timeElapsed;
                }

                //som=som/10;
                som=noeud.Conflit(noeud, noeud.getN());
                System.out.print(som + ",");
            }
            System.out.println(" ");
            System.out.println(ii);
        }


        //if(calculate_fitness(gbest_position)==0) {   print(gbest_position,gbest_position.length);}

    }

    public static void print(int[] liste,int taille){

            for (int j=0;j<taille;j++){
                System.out.print(" "+liste[j]);
            } System.out.println();
        }


    //----------------------------------------------------------------------
    // Class pour generer  le vecteur Etat
    //----------------------------------------------------------------------

    public static int[] genererTableauEtat(int n) {
        int x,j=0;
        Random random = new Random();
        int[] Etat=new int[n];
        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=i;
        }

        ArrayList<Integer> list = new ArrayList<Integer>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < arr.length; i++) {
            Etat[i] = list.get(i);
        }

        return Etat;
    }

    //----------------------------------------------------------------------
    // Class pour calculer fitness
    //----------------------------------------------------------------------

    public  static int calculate_fitness(int[] Etat) {
        //verifier si meme colonne
        int taille=Etat.length;
        int conflit=0;
        for(int i=0 ; i<taille-1 ; i++) {
            for (int j = i + 1; j <taille; j++) {
                if (Etat[i] == Etat[j]) {
                    conflit++;
                }
            }
        }
        //verifier si meme diag
        int i=0;
        while(i<taille-1){
            if((Math.abs((Etat[i]) - (Etat[i+1])))==1){
                conflit++;
            }
            i++;

        }
        if (taille>0){
            if ((Etat[0]==0)&&(Etat[taille-1]==taille-1))    conflit++;
        }
        for( i=0 ; i<taille ; i++)
        {  int i1=i,j=Etat[i];
            //verifier si meme A p haut
            while((i1>0)&&(j>0)) {
                i1=i1-1;j=j-1;
                if( (j==Etat[i1])){
                    conflit++;
                }
            }

            //verifier si meme B p bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j<taille-1)) {
                i1=i1+1;j=j+1;
                if( (j==Etat[i1])){
                    conflit++;
                }

            }

            //verifier si meme C  I bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j>0)) {
                i1=i1+1;j=j-1;
                if( (j==Etat[i1])){
                    conflit++;
                }
            }

            //verifier si meme D I haut
            i1=i;
            j=Etat[i];
            while((i1>0)&&(j<taille)) {
                i1=i1-1;j=j+1;
                if( (j==Etat[i1])){
                    conflit++;
                }
            }


        }


        return conflit;
    }


    //----------------------------------------------------------------------
    // Class PSO
    //----------------------------------------------------------------------

    public static int[] recherchelocale1(int[] parent){
       ArrayList <int[]> ouverte = new ArrayList<int[]>();
        int[] courant=new int[parent.length];
        int[] best=new int[parent.length];
        int[] Act =new int[] {parent.length-1, parent.length-1};
            int temp,score=calculate_fitness(parent),k=0;
        ouverte.add(parent);
        best[k]=score;
        k++;
        for (int i=0;i<courant.length-1;i++){
            int j=i+1;
            if (i>0) {
                temp = parent[i - 1];
                parent[i - 1] = parent[courant.length-1];
                parent[courant.length-1] = temp;
            }
            while (j<courant.length){
                int[] newelement=new int[courant.length];

                newelement=parent;

                temp=newelement[i];
                newelement[i]=newelement[j-1];
                newelement[j-1]=temp;

                temp=newelement[i];
                newelement[i]=newelement[j];
                newelement[j]=temp;

                if ((calculate_fitness(newelement)) < score){
                    score=(calculate_fitness(newelement));
                    ouverte.add(Arrays.copyOf(newelement,newelement.length));
                    best[k]=score;k++;
                  }
                j++;
            }
        }
        return ouverte.get(ouverte.size()-1);
    }



      public static int[] PSOrecherche2(ArrayList<Noeud> particles,int[][] velocity, int[] pbest_score, int[][]  pbest_position,int gbest_score , int[] gbest_position,int max_iter, double w, double c1 , double c2){
        Random random = new Random();
        int num_particles=particles.size();
        int N=particles.get(0).getN();
        // PSO loop
        for (int iter = 0; iter < max_iter; iter++) {
            for (int i = 0; i < num_particles; i++) {
                int[] position = particles.get(i).getT();
                // Calcul du cout
                int score = calculate_fitness(position);
                if (score==0) return position;
                // Update personal best
                if (score < pbest_score[i]) {
                    pbest_score[i] = score;
                    pbest_position[i] = position;
                }

                // Update global best
                if (score < gbest_score) {
                    gbest_score = score;
                    gbest_position = Arrays.copyOf(position, position.length);
                }

                // Update velocity
                for (int j = 0; j < N; j++) {
                    int rp = random.nextInt(num_particles);
                    int rg = random.nextInt(num_particles);
                    velocity[i][j] = (int) (w * velocity[i][j] + c1 * random.nextDouble() * (pbest_position[i][j] - position[j])
                            + c2 * random.nextDouble() * (gbest_position[j] - position[j]));
                }
                // swap du tableau position
                ArrayList<int[]> listepos = new ArrayList<int[]>(3);
                listepos.add(position);
                ArrayList<Integer> list = new ArrayList<Integer>(position.length);
                for (int k : particles.get(i).getT()) {
                    list.add(k);
                }
                Collections.shuffle(list);
                int[] positionp1=new int[N];
                for (int k = 0; k < position.length; k++) {
                    positionp1[k] = list.get(k);
                }
                //l'ajouter a la liste
                listepos.add(positionp1);
                //on applique une recherche locale
                int[] positionp2=new int[N];
                if ((calculate_fitness(listepos.get(0))) < gbest_score ){
                    if ((calculate_fitness(listepos.get(0))) <= (calculate_fitness(listepos.get(1)))) {
                        positionp2=recherchelocale1(Arrays.copyOf(particles.get(i).getT(), position.length));}
                    else {
                        if ((calculate_fitness(listepos.get(1))) < gbest_score) {
                            positionp2 = recherchelocale1(Arrays.copyOf(listepos.get(1), position.length));
                        }
                        else positionp2=recherchelocale1(Arrays.copyOf(gbest_position, position.length));
                    }
                }

                else {
                    if ((calculate_fitness(listepos.get(1))) < gbest_score) {
                        positionp2 = recherchelocale1(Arrays.copyOf(listepos.get(1), position.length));
                    }
                    else positionp2=recherchelocale1(Arrays.copyOf(gbest_position, position.length));
                }
                listepos.add(positionp2);
                //on recupere la meilleure solution
                if ((calculate_fitness(listepos.get(0))) > (calculate_fitness(listepos.get(1)))) {
                    if ((calculate_fitness(listepos.get(1))) > (calculate_fitness(listepos.get(2))))
                          { particles.get(i).setT(listepos.get(2));}
                    else   { particles.get(i).setT(listepos.get(1)); }
                }
                else  if ((calculate_fitness(listepos.get(0))) >= (calculate_fitness(listepos.get(2)))){
                    particles.get(i).setT(listepos.get(2));  }
                     else {particles.get(i).setT(listepos.get(0)); }

            }

        }
        return gbest_position;
    }
}
