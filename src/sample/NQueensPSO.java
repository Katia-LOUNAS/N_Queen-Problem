package sample;

import java.util.BitSet;

public class NQueensPSO {

    // Taille du plateau
    private int N;

    // Nombre de bits pour encoder une position sur le plateau
    private int numBits;

    // Initialisation du problème
    public NQueensPSO(int N) {
        this.N = N;
        this.numBits = (int) Math.ceil(Math.log(N) / Math.log(2));
    }

    // Encode un vecteur de positions en binaire
    public BitSet encode(int[] positions) {
        BitSet bits = new BitSet(N * numBits);
        for (int i = 0; i < N; i++) {
            int pos = positions[i];
            int offset = i * numBits;
            for (int j = 0; j < numBits; j++) {
                if ((pos & (1 << j)) != 0) {
                    bits.set(offset + j);
                }
            }
        }
        return bits;
    }

    // Decode un BitSet en un vecteur de positions
    public int[] decode(BitSet bits) {
        int[] positions = new int[N];
        for (int i = 0; i < N; i++) {
            int offset = i * numBits;
            int pos = 0;
            for (int j = 0; j < numBits; j++) {
                if (bits.get(offset + j)) {
                    pos |= (1 << j);
                }
            }
            positions[i] = pos;
        }
        return positions;

    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getNumBits() {
        return numBits;
    }

    public void setNumBits(int numBits) {
        this.numBits = numBits;
    }
}
