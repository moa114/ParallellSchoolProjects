package Controller;

import Model.Engine;

import java.awt.event.KeyAdapter;


import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;


public class Controller extends KeyAdapter {
    Engine engine;
    Map<Integer, Boolean> pressed = new HashMap<>();


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    //Dessa ska fyllas i med något vettigt, antagligen ett switch/case
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }
}
