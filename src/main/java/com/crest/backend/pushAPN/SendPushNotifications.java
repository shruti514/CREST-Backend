package com.crest.backend.pushAPN;

import com.crest.backend.model.Caregiver;
import com.crest.backend.model.Dependant;
import com.crest.backend.service.UserService;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * Created by sgangras on 12/2/16.
 */
public class SendPushNotifications {
    private final ApnsService apnsService;
    private UserService userService = new UserService();

    public SendPushNotifications(){
         apnsService =  APNS.newService()
                        .withCert("Certificates.p12", "Iam@shruti514")
                        .withSandboxDestination()
                        .build();
    }


    public void sendPushNotifications(String userId, String bus,String tripStatus){
        Dependant dependent = userService.getDependentById(userId);
        Caregiver caregiver = userService.getCaregiverForDependant(userId);

        String payload = APNS.newPayload().sound("default").badge(1).alertBody("User "+ dependent.getName() +" has reached bus stop "+
                bus+". Current trip status is -"+tripStatus).build();
        //String token = "7baba84b5ed0c27c0f6933d864485d29696821f0fafb2f6cbd9ced347e6e1a7f";
        if(caregiver!=null && caregiver.getDeviceToken() != null){
            apnsService.push(caregiver.getDeviceToken(), payload);
        }
    }

    public void sendFallPushNotifications(String userId){
        Dependant dependent = userService.getDependentById(userId);
        String payload = APNS.newPayload().sound("default").badge(1).alertBody("Alert - A device fall has been detected on "+ dependent.getName()
                +" device.").build();
        Caregiver caregiver = userService.getCaregiverForDependant(userId);
       // String token = "7baba84b5ed0c27c0f6933d864485d29696821f0fafb2f6cbd9ced347e6e1a7f";
        if(caregiver!=null && caregiver.getDeviceToken() !=null){
            apnsService.push(caregiver.getDeviceToken(), payload);
        }

    }


    public static void main(String args[]){
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendFallPushNotifications("1051");
    }

}
