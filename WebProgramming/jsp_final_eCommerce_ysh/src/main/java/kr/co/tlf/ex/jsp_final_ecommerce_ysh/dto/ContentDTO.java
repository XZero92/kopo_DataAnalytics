package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Blob;
import java.sql.Timestamp;

public class ContentDTO {
    // TB_CONTENT
    private String fileId; //  컨텐츠 식별 ID, ID_FILE
    private String originalFileName; // 원본 파일 명, NM_ORG_FILE
    private String savedFileName; // 저장 파일 명, NM_SAVE_FILE
    private String filePath; // 저장 경로 명, NM_FILE_PATH
    private Blob savedFile; // 저장 파일, BO_SAVE_FILE
    private String fileExtension; // 파일 확장자 명, NM_FILE_EXT
    private String fileTypeCode; // 파일 유형 코드, CD_FILE_TYPE
    private Timestamp saveDate; // 저장 일시, DA_SAVE, DEFAULT SYSDATE
    private int hitCount; // 조회수, CN_HIT, DEFAULT 0
    private String serviceId; // 서비스 ID, ID_SERVICE
    private String originalFileId; // 원 컨텐츠 식별 ID, ID_ORG_FILE
    private String registerNo; // 최초 등록자 ID, NO_REGISTER
    private Timestamp firstDate; // 최초 등록 일시, gksDA_FIRST_DATE

    // Getters and Setters
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Blob getSavedFile() {
        return savedFile;
    }

    public void setSavedFile(Blob savedFile) {
        this.savedFile = savedFile;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public Timestamp getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Timestamp saveDate) {
        this.saveDate = saveDate;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOriginalFileId() {
        return originalFileId;
    }

    public void setOriginalFileId(String originalFileId) {
        this.originalFileId = originalFileId;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }
}