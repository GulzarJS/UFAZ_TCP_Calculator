package calculator;

import client.ClientSide;

import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

/*
 * Class for creating some parts of calculator GUI which can do some actions
 */


public class Calculator implements ActionListener {

    // creating frame
    public JFrame frame;

    // creating text field for displaying calculations and its answer
    public JTextField text;

    // Creating ClientSide object for sending and getting message
    // We are using it for connecting socket and GUI
    private ClientSide clientSide = new ClientSide();

    // string for holding last taken answer (private because we use it just in this class)
    private static String lastAnswer;

    // strings for keeping operators and operands
    String S1, S2, S3;

    // constructor for creating object
    Calculator()  {
        S1=S2=S3="";
    }

    // ActionListeners for buttons

    public void actionPerformed(ActionEvent e) {

        // first and third string always hold values, second holds operation

        String takenstring = e.getActionCommand();

        /*Please, test first if scope with clicking following buttons on calculator
         * (6 + 3 =) => when you click this buttons, you will see answer 9
         * after seeing 9 , please click immediately another number
         * you will see that, you start new calculation without logic error
         */

        // If takenstring is number and dot (for a fractional number)

        if (takenstring.charAt(0) >= '0' && takenstring.charAt(0) <= '9' || takenstring.charAt(0) == '.') {
            // if first string is full, put taken string to third one
            if (!S1.equals("")) {
                if(!S2.equals("")) {
                    S3 = S3 + takenstring;
                }
                else {
                    // if first string is the same with last taken string, clear it and put new number
                    if (S1.equals(lastAnswer)) {
                        S1 = "" + takenstring;
                    }
                    // else, concatenate taken string with S1
                    else {
                        S1 = S1 + takenstring;
                    }
                }
                // if not put it to first one
            }else{
                S1= S1+takenstring;
            }
            // display values and operation(if there is)
            text.setText(S1 + S2 + S3);
        }

        /*
         *  If taken string is not value
         */

        // if taken string is C (clear)
        else if (takenstring.charAt(0) == 'C') {
            // clear all strings
            S1 = S2 = S3 = "";

            // set the value of text
            text.setText(S1 + S2 + S3);
        }

        /*
         * Now, operation part
         */

        // if taken string is =
        else if (takenstring.charAt(0) == '=') {

            // if one of value part is empty, give ERROR
            if(S1.equals("") || S3.equals("")  ) {
                text.setText("ERROR");
                S1=S2=S3="";
            }
            // if both of values are ready for sending server
            else {
                try {
                    /*display answer with help of getAnswer() method
                     * getAnswer method send given string to server and get answer of operation
                     */

                    /* CHECKING=>
                     * if sent answer is ERROR, just display error, modify last taken answer and clear all strings
                     */
                    if (clientSide.getAnswer(S1 + " " + S2 + " " + S3).equals("ERROR")) {
                        text.setText(clientSide.getAnswer(S1 + " " + S2 + " " + S3));
                        lastAnswer=clientSide.getAnswer(S1 + " " + S2 + " " + S3);
                        S1 = S2 = S3 = "";
                    }
                    //else, display values, operation and answer, modify last take string and set answer to first string
                    else {
                        text.setText(S1 + S2 + S3 + "=" + clientSide.getAnswer(S1 + " " + S2 + " " + S3));
                        lastAnswer=clientSide.getAnswer(S1 + " " + S2 + " " + S3);

                        /*Setting answer to first string (for keeping it in memory) for immediately doing some operation with it.
                         * Please, test it with clicking following buttons =>
                         * (6 + 3 = + 3) => when tou click 4-th button (=), you will see answer of 6+3=9
                         * then you will click 5-th and 6-th buttons (+ 3), you will see 9+3
                         * So, calculator keep every answer for doing immediate operations
                         */
                        S1 = clientSide.getAnswer(S1 + " " + S2 + " " + S3);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // clearing second and third string for new operation and number
                S2 = S3 = "";
            }
        }
        // if taken string is any operation
        else {

            // if second or third string is empty, put it to second one (second one always holds operation)
            // it means that, if you don't enter second value, you can change operation (example, chane + to - )
            if (S2.equals("") || S3.equals("")) {
                /*if there is no first value when you click operation buttons, don't save this operation string
                 * Please, test it with clicking following buttons on calculator =>
                 * Do some operations and clear it with clicking C button
                 * Then click ( + 6 - 3 = ) => you will see that, calculator doesn't consider first operation (+)
                 * because first string is empty and doing operation will bring logic ERROR ( first you will see +
                 * then when you click 6, you will see 6+ )
                 * */
                if (!S1.equals("")) {
                    S2 = takenstring;
                } else {
                    S2 = "";
                }
            }
            /*
             * if they are not empty, evaluate given calculation then get new one (first string can't be empty,
             * because we solved that problem. Calculator can't consider any operation if first value is empty
             */
            else {
                try {
                    /* CHECKING=>
                     * if sent answer is ERROR, just display error, modify last taken answer and clear all strings
                     */
                    if (clientSide.getAnswer(S1 + " " + S2 + " " + S3).equals("ERROR")) {
                        text.setText(clientSide.getAnswer(S1 + " " + S2 + " " + S3));
                        lastAnswer=clientSide.getAnswer(S1 + " " + S2 + " " + S3);
                        S1 = S2 = S3 = "";
                    } else {
                        // put answer of given operation to first string for continuing to calculate
                        S1 = clientSide.getAnswer(S1 + " " + S2 + " " + S3);
                        // modify last taken string
                        lastAnswer=clientSide.getAnswer(S1 + " " + S2 + " " + S3);
                        // set taken string to second string for doing new calculations
                        S2 = takenstring;

                        // clearing third string for holding second value for new calculations
                        S3 = "";
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }



            }

            // display calculation and its answer
            text.setText(S1 + S2 + S3);
        }

    }

}

