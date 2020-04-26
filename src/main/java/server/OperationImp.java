package server;

/*
 * Class for doing calculations with overriding methods of Operations interface
 */

public class OperationImp implements Operation{


    // Methods for doing calculations
    public String calculation(String msg){

        // Splitting given string for taking values and operation separately
        String[] arrOfStr = msg.split(" ");

        String answer;

        /*
         *Doing operations for checking operation_string
         *First we take values as string, then we convert them to Double and sent operation methods
         */

        switch (arrOfStr[1]) {
            case "+":
                answer = add(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[2]));
                break;
            case "-":
                answer = subt(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[2]));
                break;
            case "/":
                answer = div(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[2]));
                break;
            default:
                answer = mult(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[2]));
                break;
        }

        return answer;
    }


    /*
     * Operations which are add, subtract, multiply and divide values
     * They take values as Double and return answer as String
     * div() method return "ERROR" message if second value is zero
     */

    @Override
    public String add(double val1, double val2) {
        return String.valueOf(val1+val2);
    }

    @Override
    public String  subt(double val1, double val2) {

        return String.valueOf(val1-val2);
    }

    @Override
    public String mult(double val1, double val2) {
        return String.valueOf(val1*val2);
    }

    @Override
    public String div(double val1, double val2) {

        if(val2==0){
            return "ERROR";
        }else
            return String.valueOf(val1/val2);
    }
}

