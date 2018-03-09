package com.lateroad.bank.logic.security;

import java.util.HashMap;

public class HashMessage {
    private static final HashMessage instance = new HashMessage();
    private static HashMap<String, String> keyMap = new HashMap<>();

    private HashMessage() {
    }

    public static String getKey(String login) {
        return keyMap.get(login);
    }

    public static void setKey(String login, String key) {
        keyMap.put(login, key);
    }

    public static HashMessage getInstance() {
        return instance;
    }

    public byte[] encode(String pText, String login) {
        byte[] txt = pText.getBytes();
        byte[] pKey = keyMap.get(login).getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; ++i) {
            res[i] = (byte) (txt[i] ^ pKey[i % pKey.length]);
        }
        return res;
    }


    public String decode(byte[] pText, String login) {
        byte[] res = new byte[pText.length];
        byte[] pKey = keyMap.get(login).getBytes();

        for (int i = 0; i < pText.length; ++i) {
            res[i] = (byte) (pText[i] ^ pKey[i % pKey.length]);
        }
        return new String(res);
    }

    public String decodeWithKey(byte[] pText, String key) {
        byte[] res = new byte[pText.length];
        byte[] pKey = key.getBytes();

        for (int i = 0; i < pText.length; ++i) {
            res[i] = (byte) (pText[i] ^ pKey[i % pKey.length]);
        }
        return new String(res);
    }
}
