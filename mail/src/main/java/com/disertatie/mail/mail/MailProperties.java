package com.disertatie.mail.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.email")
@EnableConfigurationProperties
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailProperties {
    @Value("${cm.app.email.user}")
    private String address;
    @Value("${cm.app.email.pwd}")
    private String pwd;
    @Value("${cm.app.email.host}")
    private String host;
    @Value("${cm.app.email.port}")
    private String port;

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
