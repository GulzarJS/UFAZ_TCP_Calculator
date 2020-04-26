package calculator;
import calculator.Buttons;
import calculator.Calculator;

import javax.swing.*;
import java.awt.*;


/*
 * Class for creating calculator user interface
 *
 */

public class Playground  {


    public Playground()  {

        // initializing new Calculator object
        Calculator calc = new Calculator();
        // creating new frame
        calc.frame = new JFrame("Calculator");
        // creating new text field for displaying calculations and answers
        calc.text = new JTextField(20); // new textfield
        // set frame to not resizable. Nobody can resize ot
        calc.frame.setResizable(false);

        //Creating buttons

        Buttons b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
        Buttons bsum,bmult,bsub,bdiv,bdot,bequal,bclear;

        //Number buttons

        b0 = new Buttons("0", calc);
        b1 = new Buttons("1", calc);
        b2 = new Buttons("2", calc);
        b3 = new Buttons("3", calc);
        b4 = new Buttons("4", calc);
        b5 = new Buttons("5", calc);
        b6 = new Buttons("6", calc);
        b7 = new Buttons("7", calc);
        b8 = new Buttons("8", calc);
        b9 = new Buttons("9", calc);

        //Operation buttons

        bequal = new Buttons("=", calc);
        bsum = new Buttons("+", calc);
        bsub = new Buttons("-", calc);
        bdiv = new Buttons("/", calc);
        bmult = new Buttons("x", calc);
        bclear = new Buttons("C", calc);
        bdot = new Buttons(".", calc);

        //Creating panel

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        //Adding elements to panel

        panel1.add(calc.text);
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        panel2.add(bsum);
        panel2.add(b4);
        panel2.add(b5);
        panel2.add(b6);
        panel2.add(bsub);
        panel2.add(b7);
        panel2.add(b8);
        panel2.add(b9);
        panel2.add(bmult);
        panel2.add(b0);
        panel2.add(bdot);
        panel2.add(bclear);
        panel2.add(bdiv);
        panel2.add(bequal);


        // defining text field characteristics ( text font, color, background color, size)
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        calc.text.setFont(font1);
        calc.text.setPreferredSize(new Dimension(320,75));
        calc.text.setBackground(Color.GRAY);

        //Background of panel
        panel1.setBackground(Color.DARK_GRAY);
        panel2.setBackground(Color.DARK_GRAY);

        // adding panels to frame
        calc.frame.add(panel1,BorderLayout.NORTH);
        calc.frame.add(panel2);
        calc.frame.setLocation(100,100);
        calc.frame.setSize(400,600);
        calc.frame.setVisible(true);

    }
}

