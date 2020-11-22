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

/**
 *
 * @author Carla
 */
public class KNPCFile {
    
    public static void reader(String path) throws IOException{
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String line = "";
            
            while(true){
                if(line != null){
                    System.out.println(line);
                }
                else break;
                
                line = buffRead.readLine();
            }
        }
    }
    
    public static void writer(String path, String line) throws IOException{
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));         
        
        buffWrite.append(line + '\n');
        buffWrite.close();
    }
    
}
