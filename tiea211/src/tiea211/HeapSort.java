package tiea211;

import java.util.Random;


/**
 * @author vilij
 * @version 28.3.2019
 * Heap Sort implementaatio Javalla
 */
public class HeapSort {
    static int koko;


static void teeKeko(int[] lista, int indeksi) {


int ekaindeksi = indeksi*2;
int tokaindeksi = indeksi*2 + 1;
int uusiindeksi = indeksi;


if(ekaindeksi <= koko && lista[ekaindeksi]> lista[indeksi] )
    uusiindeksi = ekaindeksi;
if(tokaindeksi <= koko && lista[tokaindeksi] > lista[uusiindeksi] )
    uusiindeksi = tokaindeksi;

if(uusiindeksi != indeksi) {
    teeSiirto(lista, indeksi, uusiindeksi);
    teeKeko(lista, uusiindeksi);
}
}


static void teeSiirto(int[]lista, int j, int k) {
   int apuluku = lista[j];
   lista[j]= lista[k];
   lista[k]= apuluku;
}

static void sort(int[] lista) {
koko = lista.length - 1;
for(int i =koko/2; i >=0; i--)
    teeKeko(lista, i);
for(int i = koko; i>=0;i--) {
    teeSiirto(lista, 0, i);
    koko--;
    teeKeko(lista, 0);
}
}

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int[] omaLista =  new int[8];
        Random random = new Random();
        for(int i = 0; i<omaLista.length; i++)  omaLista[i] = random.nextInt();
         
        sort(omaLista);
            System.out.println(java.util.Arrays.toString(omaLista));
            ;
    }

}
