package com.isidoreofseville.swine;

import java.util.ArrayList;

/**
 * Created by Dave on 3/22/2018.
 */

public class CATEGORIES {

    public static ArrayList<Integer> categoriesArray;

    public static final int BREEDS_AND_BREEDING = 0;
    public static final int SWINE_PRODUCTIVITY_PHYSIOLOGY = 1;
    public static final int PREGNANCY_PERIOD = 2;
    public static final int LACTATION_PERIOD = 3;
    public static final int FEEDING_MANAGEMENT = 4;
    public static final int HEALTH_AND_VACCINATION_PROGRAM_FOR_PREGNANT_SOW = 5;
    public static final int BABY_PIG_MANAGEMENT = 6;
    public static final int DISEASE_PREVENTION = 7;


    public static void initialize(){
        categoriesArray = new ArrayList<>();
        categoriesArray.add(BREEDS_AND_BREEDING);
        categoriesArray.add(SWINE_PRODUCTIVITY_PHYSIOLOGY);
        categoriesArray.add(PREGNANCY_PERIOD);
        categoriesArray.add(LACTATION_PERIOD);
        categoriesArray.add(FEEDING_MANAGEMENT);
        categoriesArray.add(HEALTH_AND_VACCINATION_PROGRAM_FOR_PREGNANT_SOW);
        categoriesArray.add(BABY_PIG_MANAGEMENT);
        categoriesArray.add(DISEASE_PREVENTION);
    }
}
