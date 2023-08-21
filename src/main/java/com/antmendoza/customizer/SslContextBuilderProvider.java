package com.antmendoza.customizer;

import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.temporal.serviceclient.SimpleSslContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;


@Component
public class SslContextBuilderProvider {
    @Autowired
    private ResourceLoader resourceLoader;


    @Value("${mtls.key-file:#{null}}")
    private String temporal_key_location;
    @Value("${mtls.cert-chain-file:#{null}}")
    private String temporal_cert_location;


    public SslContext getSslContext() {

        if (temporal_key_location == null) {
            return null;
        }

        try {
            InputStream clientCert = resourceLoader.getResource(temporal_cert_location).getInputStream();
            InputStream clientKey = resourceLoader.getResource(temporal_key_location).getInputStream();
            return SimpleSslContextBuilder.forPKCS8(clientCert, clientKey).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
