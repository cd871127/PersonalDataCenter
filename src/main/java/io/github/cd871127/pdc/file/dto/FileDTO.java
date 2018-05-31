package io.github.cd871127.pdc.file.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class FileDTO implements Serializable {
    private String fileId;
    private String fileName;
    private String postfix;
    private String userName;
    private String serverPath;

    public FileDTO() {
    }

    public FileDTO(MultipartFile multipartFile) {
        this();
        String originalName = multipartFile.getOriginalFilename();
        if (originalName.contains("."))
            this.setPostfix(originalName.substring(originalName.indexOf(".")));
        else
            this.setPostfix("");
        this.setFileName(originalName.replace(this.getPostfix(), ""));
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
}
