/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.Model;

/**
 *
 * @author KeithW
 */
public class LifeCell {

    public enum LifeState {

        ALIVE, DEAD, UNKNOWN;
    }

    public enum LifeStateTransition {

        BORN, DIED, SURVIVED, DORMANT, UNKNOWN;
    }
    private LifeState stateCurrent, stateNext;
    private LifeStateTransition transitionLast;

    public LifeCell(LifeState stateCurrent) {
        this.stateCurrent = stateCurrent;
        this.stateNext = LifeState.UNKNOWN;
        this.transitionLast = LifeStateTransition.DORMANT;
    }

    public LifeState getStateCurrent() {
        return stateCurrent;
    }

    public void setStateCurrent(LifeState stateCurrent) {
        this.stateCurrent = stateCurrent;
    }

    public LifeState getStateNext() {
        return stateNext;
    }

    public void setStateNext(LifeState stateNext) {
        this.stateNext = stateNext;
    }

    public void setStateNext(int iNeighbours) {

        LifeState stateNew;
        LifeStateTransition transition;

        // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
        // Any live cell with two or three live neighbours lives on to the next generation.
        // Any live cell with more than three live neighbours dies, as if by overcrowding.
        if (this.stateCurrent == LifeState.ALIVE) {

            switch (iNeighbours) {
                case 0:
                case 1:
                    stateNew = LifeState.DEAD;
                    transition = LifeCell.LifeStateTransition.DIED;
                    break;
                case 2:
                case 3:
                    stateNew = LifeState.ALIVE;
                    transition = LifeCell.LifeStateTransition.SURVIVED;
                    break;
                default:
                    stateNew = LifeState.DEAD;
                    transition = LifeCell.LifeStateTransition.DIED;
                    break;

            }
        } 
        // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        else {

            switch (iNeighbours) {
                case 0:
                case 1:
                case 2:
                    stateNew = LifeState.DEAD;
                    transition = LifeCell.LifeStateTransition.DORMANT;
                    break;
                case 3:
                    stateNew = LifeState.ALIVE;
                    transition = LifeCell.LifeStateTransition.BORN;
                    break;
                default:
                    stateNew = LifeState.DEAD;
                    transition = LifeCell.LifeStateTransition.DORMANT;
                    break;

            }

        }

        this.stateNext = stateNew;
        this.transitionLast = transition;

    }

    public void changeState() {
        this.stateCurrent = this.stateNext;

    }

    public LifeCell.LifeStateTransition getLastStateTransition() {
        return this.transitionLast;
    }
}
