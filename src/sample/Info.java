package sample;

public class Info {
    Noeud node;
    int nbr,nbrg; //nbrg: nbr de node genere   |   nbr:nbr de node devlope

    public Info(Noeud node, int nbr, int nbrg) {
        this.node = node;
        this.nbr = nbr;
        this.nbrg = nbrg;
    }

    public Noeud getNode() {
        return node;
    }

    public void setNode(Noeud node) {
        this.node = node;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public int getNbrg() {
        return nbrg;
    }

    public void setNbrg(int nbrg) {
        this.nbrg = nbrg;
    }
}
