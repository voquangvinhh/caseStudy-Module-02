package com.example.casetest.global;

import com.example.casetest.entity.Pet;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    //tao bien toan cuc
    public static List<Pet> cart;

    static {
        cart = new ArrayList<>();
    }
}
