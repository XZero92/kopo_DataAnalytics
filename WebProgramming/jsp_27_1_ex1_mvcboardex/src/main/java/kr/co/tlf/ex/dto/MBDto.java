package kr.co.tlf.ex.dto;

import java.sql.Timestamp;

public class MBDto {

    private int nbBoard;          // NB_BOARD (NUMBER(9)) - NB_MVC_BOARD 대신 또는 함께 사용
    private String nmTitle;       // NM_TITLE (VARCHAR2(200 BYTE))
    private String nmContent;     // NM_CONTENT (VARCHAR2(4000 BYTE))
    private String cbContent;     // CB_CONTENT (CLOB) - 필요시 추가
    private String nmWriter;      // NM_WRITER (VARCHAR2(50 BYTE))
    private Timestamp daWrite;    // DA_WRITE (DATE)
    private int cnHit;            // CN_HIT (NUMBER(5))
    private int nbGroup;          // NB_GROUP (NUMBER(5))
    private int nbStep;           // NB_STEP (NUMBER(5))
    private int nbIndent;         // NB_INDENT (NUMBER(5))
    private String idFile;        // ID_FILE (VARCHAR2(100 BYTE))
    // 필드에 최초 작성일 추가
	private Timestamp daFirstDate;  // DA_FIRST_DATE (DATE)

    // 기본 생성자
    public MBDto() {
    }

    // 모든 필드를 포함하는 생성자 (필요에 따라 추가)
    public MBDto(int nbBoard, String nmTitle, String nmContent, String nmWriter,
                 Timestamp daWrite, int cnHit, int nbGroup, int nbStep, int nbIndent, String idFile) {
        this.nbBoard = nbBoard;
        this.nmTitle = nmTitle;
        this.nmContent = nmContent;
        this.nmWriter = nmWriter;
        this.daWrite = daWrite;
        this.cnHit = cnHit;
        this.nbGroup = nbGroup;
        this.nbStep = nbStep;
        this.nbIndent = nbIndent;
        this.idFile = idFile;
    }

    // Getter 및 Setter 메소드들
    // 예시:
    public int getNbBoard() {
        return nbBoard;
    }

    public void setNbBoard(int nbBoard) {
        this.nbBoard = nbBoard;
    }

    public String getNmTitle() {
        return nmTitle;
    }

    public void setNmTitle(String nmTitle) {
        this.nmTitle = nmTitle;
    }

    public String getNmContent() {
        return nmContent;
    }

    public void setNmContent(String nmContent) {
        this.nmContent = nmContent;
    }

    public String getCbContent() { return cbContent;}

    public void setCbContent(String cbContent) { this.cbContent = cbContent; }

    public String getNmWriter() {
        return nmWriter;
    }

    public void setNmWriter(String nmWriter) {
        this.nmWriter = nmWriter;
    }

    public Timestamp getDaWrite() {
        return daWrite;
    }

    public void setDaWrite(Timestamp daWrite) {
        this.daWrite = daWrite;
    }

    public int getCnHit() {
        return cnHit;
    }

    public void setCnHit(int cnHit) {
        this.cnHit = cnHit;
    }

    public int getNbGroup() {
        return nbGroup;
    }

    public void setNbGroup(int nbGroup) {
        this.nbGroup = nbGroup;
    }

    public int getNbStep() {
        return nbStep;
    }

    public void setNbStep(int nbStep) {
        this.nbStep = nbStep;
    }

    public int getNbIndent() {
        return nbIndent;
    }

    public void setNbIndent(int nbIndent) {
        this.nbIndent = nbIndent;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }
    
    // getter와 setter 메소드 추가
 	public Timestamp getDaFirstDate() {
 		return daFirstDate;
 	}

 	public void setDaFirstDate(Timestamp daFirstDate) {
 		this.daFirstDate = daFirstDate;
 	}

 	// CLOB 필드의 getter와 setter 메소드 (주석 처리)
 	/*
 	public String getCbContent() {
 	    return cbContent;
 	}

 	public void setCbContent(String cbContent) {
 	    this.cbContent = cbContent;
 	}
 	*/
}