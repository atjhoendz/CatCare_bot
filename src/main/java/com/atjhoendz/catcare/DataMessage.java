package com.atjhoendz.catcare;

import java.util.*;

public class DataMessage {
    private String ya[] = {"iya", "y", "benar", "betul", "ada", "ya", "punya"};
    private String tidak[] = {"t", "tidak", "g", "engga", "ga"};
    private String tidakSopan[] = {"bodo", "bodoh", "bacot", "bct", "bangsat", "ccd", "bngst"};
    private String sapa[] = {"hai", "hello", "test", "halo"};
    private String thanks[] = {"terima", "kasih", "thank", "thanks", "oke", "ok", "okay", "sip", "nuhun", "makasih"};
    private String bingung[] = {"hah?", "hah", "ha", "hih", "apa?"};
    private String author[] = {"ngebikin?", "bikin?", "ngebuat?", "pembuat", "ngebikin", "bikinnya?"};
    private String myName[] = {"achun", "armando"};
    private String answer = "";

    Map<String, List<String>> jawaban = new HashMap<String, List<String>>();

    public DataMessage(){
        List<String> listYa = new ArrayList<String>(Arrays.asList(ya));
        List<String> listTidak = new ArrayList<String>(Arrays.asList(tidak));
        List<String> listTsopan = new ArrayList<String>(Arrays.asList(tidakSopan));
        List<String> listSapa = new ArrayList<String>(Arrays.asList(sapa));
        List<String> listThanks = new ArrayList<String>(Arrays.asList(thanks));
        List<String> listBingung = new ArrayList<String>(Arrays.asList(bingung));
        List<String> listAuth = new ArrayList<String>(Arrays.asList(author));
        List<String> listmyName = new ArrayList<String>(Arrays.asList(myName));

        jawaban.put("ya", listYa);
        jawaban.put("tidak", listTidak);
        jawaban.put("tidakSopan", listTsopan);
        jawaban.put("sapa", listSapa);
        jawaban.put("thanks", listThanks);
        jawaban.put("author", listAuth);
        jawaban.put("bingung", listBingung);
        jawaban.put("myname", listmyName);
    }

    public String cekJawaban(ArrayList<String> jawab){
        for(Map.Entry<String, List<String>> entry : jawaban.entrySet()){
            String key = entry.getKey();
            List<String> value = entry.getValue();
            Set<String> irisanValue = new HashSet<String>(value);
            irisanValue.retainAll(jawab);
            if(irisanValue.size() > 0){
                this.answer = key;
                break;
            }else{
                this.answer = "unknown";
            }
        }
        return this.answer;
    }

}
