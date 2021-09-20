package com.jakesajao.githubAnalytics.models;

public class Verification{
    public boolean verified;
    public String reason;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getSignature() {
        return signature;
    }

    public void setSignature(Object signature) {
        this.signature = signature;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Object signature;
    public Object payload;

//    public Verification(boolean verified, String reason, Object signature, Object payload) {
//        this.verified = verified;
//        this.reason = reason;
//        this.signature = signature;
//        this.payload = payload;
//    }

}
