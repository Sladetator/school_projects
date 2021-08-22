package tiea211;

import java.util.Arrays;

/*
 * @author vilij 25.4.2019
 *luokka kapsäkkiongelman ratkaisemiseen
 */
class Kapsakki {

    /*
     * palauttaa suuremman kahdesta luvusta
     */
    private static int max(int j, int k) {
        return (j > k) ? j : k;
    }


    /*
     * Aliohjelma joka palauttaa suurimman mahdollisen "hyötyarvon"
     */
    private static int kapsakkiMax(int painot[], int arvot[], int n,
            int maxpaino) {

        int s[][] = new int[n + 1][maxpaino + 1];
        // apumuuttujat
        int i;
        int w;

        // taulukon muodostus
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxpaino; w++) {
                if (i == 0 || w == 0) //
                    s[i][w] = 0;
                else if (painot[i - 1] <= w) {
                    s[i][w] = max(arvot[i - 1] + s[i - 1][w - painot[i - 1]],
                            s[i - 1][w]);
                } else {
                    s[i][w] = s[i - 1][w];
                }
            }
        }
        // System.out.println(Arrays.deepToString(s)); //tulostaa taulukon
        // sisällön
        return s[n][maxpaino];
    }


    /*
     * Pääohjelma jolla testataan luokkaa
     */
    public static void main(String args[]) {
        
        int maxpaino = 6;
        int arvot[] = new int[] { 6, 10, 12, 5 };
        int painot[] = new int[] { 2, 4, 3, 5 };
        int määrä = arvot.length;
        System.out.println(kapsakkiMax(painot, arvot, määrä, maxpaino));
    }
}
