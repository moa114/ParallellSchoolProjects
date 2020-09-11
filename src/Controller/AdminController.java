package Controller;

import Model.Admin;

import java.awt.event.KeyAdapter;


import java.awt.event.KeyEvent;


public class AdminController extends KeyAdapter {
    Admin admin;

    public AdminController(Admin admin) {
        this.admin = admin;
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
