package com.angel.common.db.util;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;

public class CryptoUtil {

    private final static String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAg86MppLDBFyNUWLm08eTT2gpUQ3uy0GCHdrqNVusV0KuNGWJGDcJHiEj83ruczaL8LBq6E/KfZ6x4H7rK5ezywIDAQABAkAfR6hna5LueQxskR0Yi1dxU9XiT30pLW20qhxE93GJ74YmZpJ/luJRaxgxD6L5U9+RJvBVXBArLRzOnhvJZ+mBAiEA7M3p8934swZFwf+iorKFb9mtgYsjLxQb3ML482Ud/cECIQCOfcL0WKeuS+mkrZ0SozsxcBmmriI6isFdHGwBCKHsiwIhALcqPwAGfcvb2jFIjn/SHix57x74hGpx32tO1ElPuXcBAiAT4Ygp/5SlxQG8cFzSFORVaYaqsvEjqeiAzU5kdofhMQIhAIPF1DY8dcSpi2QK4c90QvmJq4YkqTrYX5CNC7Snoqrr";

    private final static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIPOjKaSwwRcjVFi5tPHk09oKVEN7stBgh3a6jVbrFdCrjRliRg3CR4hI/N67nM2i/CwauhPyn2eseB+6yuXs8sCAwEAAQ==";

    public static void main(String[] args) throws Exception {
        String password = "angel-spring@123";
        String encodePassword = CryptoUtils.encrypt(password);
        System.out.println(encodePassword);
        //自定义publicKey
        String[] arr = CryptoUtils.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        //HsBXc/YG9FApR9RFcJyB6ZqEONbdF2Mcp3pcBTnIcye4YwPP0qwAdN+ftryRSGoFgUXUWZSrGZp/J+t/DoK/4Q==
        //HsBXc/YG9FApR9RFcJyB6ZqEONbdF2Mcp3pcBTnIcye4YwPP0qwAdN+ftryRSGoFgUXUWZSrGZp/J+t/DoK/4Q==
        System.out.println("password:" + CryptoUtils.encrypt(privateKey, password));
    }
}
