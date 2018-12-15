package com.atjhoendz.catcare;

import java.util.*;

public class DataKeluhan {

    private String flu[]={"nafsu", "makan", "berkurang", "demam", "bersin", "batuk"};
    private String cacingan = "kotoran muntah cacing cacingnya tidak nafsu perut membuncit wajar badan kurus haus diare mencret makan banyak tapi badan tetap kurus";
    private String arrCacingan[] = cacingan.split(" ");
    private String koksidiosis[] = {"malas", "gerak", "bergerak", "mager", "anemia", "kekurangan", "kurang", "darah", "diare"};
    private String tripanosomiasis[] = {"malas", "gerak", "bergerak", "mager", "anemia", "kekurangan", "kurang", "darah", "anoreksia", "gamau", "makan", "epifora", "mata", "berair"};
    private String hepatozoonosis[] = {"malas", "gerak", "bergerak", "mager", "demam", "pucat"};
    private String babesiosis[] = {"kekurusan", "badan", "kurus", "kurusan", "malas", "gerak", "bergerak", "mager", "demam", "anoreksia", "gamau", "makan", "kelumpuhan", "pincang"};
    private String distemper[] = {"dehidrasi", "malas", "gerak", "bergerak", "mager", "diare", "demam", "anoreksia", "gamau", "makan", "menurun", "halitosis", "bau", "mulut", "muntah", "lesi anggota"};
    private String ankilostomiosis[] = {"anemia", "kekurangan", "kurang", "darah", "diare", "berak", "darah", "melena", "ikterus", "kulit", "kuning", "lesi", "anggota", "batuk", "radang", "kulit"};
    private String phthiriasis[] = {"kekurusan", "kurusan", "badan", "kurus", "anemia", "kekurangan", "kurang", "darah", "ikterus", "kulit", "kuning", "radang", "kulit"};
    private String pedikulosis[] = {"kutu", "kutuan", "radang", "kulit"};

    Map<String, List<String>> dataPenyakit = new HashMap<String, List<String>>();
    private String hasilPenyakit = "Sehat";

    public DataKeluhan(){
        List<String> kCacingan = new ArrayList<String>(Arrays.asList(arrCacingan));
        List<String> kFlu = new ArrayList<>(Arrays.asList(flu));
        List<String> kKoks = new ArrayList<>(Arrays.asList(koksidiosis));
        List<String> kTrip = new ArrayList<>(Arrays.asList(tripanosomiasis));
        List<String> kHepa = new ArrayList<>(Arrays.asList(hepatozoonosis));
        List<String> kBabe = new ArrayList<>(Arrays.asList(babesiosis));
        List<String> kDist = new ArrayList<>(Arrays.asList(distemper));
        List<String> kAnki = new ArrayList<>(Arrays.asList(ankilostomiosis));
        List<String> kPhth = new ArrayList<>(Arrays.asList(phthiriasis));
        List<String> kPedi = new ArrayList<>(Arrays.asList(pedikulosis));

        dataPenyakit.put("Flu", kFlu);
        dataPenyakit.put("Cacingan", kCacingan);
        dataPenyakit.put("Koksidiosis", kKoks);
        dataPenyakit.put("Tripanosomiasis", kTrip);
        dataPenyakit.put("Hepatozoonosis", kHepa);
        dataPenyakit.put("Babesiosis", kBabe);
        dataPenyakit.put("Distemper", kDist);
        dataPenyakit.put("Ankilostomiosis", kAnki);
        dataPenyakit.put("Phthiriasis", kPhth);
        dataPenyakit.put("Pedikulosis", kPedi);
    }

    public String cekKeluhan(ArrayList<String> keluhan){

        for (Map.Entry<String, List<String>> entry : dataPenyakit.entrySet()){
            String namaPenyakit = entry.getKey();
            List<String> listKeluhan = entry.getValue();
            Set<String> irisanKeluhan = new HashSet<String>(listKeluhan);
            irisanKeluhan.retainAll(keluhan);

            if(irisanKeluhan.size() >= 3){
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

    public List<String> getPenyakit(DataKeluhan data){
        List<String> namaPenyakit = new ArrayList<String>();
        for(Map.Entry<String, List<String>> entry : data.dataPenyakit.entrySet()){
            namaPenyakit.add(entry.getKey());
        }
        return namaPenyakit;
    }
}
