package ua.com.juja;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serzh on 8/14/16.
 */
public class ForTests {
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup ("subgroup 2");
        Thread t = new Thread (tg, () -> {

        });
    }
}
