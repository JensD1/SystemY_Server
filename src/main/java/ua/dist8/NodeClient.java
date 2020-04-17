package ua.dist8;

import java.util.HashMap;

public class NodeClient {

    public int receiveUnicastMessage(){
        Integer receivedNumberOfMessages = 0;
        Boolean leaveWhile = Boolean.FALSE;
        do{
            if( reveivedMessage){
                //readmessage
                receivedNumberOfMessages++;
                if(vanNameServer && aantalNodes <= 0){
                    leaveWhile = Boolean.TRUE; // er is maar 1 bericht dat ontvangen moest worden en dit is ontvangen
                }
                if(vanNode){
                    // handel dit verder af.
                }
            }
        }while(!leaveWhile && receivedNumberOfMessages<3);
        return 0;
    }

    public int sendUnicastMessage(){
        return 0;
    }
}
