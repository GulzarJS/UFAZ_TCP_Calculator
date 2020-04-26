package calculator;

import javax.swing.*;
import java.awt.*;

/*
 * This class is for creating button with its characteristics
 * */

public class Buttons extends JButton{



    public JButton button;

    public Buttons(String name, Calculator calc){
        // First we should call superclass constructor and create button with its name
        super(name);

        // Then, we give it some characteristics ( dimension, background color)
        this.setPreferredSize(new Dimension(80,80));
        this.setBackground(Color.GRAY);

        // And we add actionListener for using calculator
        this.addActionListener(calc);
    }
}
