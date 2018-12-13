package com.atjhoendz.catcare;

import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value="/linebot")
public class LineBotController {

    ArrayList<String> keluhanUser = new ArrayList<String>();
    Data data = new Data();
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
                msgText = payload.events[0].message.text;
                msgText = msgText.toLowerCase();
                String[] arrMsg = msgText.split(" ");

                if(msgText.equals("ya")){
                    replyToUser(payload.events[0].replyToken, "Masukan keluhan kucingmu");
                    state = "ya";
                }else if(state.equals("ya")){
                    for(int i = 0; i < arrMsg.length; i++){
                        keluhanUser.add(arrMsg[i]);
                    }
                }else if(msgText.equals("tidak") && state.equals("")){
                    replyToUser(payload.events[0].replyToken, "Selamat kucing anda baik baik saja :)");
                }else if(msgText.equals("tidak") && state.equals("ya")){
                    String hasil = data.cekKeluhan(keluhanUser);
//                    if(!hasil.equals("Sehat")){
//                        replyToUser(payload.events[0].replyToken, "Penyakit kucing anda adalah " + hasil);
//                        state = "";
//                    }else{
//                        replyToUser(payload.events[0].replyToken, "Kucing anda sehat, itu hanya keluhan normal");
//                        state = "";
//                    }
                    replyToUser(payload.events[0].replyToken, keluhanUser.toString());
                    state = "";
                }else if(state.equals("ya")){
                    replyToUser(payload.events[0].replyToken, "Ada keluhan lagi?");
                }else{
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
