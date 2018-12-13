package com.atjhoendz.catcare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Data {
    public String listKeluhan[] = {"nafsu", "berkurang", "bersin", "demam", "batuk", "mata", "berair"};
    public String flu[]={"nafsu", "makan", "berkurang", "demam", "bersin", "batuk"};
    Set<String> dataKeluhan;
    Set<String> keluhanUser;

    public Data(){
        dataKeluhan = new HashSet<String>();
        dataKeluhan.addAll(Arrays.asList(flu));
    }

    public String cekKeluhan(ArrayList<String> keluhan){
        keluhanUser = new HashSet<String>();
        keluhanUser.addAll(keluhan);

        Set<String> unionList = new HashSet<String>(dataKeluhan);
        unionList.retainAll(keluhanUser);

        if(unionList.size() > 3){
            return "Flu";
        }else{
            return "Sehat";
        }
    }
}
