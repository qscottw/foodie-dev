package com.qw.resource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev.properties")
public class FileUpload {
    private String avatarSavePath;
    private String avatarServerUrl;

    public String getAvatarSavePath() {
        return avatarSavePath;
    }

    public void setAvatarSavePath(String avatarSavePath) {
        this.avatarSavePath = avatarSavePath;
    }

    public String getAvatarServerUrl() {
        return avatarServerUrl;
    }

    public void setAvatarServerUrl(String avatarServerUrl) {
        this.avatarServerUrl = avatarServerUrl;
    }
}
