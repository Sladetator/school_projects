package tiea211;




/**
 * @author vilij
 * @version 4.4.2019
 *Hash implementaatio javalla
 *Johtuen siitä, että 0 merkitsee vapaata paikkaa, taulukon ensimmäinen paikka ei täyty syötteellä 1
 *
 */
public class HashFunc {
    public static int HASHTABLESIZE = 10;
    
    
    public static int hashfunc(int integer_key)
    {
    return integer_key % HASHTABLESIZE;
    }
    
    public static int[] put(int input, int[] table){
        for (int i = hashfunc(input); i <= table.length;i++) {
            if(table[i] == input) {System.out.println("luku " + input + " on jo tallennettu"); return table;}
            if(table[i] == 0) {table[i] = input; return table;}
            if(table[i] == -1) {table[i] = input; return table;}
            if( i == HASHTABLESIZE-1) i=0;
            if(table[i] == input) {System.out.println("luku " + input + " on jo tallennettu"); return table;}
            if(table[i] == 0) {table[i] = input; return table;}
            if(table[i] == -1) {table[i] = input; return table;}
            
        }
        return table;
    }
    
    public static int[] remove(int input, int[] table){
        boolean kierretty = false;
        for (int i = hashfunc(input); i < table.length; i++) {
            //if(table[i] == 0) {return table;}
            //if(table[i] == -1) {return table;}
            if(table[i] == input) {table[i] = -1;
            return table;
            }
            if(kierretty && (i == HASHTABLESIZE-1) ) {
                System.out.println("lukua ei löydetty");
                return table;
            }
            if( i == HASHTABLESIZE-1) i=0;
            kierretty = true;
            
            
        }
        return table;
    }
    
    

    public static void main(String[] args) {
        int[] table = new int[HASHTABLESIZE];

       put(19, table);
       put(29, table);
       put(39, table);

       put(39, table);
      

       System.out.println(java.util.Arrays.toString(table));

       remove(29, table); 
       System.out.println(java.util.Arrays.toString(table));
       remove(20, table);
       remove(20, table);
       put(99, table);
       System.out.println(java.util.Arrays.toString(table));

    }

}
