package edu.wctc;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<>();


    public Main() {
        String line;
        String[] words;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]", "")
                    .toLowerCase().trim();

            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }

            // Split string over one or more spaces
            words = line.split(" +");

            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                } else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            }
        }

        // Loop over entries in the map, getting each key/value pair
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //My stuff:
        Map.Entry<String,Integer>[] watchMojo = new Map.Entry[10]; //<String,Integer> is required for it expect proper value and key types it seems


            for(Map.Entry<String , Integer> e : map.entrySet()){
                for(int i = 9; i>=0; i--){
                    if((watchMojo[i] == null)){
                        watchMojo[i] = e;
                    }
                    else if((watchMojo[i].getValue() < e.getValue())){
                        if(i>0&&!(watchMojo[i-1].getValue() <= e.getValue()||watchMojo[i-1] == null)){
                            if(i<9){
                                Map.Entry<String,Integer> bob = watchMojo[i];
                                Map.Entry<String,Integer> fred = watchMojo[i];
                                watchMojo[i] = e;
                                for(int counting = i+1; counting<=9;counting++){
                                    fred = watchMojo[counting];
                                    watchMojo[counting] = bob;
                                    bob = fred;
                                }
                            }
                            else watchMojo[i] = e;
                        }
                        else if(i==0){

                            if(i<9){
                                Map.Entry<String,Integer> bob = watchMojo[i];
                                Map.Entry<String,Integer> fred = watchMojo[i];
                                watchMojo[i] = e;
                                for(int counting = i+1; counting<=9;counting++){
                                    fred = watchMojo[counting];
                                    watchMojo[counting] = bob;
                                    bob = fred;
                                }
                            }
                        }

                    }

                }


            }

        System.out.println("Top Ten Most Written Words:");
        int counting = 0;
        for(Map.Entry<String,Integer> e : watchMojo){
            System.out.println(++counting +": "+e.getKey() + " is used " + e.getValue()+" times");
        }

        System.out.println("Words used once:");
        int h = 10;
        for(Map.Entry<String , Integer> e : map.entrySet()){
            if(e.getValue()==1){
                h--;
                System.out.print(e.getKey());
                if(h==0){
                    System.out.println();
                    h = 10;
                }
                else System.out.print(", ");
            }
        }

    }

    public static void main(String[] args) {
        new Main();
    }



}