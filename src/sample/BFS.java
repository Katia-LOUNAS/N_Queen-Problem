package sample;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

public class BFS {


    public static Info ParcoursBFS(Noeud noeud_parent, LinkedList<Noeud> listeouvert,LinkedList<Noeud> listefermé){
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
            if (courant.getAction()[0]+1!=courant.getN()){
                for (int i = 0; i < courant.getN(); i++) {
                    int[] Act;
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

                    Noeud courantfils= new Noeud(noeud_parent.getN(),E , courant, null, Act,  courant.getCout_chemin() + 1, courant.getProfondeur() + 1);
                    nb=nb+1;

                    if ((courant.validation(courantfils.getT(), (courantfils.getAction()[0])))) {
                        listeouvert.addLast(courantfils);
                    }}
            }
        }
        return null;
    }


}

