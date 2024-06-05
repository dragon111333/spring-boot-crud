package com.testsdemo.testcrud.util;

 enum Level {
    LOW,
    MEDIUM,
    HIGH,
    SUPER_HIGH,
    TEST_VALUE("HELLO", "world");
    private String firstWord;
    private String secondWord;

    Level() {
        this.firstWord = "";
        this.secondWord = "";
    }

    Level(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getFullWord(){
        if(this.firstWord.equals("") || this.secondWord.equals("")) return "empty!";
        else return "HAPPY " +firstWord.toUpperCase() + " " +secondWord.toUpperCase();
    }
}

public class EnumTest {
    public static void getAllLevel(){
        Level [] levelList = Level.values();
        System.out.println("-----------[ALL LEVEL]------------");
        for(int index = 0 ;index < levelList.length ; index++)
            System.out.println((index+1)+ ".) "+levelList[index]);
        System.out.println("-----------[ALL LEVEL](END)------------");

    }

    public static void getLevelWithParam(){
        Level testValue = Level.TEST_VALUE;
        Level low = Level.LOW;

        System.out.println("-----------------------------------");
        System.out.println("Have param : "+testValue.getFullWord()+"\n Not have param "+low);
        System.out.println("-----------------------------------");

    }

    public static void init(){

        EnumTest.getAllLevel();
        EnumTest.getLevelWithParam();

        Level level = Level.MEDIUM;
        System.out.println("CURRENT LEVEL -->"+level);


        switch(level) {
            case LOW:
                System.out.println("Low level");
                break;
            case MEDIUM:
                System.out.println("Medium level");
                break;
            case HIGH:
                System.out.println("High level");
                break;
            default:
                System.out.printf("SUPER HIGH");
                break;
        }
    }
}