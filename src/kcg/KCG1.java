/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carla
 */
public class KCG1 {
    
    static int bound = Integer.MIN_VALUE;
    static int solutionBound = 0;
    static int solutionWeight = 0;
    static List<Integer> finalSol = new ArrayList();       
    
    public static List<Integer> search(List<Integer> solution, List<Item> items, int index, int[][] A){
        
        boolean conflict = true;
        
        if(solutionBound > bound){
            if((index+1) < items.size()){
                if(solutionWeight <= 15 && solutionWeight + items.get(index+1).getWeight() > 15){
                    bound = solutionBound;
                    finalSol.clear();
                    finalSol.addAll(solution);
                }
                else{
                    
                    while(conflict){
                        index++;
                        
                        for(Integer i : solution){
                            if(A[index][i] != 1 && A[i][index] != 1){
                               conflict = false;
                               break;
                            }
                        }   
                    }
                                        
                    if(!solution.contains(index)){ 
                        solutionBound += items.get(index).getProfit();
                        solutionWeight += items.get(index).getWeight();
                        solution.add(index);
                        search(solution,items,index,A); 
                    } 
                }
            }                               
        }      
        
        return finalSol;
    }
    
    /**
    * @param args the command line arguments
    */
    
    public static void main(String[] args) {
        // TODO code application logic here
                
        Item item1 = new Item(10,3);
        Item item2 = new Item(8,4);
        Item item3 = new Item(4,4);
        Item item4 = new Item(6,5);
        Item item5 = new Item(8,7);
        Item item6 = new Item(6,9);
        Item item7 = new Item(9,9);
        Item item8 = new Item(6,10);
        
        List<Item> itens = new ArrayList();   
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
        itens.add(item5);
        itens.add(item6);
        itens.add(item7);
        itens.add(item8);

        itens.forEach((item)->System.out.println(item.toString()));
        
        int[][] A = new int[12][12];
        
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j ++){
                A[i][j] = 0;
            }
        }
        
        A[1][4] = 1;
        A[2][4] = 1;
        A[2][5] = 1;
        A[2][7] = 1;
        A[4][7] = 1;
        A[4][8] = 1;
        A[5][6] = 1;
        A[5][7] = 1;
        A[5][8] = 1;
        A[6][7] = 1;
        A[6][8] = 1;
        A[7][8] = 1;
        
        List<Integer> solution = new ArrayList();
        boolean conflict = false;
        
        for(int i = 0; i < itens.size(); i++){
            for(Integer index : solution){
                if(A[index][i] == 1 && A[i][index] == 1){
                    conflict = true;
                    break;
                }
            }
            
            if(conflict) continue;
            
            if(!solution.contains(i)){   
                                
                solution.add(i);
                solutionBound += itens.get(i).getProfit();
                solutionWeight += itens.get(i).getWeight();
                
                search(solution, itens, i, A);  
                
                conflict = false;
                System.out.println(solution.toString());
                                
                if(solution.size() > 0){
                    solutionBound -= itens.get(solution.get(solution.size()-1)).getProfit();
                    solutionWeight -= itens.get(solution.get(solution.size()-1)).getWeight();
                    solution.remove(solution.size()-1);                      
                }
                System.out.println(solution.toString());
            }            
        }
                       
        System.out.println(finalSol.toString());  
        System.out.println(bound);
    }    
}
