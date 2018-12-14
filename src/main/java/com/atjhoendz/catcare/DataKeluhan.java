package com.atjhoendz.catcare;

import java.util.*;

public class DataKeluhan {

    private String flu[]={"nafsu", "makan", "berkurang", "demam", "bersin", "batuk"};
    private String cacingan = "kotoran kucing ada cacingnya muntah ada cacingnya tidak nafsu makan perut membuncit tidak wajar badan kurus selalu merasa haus kucing diare atau mencret makan banyak tapi badan tetap kurus";
    private String arrCacingan[] = cacingan.split(" ");

    Map<String, List<String>> dataPenyakit = new HashMap<String, List<String>>();
    private String hasilPenyakit = "Sehat";

    public DataKeluhan(){
        List<String> kCacingan = new ArrayList<String>(Arrays.asList(arrCacingan));
        List<String> kFlu = new ArrayList<>(Arrays.asList(flu));
        dataPenyakit.put("Flu", kFlu);
        dataPenyakit.put("Cacingan", kCacingan);
    }

    public String cekKeluhan(ArrayList<String> keluhan){

        for (Map.Entry<String, List<String>> entry : dataPenyakit.entrySet()){
            String namaPenyakit = entry.getKey();
            List<String> listKeluhan = entry.getValue();
            Set<String> irisanKeluhan = new HashSet<String>(listKeluhan);
            irisanKeluhan.retainAll(keluhan);

            if(irisanKeluhan.size() > 3){
                this.hasilPenyakit = namaPenyakit;
                break;
            }else if(irisanKeluhan.size() == 0){
                this.hasilPenyakit = "Sehat";
            }else{
                this.hasilPenyakit = "unknown";
            }
        }

        return this.hasilPenyakit;
    }
}
