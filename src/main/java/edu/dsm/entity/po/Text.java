package edu.dsm.entity.po;

import java.io.Serializable;

public class Text implements Serializable {
    private Integer textId;

    private String textSubject;

    private String textDifficulty;

    private String textImageUrl;

    private Byte textNoteFlag;

    private String textNoteText;

    private String textNoteImage;

    private static final long serialVersionUID = 1L;

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public String getTextSubject() {
        return textSubject;
    }

    public void setTextSubject(String textSubject) {
        this.textSubject = textSubject;
    }

    public String getTextDifficulty() {
        return textDifficulty;
    }

    public void setTextDifficulty(String textDifficulty) {
        this.textDifficulty = textDifficulty;
    }

    public String getTextImageUrl() {
        return textImageUrl;
    }

    public void setTextImageUrl(String textImageUrl) {
        this.textImageUrl = textImageUrl;
    }

    public Byte getTextNoteFlag() {
        return textNoteFlag;
    }

    public void setTextNoteFlag(Byte textNoteFlag) {
        this.textNoteFlag = textNoteFlag;
    }

    public String getTextNoteText() {
        return textNoteText;
    }

    public void setTextNoteText(String textNoteText) {
        this.textNoteText = textNoteText;
    }

    public String getTextNoteImage() {
        return textNoteImage;
    }

    public void setTextNoteImage(String textNoteImage) {
        this.textNoteImage = textNoteImage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", textId=").append(textId);
        sb.append(", textSubject=").append(textSubject);
        sb.append(", textDifficulty=").append(textDifficulty);
        sb.append(", textImageUrl=").append(textImageUrl);
        sb.append(", textNoteFlag=").append(textNoteFlag);
        sb.append(", textNoteText=").append(textNoteText);
        sb.append(", textNoteImage=").append(textNoteImage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Text other = (Text) that;
        return (this.getTextId() == null ? other.getTextId() == null : this.getTextId().equals(other.getTextId()))
            && (this.getTextSubject() == null ? other.getTextSubject() == null : this.getTextSubject().equals(other.getTextSubject()))
            && (this.getTextDifficulty() == null ? other.getTextDifficulty() == null : this.getTextDifficulty().equals(other.getTextDifficulty()))
            && (this.getTextImageUrl() == null ? other.getTextImageUrl() == null : this.getTextImageUrl().equals(other.getTextImageUrl()))
            && (this.getTextNoteFlag() == null ? other.getTextNoteFlag() == null : this.getTextNoteFlag().equals(other.getTextNoteFlag()))
            && (this.getTextNoteText() == null ? other.getTextNoteText() == null : this.getTextNoteText().equals(other.getTextNoteText()))
            && (this.getTextNoteImage() == null ? other.getTextNoteImage() == null : this.getTextNoteImage().equals(other.getTextNoteImage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTextId() == null) ? 0 : getTextId().hashCode());
        result = prime * result + ((getTextSubject() == null) ? 0 : getTextSubject().hashCode());
        result = prime * result + ((getTextDifficulty() == null) ? 0 : getTextDifficulty().hashCode());
        result = prime * result + ((getTextImageUrl() == null) ? 0 : getTextImageUrl().hashCode());
        result = prime * result + ((getTextNoteFlag() == null) ? 0 : getTextNoteFlag().hashCode());
        result = prime * result + ((getTextNoteText() == null) ? 0 : getTextNoteText().hashCode());
        result = prime * result + ((getTextNoteImage() == null) ? 0 : getTextNoteImage().hashCode());
        return result;
    }
}