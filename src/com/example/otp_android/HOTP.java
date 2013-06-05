package com.example.otp_android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HOTP {
    private Mac mac;
    public enum AlgorithmType{
        SHA1,SHA256,SHA384,SHA512;
    }

    public HOTP(AlgorithmType alg, int digits,final byte[] passPhrase){
        try {
            SecretKeySpec key=new SecretKeySpec(passPhrase,"raw");
            mac=Mac.getInstance("Hmac"+alg);
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int generateHTOPPassword(long counter){
        byte [] bytes=ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(counter).array();
        byte []resultingHash=mac.doFinal(bytes);
        int offset=resultingHash[19]&0x0F;
        int password=((ByteBuffer)ByteBuffer.wrap(resultingHash).order(ByteOrder.BIG_ENDIAN).position(offset)).getInt()& 0x7FFFFFFF;
        return password;
    }

}
