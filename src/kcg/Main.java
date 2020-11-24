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
    static List<Integer> finalSol = new ArrayList();    
    Timer timer;
    
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

            KCG kcg = KNPCFile.format(text);   
            
            //Solution finalSolution = Solver.branchAndBound(kcg);            
            //Solution finalSolution = Solver.greedy(kcg);
            Solution finalSolution = Solver.relaxedGreedy(kcg);

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
