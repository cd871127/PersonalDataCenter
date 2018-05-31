package io.github.cd871127.pdc.file.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class FileDTO implements Serializable {
    private String fileId;
    private String fileName;
    private long fileSize;
    private String postfix;
    private String userName;
    private String serverPath;
    private Date createdDate;

    private String readableCreatedDate;
    private String readableFileSize;

    public FileDTO() {
    }

    public FileDTO(MultipartFile multipartFile) {
        this();
        String originalName = multipartFile.getOriginalFilename();
        if (originalName.contains("."))
            this.setPostfix(originalName.substring(originalName.lastIndexOf(".")));
        else
            this.setPostfix("");
        this.setFileName(originalName.replace(this.getPostfix(), ""));
        this.setFileSize(multipartFile.getSize());
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getReadableCreatedDate() {
        return readableCreatedDate;
    }

    public void setReadableCreatedDate(String readableCreatedDate) {
        this.readableCreatedDate = readableCreatedDate;
    }

    public String getReadableFileSize() {
        return readableFileSize;
    }

    public void setReadableFileSize(String readableFileSize) {
        this.readableFileSize = readableFileSize;
    }

}
