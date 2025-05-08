package kr.co.tlf.ex.dto;

import java.sql.Timestamp;

public class MBDto {
    private int nbMvcBoard;
    private String nmName;
    private String nmTitle;
    private String nmContent;
    private Timestamp daWrite;
    private int cnHit;
    private int nbGroup;
    private int nbStep;
    private int nbIndent;

    public int getNbMvcBoard() {
        return nbMvcBoard;
    }
    public void setNbMvcBoard(int nbMvcBoard) {
        this.nbMvcBoard = nbMvcBoard;
    }

    public String getNmName() {
        return nmName;
    }
    public void setNmName(String nmName) {
        this.nmName = nmName;
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
}
