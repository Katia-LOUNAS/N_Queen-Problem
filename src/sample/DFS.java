package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class DFS {
  public static void main(String[] args) {
        int n = 4; // taille du plateau
        int[] E = new int[n];
        int[] T= {0,0};
        LinkedList<Noeud> N= new LinkedList<>();
        Noeud node= new Noeud(n,E,null,N,T,0,0);
        Info Etatfin;

        //Etatfin=dfs(node,0,false);
        LinkedList<Noeud> listefifo = new LinkedList<Noeud>();
        LinkedList<Noeud> listefermé = new LinkedList<Noeud>();

        //Vector<Integer> Etatfin = new Vector<Integer>();
        Etatfin=ParcoursdFS(node,listefifo,listefermé);


    }




    public static Info ParcoursdFS(Noeud noeud_parent, LinkedList<Noeud> listeouvert,LinkedList<Noeud> listefermé){
        int k=0,nb=1;
        listeouvert.addLast(noeud_parent);
        Noeud courant;
        while(! listeouvert.isEmpty()){
            k=k+1;
            courant = listeouvert.getFirst();
            listeouvert.removeFirst();
            listefermé.addLast(courant);
            if ( (courant.getN()==courant.getAction()[0]+1) && (courant.validation(courant.getT(), (courant.getAction()[0]+1))) ) {

                Info resultat=new Info(courant,k,nb);
                return resultat;
            }
            // Vector<Integer> E = new Vector<Integer>();
            if (courant.getAction()[0]+1!=courant.getN()){
                for (int i = courant.getN()-1 ; i >=0 ; i--) {
                    int[] Act;
                    int[] E=new int[courant.getN()];
                    for(int j=0;j<=courant.getAction()[0];j++){
                        E[j]=courant.getT()[j];
                    }
                    int taille;
                    if (k==1){
                        Act =new int[] {courant.getAction()[0], i};
                        E[courant.getAction()[0]]=i;
                         taille=courant.getAction()[0];
                    }
                    else{
                        Act =new int[] {courant.getAction()[0]+1, i};
                        E[courant.getAction()[0]+1]=i;
                         taille=courant.getAction()[0]+1;
                    }
                    Noeud courantfils= new Noeud(noeud_parent.getN(),E , courant, null, Act,  courant.getCout_chemin() + 1, courant.getProfondeur() + 1);
                    nb=nb+1;
                    if ((courant.validation(courantfils.getT(), (courantfils.getAction()[0])))) {
                        listeouvert.addFirst(courantfils);
                    }
                }
            }
        }
        return null;
    }


}