package com.crest.backend.pushAPN;

import com.crest.backend.model.Dependant;
import com.crest.backend.service.UserService;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import org.springframework.util.StringUtils;

import static weka.core.pmml.jaxbbindings.LINKFUNCTION.LOG;

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
        String payload = APNS.newPayload().sound("default").badge(1).alertBody("User "+ dependent.getName() +" has reached bus stop "+
                bus+". Current trip status is -"+tripStatus).build();
        String token = "7f29676dfeaabb0364bab68f6849ab265de6d9daca80836e948f9d127ba9feb5";
        apnsService.push(token, payload);
    }

    public void sendFallPushNotifications(String userId){
        Dependant dependent = userService.getDependentById(userId);
        String payload = APNS.newPayload().sound("default").badge(1).alertBody("Alert - A device fall has been detected on "+ dependent.getName()
                +" device.").build();
        String token = "0ac1bc3dcaf6ffbc1b0b3b526c53d1259e2303f51401d7a82db3dcbe86b670f3";
        apnsService.push(token, payload);
    }


    private String getValidMarketingEmailOptIn(String marketingEmailOptIn){
        if (!StringUtils.isEmpty(marketingEmailOptIn) &&
                !("Y".equalsIgnoreCase(marketingEmailOptIn) || "N".equalsIgnoreCase(marketingEmailOptIn))) {
            System.out.println("message=\"Invalid value received for marketingEmailOptIn.\", marketingEmailOptIn = {} method=\"socialLogin\""+
                    marketingEmailOptIn);
        }
        return !StringUtils.isEmpty(marketingEmailOptIn) && "Y".equalsIgnoreCase(marketingEmailOptIn)? "Y":"N";
    }

    public static void main(String args[]){
        SendPushNotifications sendPushNotifications = new SendPushNotifications();

        String validParam = sendPushNotifications.getValidMarketingEmailOptIn("Y");
        System.out.println(validParam);
    }



}
