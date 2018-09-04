import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Toolkit;


public class Display extends JFrame implements Gui, ActionListener{
    Controller gController;
    JLabel label;
    JButton buttonIC;
    JButton buttonGS;
    //Connect gui to controller
    //(This method will be called before ANY other methods)
    public void connect(Controller controller){
        gController = controller;
    }

    //Initialise the gui
    public void init(){

        this.setSize(1000,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Reaction Machine");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
        buttonIC = new JButton("Coin Inserted");
        buttonIC.addActionListener(this);
		panel.add(buttonIC);
		
		buttonGS = new JButton("Go/Stop");
        buttonGS.addActionListener(this);
		panel.add(buttonGS);

        label = new JLabel("Insert Coin");
        panel.add(label);
		
		
		this.add(panel);
		this.setVisible(true);
    }

    //Change the displayed text
    public void setDisplay(String s){
        label.setText(s);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand() == buttonIC.getText()){
            gController.coinInserted();
        } else if(e.getActionCommand() == buttonGS.getText()){
            gController.goStopPressed();
        }
    }
}