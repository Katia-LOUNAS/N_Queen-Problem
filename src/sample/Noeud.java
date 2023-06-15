package sample;

import java.util.LinkedList;

public class Noeud  implements Comparable<Noeud>{
    int n;
    //Vector<Integer> Etat = new Vector<Integer>();
    int[] Etat=new int[n];
    Noeud Noeud_parent;
    // liste noeuds fils
    LinkedList<Noeud> Noeud_enfant = new LinkedList<Noeud>();
    int[]  Action=new int[1];// 0: la ligne , 1 : colonne
    int cout_chemin,profondeur;
    public Noeud(int n, int[] t, Noeud noeud_parent, LinkedList<Noeud> noeud_enfant, int[] action, int cout_chemin,
                 int profondeur) {
        super();
        this.n = n;
        this.Etat = t;
        this.Noeud_parent = noeud_parent;
        // liste noeuds fils
        this.Noeud_enfant = noeud_enfant;
        this.Action = action;
        this.cout_chemin = cout_chemin;
        this.profondeur = profondeur;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public int[] getT() {
        return this.Etat;
    }
    public void setT(int[] t) {
        Etat = t;
    }
    // public void setEtat(int t) {
    //   Etat.add(t) ;
    //}
    public Noeud getNoeud_parent() {
        return this.Noeud_parent;
    }
    public void setNoeud_parent(Noeud noeud_parent) {
        Noeud_parent = noeud_parent;
    }

    public  LinkedList<Noeud> getNoeud_enfant() {
        return this.Noeud_enfant;
    }

    public void setNoeud_enfant( LinkedList<Noeud> noeud_enfant) {
        Noeud_enfant = noeud_enfant;
    }
    public void setAjout_enfant(Noeud enfant) {
        Noeud_enfant.addLast(enfant);
    }

    public int[] getAction() {
        return Action;
    }
    public void setAction(int[] action) {
        Action = action;
    }
    public int getCout_chemin() {
        return cout_chemin;
    }
    public void setCout_chemin(int cout_chemin) {
        this.cout_chemin = cout_chemin;
    }
    public int getProfondeur() {
        return profondeur;
    }
    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public  boolean validation(int[] Etat, int taille) {
        //verifier si meme colonne
        for(int i=0 ; i<taille-1 ; i++) {
            for (int j = i + 1; j <taille; j++) {
                if (Etat[i] == Etat[j]) {
                    return false;
                }
            }
        }
        //verifier si meme diag
        int i=0;
        while(i<taille-1){
            if((Math.abs((Etat[i]) - (Etat[i+1])))==1){
                return false;
            }
            i++;

        }
        if (taille>0){
            if ((Etat[0]==0)&&(Etat[taille-1]==taille-1)) return false;
        }
        for( i=0 ; i<taille ; i++)
        {  int i1=i,j=Etat[i];
            //verifier si meme A p haut
            while((i1>0)&&(j>0)) {
                i1=i1-1;j=j-1;
                if( (j==Etat[i1])){
                    return false;
                }
            }
            //verifier si meme B p bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j<taille-1)) {
                i1=i1+1;j=j+1;
                if( (j==Etat[i1])){
                    return false;
                }

            }
            //verifier si meme C  I bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j>0)) {
                i1=i1+1;j=j-1;
                if( (j==Etat[i1])){
                    return false;
                }
            }
            //verifier si meme D I haut
            i1=i;
            j=Etat[i];
            while((i1>0)&&(j<taille)) {
                i1=i1-1;j=j+1;
                if( (j==Etat[i1])){
                    return false;
                }
            }
        }
        return true;
    }
    public int Casesvides(Noeud node,int taille){
        int k=0;
        //verifier si meme colonne
        k=(taille-1)*taille;
        for(int i=0 ; i<taille ; i++)
        {  int i1=i,j=Etat[i];
            //verifier si meme A p haut
            while((i1>0)&&(j>0)) {
                i1=i1-1;j=j-1;
                if( (j==Etat[i1])){
                    k++;
                }
            }

            //verifier si meme B p bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j<taille-1)) {
                i1=i1+1;j=j+1;
                if( (j==Etat[i1])){
                    k++;
                }

            }

            //verifier si meme C  I bas
            i1=i;
            j=Etat[i];

            while((i1<taille-1)&&(j>0)) {
                i1=i1+1;j=j-1;
                if( (j==Etat[i1])){
                    k++;
                }
            }

            //verifier si meme D I haut
            i1=i;
            j=Etat[i];
            while((i1>0)&&(j<taille)) {
                i1=i1-1;j=j+1;
                if( (j==Etat[i1])){
                    k++;
                }
            }
        }
        return k;
    }
    public int calculheuristique(int taille){
        return Noeud.this.Casesvides(Noeud.this,taille)+(Noeud.this.getAction()[0]);
    }
    @Override
    public int compareTo(Noeud other)
    {
        return Integer.compare(Noeud.this.calculheuristique((Noeud.this.getAction()[0])), other.calculheuristique((other.getAction()[0])));
    }

    public int claculheuristique2(int taille){
        return Noeud.Conflit(Noeud.this,taille)+(Noeud.this.getAction()[0]);
    }



    //--------------------------------------------------------
    //heuristique2

    public static int Conflit(Noeud node, int taille){
        int n = taille;
        int conflicts = 0;

        // Vérifie chaque paire de reines pour conflit
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (node.getT()[i] == node.getT()[j]) { // même colonne
                    conflicts++;
                } else if (i - node.getT()[i] == j - node.getT()[j]) { // même diagonale
                    conflicts++;
                } else if (i + node.getT()[i] == j + node.getT()[j]) { // même diagonale
                    conflicts++;
                }
            }
        }
        return conflicts;
    }



}
