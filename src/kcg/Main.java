/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carla
 */
public class Main {
    
    static int bound = Integer.MIN_VALUE;
<<<<<<< HEAD
    static List<Integer> finalSol = new ArrayList();    
    Timer timer;
=======
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
>>>>>>> parent of 8de4d34 (nova classe Solver)
    
    /**
    * @param args the command line arguments
    */
    
    public Main(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }
    
    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            System.exit(0);   // Stops the AWT thread
                              // (and everything else)
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //new Main(763);
                
        if(args.length >= 2){
            String inPath = args[0];
            String outPath = args[1];

            List<String> text = new ArrayList();

            try {
                text.addAll(KNPCFile.reader(inPath));
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

<<<<<<< HEAD
            KCG kcg = KNPCFile.format(text);   
            
            //Solution finalSolution = Solver.branchAndBound(kcg);            
            //Solution finalSolution = Solver.greedy(kcg);
            Solution finalSolution = Solver.relaxedGreedy(kcg);
=======
            KCG kcg = KNPCFile.format(text);    

            List<Integer> solution = new ArrayList();
            boolean conflict = false;

            for(int i = 0; i < kcg.getItems().size(); i++){
                for(Integer index : solution){
                    if(kcg.getConflict()[index][i] == 1 && kcg.getConflict()[i][index] == 1){
                        conflict = true;
                        break;
                    }
                }

                if(conflict) continue;

                if(!solution.contains(i)){   

                    solution.add(i);
                    solutionBound += kcg.getItems().get(i).getProfit();
                    solutionWeight += kcg.getItems().get(i).getWeight();

                    search(solution, kcg.getItems(), i, kcg.getConflict());  

                    conflict = false;

                    if(solution.size() > 0){
                        solutionBound -= kcg.getItems().get(solution.get(solution.size()-1)).getProfit();
                        solutionWeight -= kcg.getItems().get(solution.get(solution.size()-1)).getWeight();
                        solution.remove(solution.size()-1);                      
                    }
                }            
            }
>>>>>>> parent of 8de4d34 (nova classe Solver)

            String totalItems = "";

            for(Item i : finalSolution.getItems()){
                totalItems += " " + (finalSolution.getItems().indexOf(i)+1);
            }
            
            //arquivo contendo lucro, total de itens e listagem de itens
            String write = finalSolution.getProfit() + " " + finalSolution.getItems().size() + totalItems + " ";

            try {
                KNPCFile.writer(outPath, write);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
