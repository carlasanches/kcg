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
public class Solver {
        
    public static Solution greedy(KCG kcg){
        
        Solution partialSolution = new Solution(kcg.getCapacity());
        Solution finalSolution = new Solution(kcg.getCapacity()); 
        finalSolution.setProfit(Integer.MIN_VALUE);
        
        partialSolution.getItems().add(kcg.getItems().get(0));
        partialSolution.setProfit(partialSolution.getProfit() + kcg.getItems().get(0).getProfit());
        partialSolution.setWeight(partialSolution.getWeight() + kcg.getItems().get(0).getWeight());
        
        upperBound(partialSolution, finalSolution, kcg.getCapacity(), kcg.getItems(), 0, kcg.getConflict());
        
        return finalSolution;
    }
    
    public static Solution upperBound(Solution partialSolution, Solution finalSolution, int capacity, List<Item> items, int index, int[][] A){
        
        boolean conflict = true;
        
        if(partialSolution.getProfit() > finalSolution.getProfit()){
            if((index+1) < items.size()){
                if(partialSolution.getWeight() <= capacity && partialSolution.getWeight() + items.get(index+1).getWeight() > capacity){
                    finalSolution.setProfit(partialSolution.getProfit());
                    finalSolution.setWeight(finalSolution.getWeight());
                    finalSolution.getItems().clear();
                    finalSolution.getItems().addAll(partialSolution.getItems());
                }
                else{
                    
                    while(conflict){
                        index++;
                        
                        //verifica se há conflito com o novo item a ser inserido
                        for(Item item : partialSolution.getItems()){
                            if(A[index][partialSolution.getItems().indexOf(item)] != 1 && A[partialSolution.getItems().indexOf(item)][index] != 1){
                               conflict = false;
                               break;
                            }
                        }   
                    }
                                        
                    if(!partialSolution.getItems().contains(items.get(index))){ 
                        partialSolution.setProfit(partialSolution.getProfit() + items.get(index).getProfit());
                        partialSolution.setWeight(partialSolution.getWeight() + items.get(index).getWeight());
                        partialSolution.getItems().add(items.get(index));
                        upperBound(partialSolution,finalSolution,capacity,items,index,A); 
                    } 
                }
            }                               
        }      
        
        return finalSolution;
    }
    
    public static Solution branchAndBound(KCG kcg){ 
        
        Solution partialSolution = new Solution(kcg.getCapacity());
        Solution finalSolution = new Solution(kcg.getCapacity()); 
        finalSolution.setProfit(Integer.MIN_VALUE);
        
        List<Item> solutionItems = partialSolution.getItems();
        List<Item> kcgItems = kcg.getItems();
        int capacity = kcg.getCapacity();
        int index;
        
        boolean conflict = false;
        
        //verifica se há conflito
        for(Item kcgItem : kcgItems){
            for(Item solItem : solutionItems){
                if(kcg.getConflict()[solutionItems.indexOf(solItem)][kcgItems.indexOf(kcgItem)] == 1 && kcg.getConflict()[kcgItems.indexOf(kcgItem)][solutionItems.indexOf(solItem)] == 1){
                    conflict = true;
                    break;
                }
            }

            if(conflict) continue;

            
            if(!solutionItems.contains(kcgItem)){   
                                
                //adiciona item que não está na solução
                partialSolution.getItems().add(kcgItem);
                partialSolution.setProfit(partialSolution.getProfit() + kcgItem.getProfit());
                partialSolution.setWeight(partialSolution.getWeight() + kcgItem.getWeight());

                index = kcgItems.indexOf(kcgItem);
                upperBound(partialSolution, finalSolution, capacity, kcgItems, index , kcg.getConflict());  

                conflict = false;

                if(solutionItems.size() > 0){
                    Item lastItem = solutionItems.get(solutionItems.size()-1);
                    partialSolution.setProfit(partialSolution.getProfit() - lastItem.getProfit());
                    partialSolution.setWeight(partialSolution.getWeight() - lastItem.getWeight());
                    partialSolution.getItems().remove(lastItem);                      
                }
            }            
        }
        return finalSolution;
    }

    public static Solution relaxedGreedy(KCG kcg){
        
        Solution partialSolution = new Solution(kcg.getCapacity());
        Solution finalSolution = new Solution(kcg.getCapacity()); 
        finalSolution.setProfit(Integer.MIN_VALUE);
        
        partialSolution.getItems().add(kcg.getItems().get(0));
        partialSolution.setProfit(partialSolution.getProfit() + kcg.getItems().get(0).getProfit());
        partialSolution.setWeight(partialSolution.getWeight() + kcg.getItems().get(0).getWeight());
        
        relaxedUpperBound(partialSolution, finalSolution, kcg.getCapacity(), kcg.getItems(), 0, kcg.getConflict());
        
        return finalSolution;
    }
    
    public static Solution relaxedUpperBound(Solution partialSolution, Solution finalSolution, int capacity, List<Item> items, int index, int[][] A){
                
        boolean conflict = true;
        
        if(partialSolution.getProfit() > finalSolution.getProfit()){
            if((index+1) < items.size()){
                if(partialSolution.getWeight() > capacity){
                    
                    double upper = ((capacity - (partialSolution.getWeight()-items.get(index).getWeight()))/items.get(index).getWeight()) * items.get(index).getProfit();
                    
                    finalSolution.setProfit(partialSolution.getProfit() - items.get(index).getProfit() + (int)upper);
                    finalSolution.setWeight(finalSolution.getWeight());
                    finalSolution.getItems().clear();
                    finalSolution.getItems().addAll(partialSolution.getItems());
                }
                else{    
                    
                    while(conflict){
                        index++;
                        
                        //verifica se há conflito com o novo item a ser inserido
                        for(Item item : partialSolution.getItems()){
                            if(A[index][partialSolution.getItems().indexOf(item)] != 1 && A[partialSolution.getItems().indexOf(item)][index] != 1){
                               conflict = false;
                               break;
                            }
                        }   
                    }
                                        
                    if(!partialSolution.getItems().contains(items.get(index))){ 
                        partialSolution.setProfit(partialSolution.getProfit() + items.get(index).getProfit());
                        partialSolution.setWeight(partialSolution.getWeight() + items.get(index).getWeight());
                        partialSolution.getItems().add(items.get(index));
                        relaxedUpperBound(partialSolution,finalSolution,capacity,items,index,A); 
                    } 
                }
            }                               
        }      
        
        return finalSolution;
    }    
}
