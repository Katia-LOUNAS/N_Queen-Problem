package sample;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class A2{

  public static void main(String[] args) {
      int n = 10;
      //Vector<Integer> Etat = new Vector<Integer>();
      int[] Action = {0, 0};// 0: la ligne , 1 : colonne
      int cout_chemin = 0, profondeur = 0;
      int[] Etat = new int[n];


      Noeud racine = new Noeud(n, Etat, null, null, Action, cout_chemin, profondeur);


      Info noeud = heuristiqueA2(racine);

      for(int i=0 ; i<n ; i++)
      {
          System.out.print (noeud.getNode().getT()[i] +" ");
      }
      System.out.println("fin ");
  }

    //----------------------------------------
    //        methode A*   fonction heuristique
    //----------------------------------------
    public static Info heuristiqueA2(Noeud n) {
        int h = 0;
        //une liste qui trie les elements par ordre croissant
        PriorityQueue<Noeud> ouverte = new PriorityQueue<Noeud>();
        /*ferme c'est une liste qui va contenir les
	        Etats parcourus */
        LinkedList<Noeud> ferme = new LinkedList<Noeud>();
        //ajouter le premier noeud parent a ouvert
        ouverte.add(n);
        int k=0,nb=1;
        while (!ouverte.isEmpty()) {
            k=k+1;

            //lire le premier element de ouvert et le supprimer
            Noeud courant = ouverte.poll();
            //l'ajouter  a ferme
            ferme.add(courant);
            //on n'est pas arrive aux feuilles
            if ( (courant.getN()==courant.getAction()[0]+1) && (courant.validation(courant.getT(), (courant.getAction()[0]+1))) ) {
                Info resultat=new Info(courant,k,nb);
                return resultat;
            }
            if (courant.getN()!=courant.getAction()[0]+1) {
                //generer les noeuds fils de courant
                for (int i = 0; i < courant.getN(); i++) {
                    int[] Act;
                    //int[] Act = {courant.getAction()[0]+1, i};
                    int[] E=new int[courant.getN()];
                    for(int j=0;j<=courant.getAction()[0];j++){
                        E[j]=courant.getT()[j];
                    }
                    if (k==1){
                        Act =new int[] {courant.getAction()[0], i};
                        E[courant.getAction()[0]]=i;
                    }
                    else{
                        Act =new int[] {courant.getAction()[0]+1, i};
                        E[courant.getAction()[0]+1]=i;
                    }
                    //recuperer la difference entre les sommes des deux sous-ensembles S0 et S1

                    Noeud courantfils= new Noeud(n.getN(),E , courant, null, Act,  courant.getCout_chemin() + 1, courant.getProfondeur() + 1);
                    nb=nb+1;
                    h = courant.Conflit(courant,(courantfils.getAction()[0]));
                    // g va contenir le niveau dans l'arbre
                    int g=courant.getAction()[0];
                    //f = la difference + le niveau
                    int f=h+g;
                    // courant.getNoeud_enfant().add(courantfils);
                    if ((courant.validation(courantfils.getT(), (courantfils.getAction()[0])))) {
                        //  courant.setAjout_enfant(courantfils);
                        ouverte.add(courantfils);
                    }
                }
                ///
            }

        }

        return null;
    }

}





