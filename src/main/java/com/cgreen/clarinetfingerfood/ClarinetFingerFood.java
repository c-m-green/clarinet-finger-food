package com.cgreen.clarinetfingerfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ClarinetFingerFood 
{
    public static final String[] PITCH_CLASSES = {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};
    public static void main( String[] args )
    {
        System.out.println("Scanning input file");
        InputStream in = ClarinetFingerFood.class.getClassLoader().getResourceAsStream("clarnumfingers.txt");
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(in));
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for (int i = 0; i < 10; i++) {
            map.put(i, new ArrayList<String>());
        }
        String pitchInfo;
        try {
            while ((pitchInfo = br.readLine()) != null) {
                String[] pitchInfoArr = pitchInfo.split(";");
                int pitchClass = Integer.parseInt(pitchInfoArr[0]);
                String register = pitchInfoArr[1];
                String note = PITCH_CLASSES[pitchClass] + register;
                String[] numFingers = pitchInfoArr[2].split(",");
                for (String str : numFingers) {
                    map.get(Integer.parseInt(str)).add(note);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter a sequence of numbers separated by commas: ");
        String numberString = s.next();
        String[] numbers = numberString.split(",");
        for (String number : numbers) {
            int currentNumber = Integer.parseInt(number);
            System.out.println("Current number: " + number);
            for (String pitch : map.get(currentNumber)) {
                System.out.print(pitch + ", ");
            }
            System.out.println();
        }
        s.close();
    }
}
