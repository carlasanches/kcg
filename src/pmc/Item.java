/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc;

/**
 *
 * @author Carla
 */
public class Item {
    
    private int lucro;
    private int peso;

    public Item(int lucro, int peso) {
        this.lucro = lucro;
        this.peso = peso;
    }

    /**
     * @return the lucro
     */
    public int getLucro() {
        return lucro;
    }

    /**
     * @param lucro the lucro to set
     */
    public void setLucro(int lucro) {
        this.lucro = lucro;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }    

    @Override
    public String toString() {
        return "Item{" + "lucro=" + lucro + ", peso=" + peso + '}';
    }
}
