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

    private String detailCacingan = "Cacing merupakan endoparasit (parasit yang hidup dalam tubuh) yang sering menyerang kucing. Sebagian besar kucing yang terinfeksi tidak memperlihatkan gejala. Kucing banyak makan tetapi tetap kurus atau perut buncit merupakan salah satu gejala cacingan. Gejala lainnya bisa berupa adanya cacing berbentuk seperti lidi atau pita tipis berwarna putih pada kotoran atau muntah kucing.Cacing dapat menyebabkan gangguan pencernaan, anemia, kekurangan gizi atau komplikasi lainnya. Anak kucing yang baru lahir dapat tertular cacing dari induknya. Anak kucing yang terserang cacingan dapat mengalami diare berkepanjangan, terhambat pertumbuhannya atau mati karena kekurangan cairan (dehidrasi) dan kekurangan gizi.";
    private String detailFlu = "Flu kucing adalah penyakit pada kucing yang biasanya disebabkan oleh infeksi satu atau kombinasi beberapa virus (virus herpes dan virus calici) dan bakteri. Penyakit ini jarang menyebabkan kematian pada kucing dewasa tetapi dapat berakibat fatal bila menyerang anak kucing. Meskipun pada kucing dewasa jarang berakibat fatal, gejala-gejala penyakit seperti pilek dan bersin-bersin dapat berlangsung cukup lama. Oleh karena itu pencegahan dengan vaksinasi rutin merupakan tindakan terbaik.";
    private String detailKoksidiosis = "Koksidiosis adalah penyakit infeksi berat oleh protozoa Coccidia. Biasanya penyakit ini menyerang domba, kambing, sapi, kelinci, dan ayam. Bila yang diserang anjing, kucing, dan babi, penyakit akan lebih ringan. Infeksi terjadi bila bentuk kista tertelan dan masuk ke dalam usus. Di sini protozoa tersebut me\u00ADnyebabkan peradangan, perdarahan usus, dan diare. Sebagai akibatnya, dapat terjadi dehidrasi; dan pada kasus penyakit yang berat dapat terjadi anemia.";
    private String detailTripanosomiasis = "";
    private String detailHepatozoonosis = "";
    private String detailBabesiosis = "";
    private String detailDistemper = "";
    private String detailAnkilostomiosis = "";
    private String detailPhthiriasis = "";
    private String detailPedikulosis = "";

    Map<String, List<String>> dataPenyakit = new HashMap<String, List<String>>();
    Map<String, String> detailPenyakit = new HashMap<String, String>();
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

        detailPenyakit.put("Flu", detailFlu);
        detailPenyakit.put("Cacingan", detailCacingan);
        detailPenyakit.put("Koksidiosis", detailKoksidiosis);
        detailPenyakit.put("Tripanosomiasis", detailTripanosomiasis);
        detailPenyakit.put("Hepatozoonosis", detailHepatozoonosis);
        detailPenyakit.put("Babesiosis", detailBabesiosis);
        detailPenyakit.put("Distemper", detailDistemper);
        detailPenyakit.put("Ankilostomiosis", detailAnkilostomiosis);
        detailPenyakit.put("Phthiriasis", detailPhthiriasis);
        detailPenyakit.put("Pedikulosis", detailPedikulosis);
    }

    public String cekKeluhan(ArrayList<String> keluhan){

        for (Map.Entry<String, List<String>> entry : dataPenyakit.entrySet()){
            String namaPenyakit = entry.getKey();
            List<String> listKeluhan = entry.getValue();
            Set<String> irisanKeluhan = new HashSet<String>(listKeluhan);
            irisanKeluhan.retainAll(keluhan);

            if(irisanKeluhan.size() >= 3){
                this.hasilPenyakit = namaPenyakit;
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

    public String detailPenyakit(DataKeluhan data, String namaPenyakit){
        String detPenyakit = "Belum ada data";
        for (Map.Entry<String, String> entry : data.detailPenyakit.entrySet()){
            String keyNamaPenyakit = entry.getKey().toLowerCase();
            String detail = entry.getValue();
            if(keyNamaPenyakit.equals(namaPenyakit)){
                detPenyakit = detail;
                break;
            }else{
                detPenyakit = "unknown";
            }
        }
        return detPenyakit;
    }
}