package com.atjhoendz.catcare;

import java.util.*;

public class DataMessage {
    private String ya[] = {"iya", "y", "betul", "ada", "ya", "punya"};
    private String tidak[] = {"t", "tidak", "g", "engga", "ga"};
    private String tidakSopan[] = {"bodo", "bodoh", "bacot", "bct"};
    private String sapa[] = {"hai", "hello", "test"};
    private String thanks[] = {"terima", "kasih", "thank", "thanks", "oke", "ok", "okay", "sip", "nuhun"};

    Map<String, List<String>> jawaban = new HashMap<String, List<String>>();
    List<String> listYa = new ArrayList<String>(Arrays.asList(ya));
    List<String> listTidak = new ArrayList<String>(Arrays.asList(tidak));
    List<String> listTsopan = new ArrayList<String>(Arrays.asList(tidakSopan));
    List<String> listSapa = new ArrayList<String>(Arrays.asList(sapa));
    List<String> listThanks = new ArrayList<String>(Arrays.asList(thanks));

    public DataMessage(){
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
                return key;
            }else{
                return "unknown";
            }
        }
        return "unknown";
    }

}
