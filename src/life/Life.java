/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package life;

import life.View.LifeMainFrame;

import life.Model.LifeCell;

/**
 *
 * @author KeithW
 */
public class Life {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LifeCell cell = new LifeCell(LifeCell.LifeState.ALIVE);
        
        for(int i = 0; i<9;i++) {
            cell.setStateNext(i);
            
        }
        
        LifeMainFrame frame = new LifeMainFrame();
        frame.setVisible(true);
    }
    
}
