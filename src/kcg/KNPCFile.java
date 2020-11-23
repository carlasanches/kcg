/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carla
 */
public class KNPCFile {
    
    public static List<String> reader(String path) throws IOException{
        
        String line;
        List<String> text = new ArrayList();
        
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) { 

            line = buffRead.readLine();                       
            
            while(true){
                if(line != null){
                    text.add(line);
                }
                else break;
                
                line = buffRead.readLine();
            }
        }
        
        return text;
    }
    
    public static void writer(String path, String line) throws IOException{
        
        if(!new File(path).exists()){
            new File(path).createNewFile();
        }
        
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path))) {
            buffWrite.append(line);
        }
    }
    
    public static KCG format(List<String> text){
                      
        int capacity = Integer.parseInt(text.get(0));
        int numItems = Integer.parseInt(text.get(1)); 
                
        Item item;
        List<Item> items = new ArrayList();
        
        int firstWeight = numItems + 2;
        int lastWeight = (2 * numItems) + 2;
        
        int profit;
        int weight;
        int i = 2;
        int j = firstWeight;
        
        while(i < (numItems+2) && j < (lastWeight+2)){
            
            //foi necessário tratar alguns números que estavam com algum espaço na frente.
            
            String element[] = text.get(i).split(" ");
            text.set(i, element[0]);
            
            String element1[] = text.get(j).split(" ");                
            text.set(j, element1[0]);
            
            profit = Integer.parseInt(text.get(i));
            weight = Integer.parseInt(text.get(j));

            item = new Item(profit,weight);
            items.add(item);
            
            i++;
            j++;
        }
                                
        int index = (2 * (numItems)) + 2;
        
        List<Integer> conflicts = new ArrayList();
        
        String[] conf;
        
        for(int x = index+1; x < text.size(); x++){
           conf = text.get(x).split(" ");
           conflicts.add(Integer.parseInt(conf[0]));
           conflicts.add(Integer.parseInt(conf[1]));
        }
                
        int numConflict = Integer.parseInt(text.get(index));
        
        int conflict[][] = new int[numConflict][numConflict];       
        
        int k = 0;
        int l = 1;
        
        for(int x = 0; x < numConflict; x++){
            for(int y = 0; y < numConflict; y++){                
                if(x == (conflicts.get(k)-1) && y == (conflicts.get(l)-1)){
                    conflict[x][y] = 1;
                    
                    //garante que não vai ultrapassar o tamanho de conflicts
                    if(l+2 < conflicts.size()){
                        k += 2;
                        l += 2;
                    }
                }
                else{
                    conflict[x][y] = 0;
                }                
            }
        }
                               
        KCG kcg = new KCG(capacity,items,conflict);
                
        return kcg;
    }
    
}
