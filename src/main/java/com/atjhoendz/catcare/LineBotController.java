package com.atjhoendz.catcare;

import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value="/linebot")
public class LineBotController {

    ArrayList<String> keluhanUser = new ArrayList<String>();
    DataKeluhan data = new DataKeluhan();
    DataMessage jawabanMasuk = new DataMessage();
    private String state = "";

    @Autowired
    @Qualifier("lineMessagingClient")
    private LineMessagingClient lineMessagingClient;

    @Autowired
    @Qualifier("lineSignatureValidator")
    private LineSignatureValidator lineSignatureValidator;

    @Autowired
    @Qualifier("com.linecorp.channel_secret")
    String lChannelSecret;

    @Autowired
    @Qualifier("com.linecorp.channel_access_token")
    String lChannelAccessToken;

    @RequestMapping(value="/callback", method = RequestMethod.POST)
    public ResponseEntity<String> callback(
            @RequestHeader("X-Line-Signature") String aXLineSignature,
            @RequestBody String eventsPayload)
    {

            final String text=String.format("The Signature is: %s",
                    (aXLineSignature!=null && aXLineSignature.length() > 0) ? aXLineSignature : "N/A");
            System.out.println(text);
            final boolean valid=new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(eventsPayload.getBytes(), aXLineSignature);
            System.out.println("The signature is: " + (valid ? "valid" : "tidak valid"));
            if(eventsPayload!=null && eventsPayload.length() > 0)
            {
                System.out.println("Payload: " + eventsPayload);
            }

            Gson gson = new Gson();
            Payload payload = gson.fromJson(eventsPayload, Payload.class);

            String msgText = "";
            String idTarget = "";
            String eventType = payload.events[0].type;


            if(eventType.equals("follow")){
                replyToUser(payload.events[0].replyToken, "Hello Cat Lovers! Ceritakan keluhan yang dialami kucing mu disini, CatCare akan memberikan solusinya.\n\nApakah kucing anda memiliki keluhan ?");
            }else if(eventType.equals("message")){
                ArrayList<String> listJawaban = new ArrayList<String>();

                msgText = payload.events[0].message.text;
                msgText = msgText.toLowerCase();
                String[] arrMsg = msgText.split(" ");

                listJawaban.addAll(Arrays.asList(arrMsg));
                String ans = jawabanMasuk.cekJawaban(listJawaban);


                if(ans.equals("ya")){
                    replyToUser(payload.events[0].replyToken, "Masukan keluhan kucingmu");
                    state = "adakeluhan";
                    ans = "";
                }else if(state.equals("adakeluhan") && !ans.equals("tidak") && !ans.equals("ya")){
                    keluhanUser.addAll(Arrays.asList(arrMsg));
                    replyToUser(payload.events[0].replyToken, "Ada keluhan lagi?");
                }else if(ans.equals("tidak") && state.equals("")){
                    replyToUser(payload.events[0].replyToken, "Selamat kucing anda baik baik saja :)");
                }else if(ans.equals("tidak") && state.equals("adakeluhan")){
                    String hasil = data.cekKeluhan(keluhanUser);
                    if(hasil.equals("unknown")){
                        replyToUser(payload.events[0].replyToken, "Mohon maaf kak, Belum ada data yang cocok terhadap keluhan-keluhan tersebut :)");
                        state = "";
                    }else if(hasil.equals("Sehat")){
                        replyToUser(payload.events[0].replyToken, "Kucing anda sehat, itu hanya keluhan normal");
                        state = "";
                    }else{
                        replyToUser(payload.events[0].replyToken, "Penyakit kucing anda adalah " + hasil);
                        state = "";
                    }
                    keluhanUser.clear();
                }else if(ans.equals("tidakSopan")){
                    replyToUser(payload.events[0].replyToken, "Tidak sopan kamu ferguso, dasar " + msgText);
                    state = "";
                }else if(ans.equals("thanks")){
                    replyToUser(payload.events[0].replyToken, "Sama sama kak :)\n\n Semoga lekas sembuh kucingnyaa (love)");
                    state = "";
                }else if(ans.equals("sapa")){
                    replyToUser(payload.events[0].replyToken, "Hallo, \nApakah kucing anda memiliki keluhan?");
                    state = "";
                }else if(ans.equals("unknown")){
                    replyToUser(payload.events[0].replyToken, "Pesan yang anda kirimkan belum ada di memori saya kak.\nJadi, apakah kucing anda memiliki keluhan?");
                }else if(ans.equals("bingung")){
                    replyToUser(payload.events[0].replyToken, "Iya seperti itu kak,\nApakah kucing anda memiliki keluhan?");
                }else if(ans.equals("author")){
                    replyToUser(payload.events[0].replyToken, "Pembuat saya adalah \n Mohamad Achun Armando (140810170020) \n\n Data kucing dari \n Refa Annisatul Ilma (140810170060)");
                }else if(ans.equals("myname")){
                    replyToUser(payload.events[0].replyToken, "Dia adalah pembuat saya bernama lengkap Mohamad Achun Armando");
                }
                else{
                    replyToUser(payload.events[0].replyToken, "Apakah kucing anda memiliki keluhan?");
                }
            }

            return new ResponseEntity<>(HttpStatus.OK);
    }

    private void replyToUser(String rToken, String messageToUser){
        TextMessage textMessage = new TextMessage(messageToUser);
        ReplyMessage replyMessage = new ReplyMessage(rToken, textMessage);
        final LineMessagingClient client = LineMessagingClient
                .builder(lChannelAccessToken)
                .build();

        final BotApiResponse botApiResponse;
        try{
            botApiResponse = client.replyMessage(replyMessage).get();
        }catch(InterruptedException | ExecutionException e){
            System.out.println("Exception is raised");
            e.printStackTrace();
            return;
        }
        System.out.println(botApiResponse);
    }

    private void quickReply(String rToken, String messageToUser){

    }



    private UserProfileResponse getProfile(String userId){
        try{
            return lineMessagingClient.getProfile(userId).get();
        }catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> profile(
            @PathVariable("id") String userId
    ){
        UserProfileResponse profile = getProfile(userId);
        if(profile != null) {
            String profileName = profile.getDisplayName();
            return new ResponseEntity<String>("Hello, " + profileName, HttpStatus.OK);
        }

        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
