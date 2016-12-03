package com.crest.backend.pushAPN;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * Created by sgangras on 12/2/16.
 */
public class SendPushNotifications {
    private final ApnsService apnsService;

    public SendPushNotifications(){
         apnsService =  APNS.newService()
                        .withCert("Certificates.p12", "Iam@shruti514")
                        .withSandboxDestination()
                        .build();
    }


    public void sendPushNotifications(String userId, String bus,String tripStatus){
        String user = "Anne";
        String payload = APNS.newPayload().sound("default").badge(1).alertBody("User "+ user +" has reached bus stop "+
                bus+". Current trip status is -"+tripStatus).build();
        String token = "7cb16eacc7ae77a49b532aaf80876ea6c36ecf7f79dce73ce449eb94c4c50d7b";
        apnsService.push(token, payload);
    }


    /*public static void main(String args[]){
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendPushNotifications("someuserId","someBusNumber");
    }*/

}
