package com.atjhoendz.catcare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Data {
    public String listKeluhan[] = {"nafsu", "berkurang", "bersin", "demam", "batuk", "mata", "berair"};
    public String flu[]={"nafsu", "makan", "berkurang", "demam", "bersin", "batuk"};
    Set<String> dataKeluhan = new HashSet<String>();
    Set<String> keluhanUser = new HashSet<String>();

    public Data(){
        dataKeluhan.addAll(Arrays.asList(flu));
    }

    public String cekKeluhan(ArrayList<String> keluhan){
        keluhanUser.addAll(keluhan);

        Set<String> unionList = new HashSet<String>(dataKeluhan);
        unionList.retainAll(keluhanUser);

        if(unionList.isEmpty()){
            return "Sehat";
        }else{
            return "Flu";
        }
    }
}
