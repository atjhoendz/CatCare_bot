package com.atjhoendz.catcare;

import java.util.*;

public class Data {
    public String listKeluhan[] = {"nafsu", "berkurang", "bersin", "demam", "batuk", "mata", "berair"};
    public String flu[]={"nafsu", "makan", "berkurang", "demam", "bersin", "batuk"};
    Map<String, List<String>> dataPenyakit = new HashMap<String, List<String>>();
//    Set<String> dataKeluhan = new HashSet<String>();
//    Set<String> keluhanUser = new HashSet<String>();
    private String hasilPenyakit = "Sehat";

    List<String> penyakit = new ArrayList<String>();

    public Data(){
        penyakit.addAll(Arrays.asList(flu));
        dataPenyakit.put("Flu", penyakit);
    }

    public String cekKeluhan(ArrayList<String> keluhan){
//        keluhanUser.addAll(keluhan);

        for (Map.Entry<String, List<String>> entry : dataPenyakit.entrySet()){
            String namaPenyakit = entry.getKey();
            List<String> listKeluhan = entry.getValue();
            Set<String> irisanKeluhan = new HashSet<String>(listKeluhan);
            irisanKeluhan.retainAll(keluhan);

            if(irisanKeluhan.size() > 3){
                this.hasilPenyakit = namaPenyakit;
                break;
            }
        }


//        Set<String> unionList = new HashSet<String>(dataKeluhan);
//        unionList.retainAll(keluhanUser);

//        if(.size() > 3){
//            return "Flu";
//        }else{
//            return "Sehat";
//        }
        return this.hasilPenyakit;
    }
}
