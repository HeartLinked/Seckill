package com.example.seckill.util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCUUU+u933bFo1r\n" +
            "v2WcqQjCQ2MwTb++wzB8Of+YALfgKCPkbCWiZBg3bvJs3Hf5/wlI3lwNDzkI8bmT\n" +
            "GyBhLLOh46Co4FuHtAGndsUdWBSqzp4e9Ru9WEE6VAH8A2ix4KKgzlcyziidBIjj\n" +
            "xf0/l6wzBn90S5lHv7gyHrHAieF97vKjJurNbZyUGvwbT4FCmuVt8LpMyIgAGZng\n" +
            "trrS80WjN3DQQ+obGXAeIp6+/fdexlSqi7bMFo7kc2GJmNtLVemnxbQRBUBEzpgr\n" +
            "Vfk9bW1nGmVQFP2MVH/LzNUCEdMWLx+pbhg3VFyg7/0h4wxE0xllgYSXlP92VzdV\n" +
            "jIVM+whPAgMBAAECggEAJm6jbT0oWZsQbjkcRWnvoRcBHsUmyHGfoLEOr/6Ppv//\n" +
            "vPLhFaFGPSb8noTKefdCGqWzyJaHOvAYHCxNSaDhszNesCPi09krDI/UJ/Ro9pGv\n" +
            "m2n8FCZIUKrwELN5b4471dth8QJGt9ky94KYSuAseNRYNnRYdaUSP7qAaNnRmmXp\n" +
            "cBMW4fEpfupR6SouHn/MnqWsSGDwAWbQCDSj7CpMKSrwA87ehF5eusw1OwOmac7R\n" +
            "q4k3E8UK1Sb0YJoKBXGIoc34R3AbDRUk23IY93hdedW6ZVDlo1lkqRvbmdF0+Dj8\n" +
            "M5sPW7m0sOc0U4w7NxmeJXDU+x7mNwJFsnuqEXvKgQKBgQDE+9U/QhpiCNxlbZGQ\n" +
            "GUT7wcDMQq7PMP9XRmiNB8MrVGplReC0sscUPCsY4rEbqjrZQ2S/HZkxxbdULx5q\n" +
            "CAkx08C4wDIkS6x+90wm9zfgoZrPVAkM7itcCtBWTjhWvIa33sKJPq0o78xwObKD\n" +
            "1BEMsj1PgFgq9xl9GKh8B0phbwKBgQDAwOh2vJ512NJ1s+vXKYxBbmgGMEBeLJ09\n" +
            "CjXgW4K1KgB+GQMGzslnK9G3BB1SK8xIBdDpUMh1Dk5nv5+yqkRlSeUsMnVOfGbH\n" +
            "szmaLCPOxBAuA1p+zKp7Lhb8VzbRIrnbC2mTofUC/cAg9c8MQ7O1s+o8FhBHbbeF\n" +
            "Z4gpg9SXIQKBgQCWHO/yaBag9O24IdqW/JrUXF/niT2cSVOdF9ve9bZm08rM6IaI\n" +
            "TeFmaz/QlBix43U7Up95AT5Ybrr53XqJBRy0qxEn+dd8T2rhyuVuoGElvyGxs/G9\n" +
            "t9a1Ksx/qNZISx1DC/AmFOxy0ONz9pQy4C4qBb6xUjZeb7Yt3devxQKK4wKBgEQc\n" +
            "Zoh+gif2Y/f/idjP76W3U77MEhK1BSLqlrse2wMVQnNMd0tSisyQKVzYcVv16guR\n" +
            "Mo8fYpYqZf43MkE59U0tXXPOqLEBOHhcloAqFMUddvIrcTDZNWytjqXAU7h24uHK\n" +
            "GJZ4ZlGWROvgsDcPPVN5rc1ppnr3BCB5wx2YXejBAoGAJYW6rG5KFoPng46MgJz7\n" +
            "tj+ynoyJGS0rsUc6He8uyJzaDgSQ6tQ4U339zw9NSQQT+HGsEcPOl+RGuNpJMyjK\n" +
            "IYasx/NLLElp9m9Hr1kWCZTiGoz+74/Jzr0pnxFZk9RXi5AaOcah8e5bwPhXSHRl\n" +
            "+h4cWdPRRIaSTo9AB6A8egg=";

    public static String decrypt(String encryptedData) throws Exception {
        // Use the MIME decoder to handle potential line breaks in the encrypted data
        byte[] encryptedBytes = Base64.getMimeDecoder().decode(encryptedData);

        // Use the MIME decoder for the private key as it contains line breaks
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(PRIVATE_KEY));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}
