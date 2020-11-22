/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
        
        String line = "";
        List<String> text = new ArrayList();
        
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {       
            
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
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));         
        
        buffWrite.append(line + '\n');
        buffWrite.close();
    }
    
    public static KCG format(List<String> text){
        
        List<Item> items = new ArrayList();
        int capacity = Integer.parseInt(text.get(0));
        int numItems = Integer.parseInt(text.get(1));    
        
        int index = (2 * (numItems-1)) + 2;
        
        int numConflict = Integer.parseInt(text.get(index));
                       
        KCG kcg = new KCG(capacity,items,numConflict);
        
        return kcg;
    }
    
}
