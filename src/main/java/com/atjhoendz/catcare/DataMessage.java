package com.atjhoendz.catcare;

import java.util.*;

public class DataMessage {
    private String ya[] = {"iya", "y", "betul", "ada", "ya", "punya"};
    private String tidak[] = {"t", "tidak", "g", "engga", "ga"};
    private String tidakSopan[] = {"bodo", "bodoh", "bacot", "bct"};
    private String sapa[] = {"hai", "hello", "test"};
    private String thanks[] = {"terima", "kasih", "thank", "thanks", "oke", "ok", "okay", "sip", "nuhun"};
    private String answer = "";

    Map<String, List<String>> jawaban = new HashMap<String, List<String>>();

    public DataMessage(){
        List<String> listYa = new ArrayList<String>();
        List<String> listTidak = new ArrayList<String>();
        List<String> listTsopan = new ArrayList<String>();
        List<String> listSapa = new ArrayList<String>();
        List<String> listThanks = new ArrayList<String>();

        listYa.addAll(Arrays.asList(ya));
        listTidak.addAll(Arrays.asList(tidak));
        listTsopan.addAll(Arrays.asList(tidakSopan));
        listSapa.addAll(Arrays.asList(sapa));
        listThanks.addAll(Arrays.asList(thanks));

        jawaban.put("ya", listYa);
        jawaban.put("tidak", listTidak);
        jawaban.put("tidakSopan", listTsopan);
        jawaban.put("sapa", listSapa);
        jawaban.put("thanks", listThanks);
    }

    public String cekJawaban(ArrayList<String> jawab){
        for(Map.Entry<String, List<String>> entry : jawaban.entrySet()){
            String key = entry.getKey();
            List<String> value = entry.getValue();
            Set<String> irisanValue = new HashSet<String>(value);
            irisanValue.retainAll(jawab);
            if(irisanValue.size() > 0){
                this.answer = key;
            }else{
                this.answer = "unknown";
            }
        }
        return this.answer;
    }

}
