package Controller;

import Model.Engine;

import java.awt.event.KeyAdapter;


import java.awt.event.KeyEvent;


public class AdminController extends KeyAdapter {
    Engine engine;

    public AdminController(Engine engine) {
        this.engine = engine;
    }

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
