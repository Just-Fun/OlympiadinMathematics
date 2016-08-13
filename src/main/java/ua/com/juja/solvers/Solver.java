package ua.com.juja.solvers;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;


/**
 * Created by Serzh on 8/12/16.
 */
public class Solver {
    public static void main(String[] args) {
        System.out.println(calculate("6-(2+(1*3)*2)"));
        System.out.println(calculate("( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )"));
    }

    public static String calculate(String task) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object eval = null;
        try {
            eval = engine.eval(task);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        double d = Double.valueOf(eval.toString());
//        double roundOff = Math.round(d * 100.0) / 100.0;
        double roundOff = Math.ceil(d * 100.0) / 100.0;
        return String.valueOf(roundOff);
    }
}
