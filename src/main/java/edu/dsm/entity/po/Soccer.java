package edu.dsm.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Soccer implements Serializable {
    private String matchtype;

    private LocalDateTime matchtime;

    private String matchleague;

    private String host;

    private String guest;

    private BigDecimal eurwin;

    private BigDecimal eurdraw;

    private BigDecimal eurlose;

    private BigDecimal aswin;

    private BigDecimal aslose;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

    public LocalDateTime getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(LocalDateTime matchtime) {
        this.matchtime = matchtime;
    }

    public String getMatchleague() {
        return matchleague;
    }

    public void setMatchleague(String matchleague) {
        this.matchleague = matchleague;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public BigDecimal getEurwin() {
        return eurwin;
    }

    public void setEurwin(BigDecimal eurwin) {
        this.eurwin = eurwin;
    }

    public BigDecimal getEurdraw() {
        return eurdraw;
    }

    public void setEurdraw(BigDecimal eurdraw) {
        this.eurdraw = eurdraw;
    }

    public BigDecimal getEurlose() {
        return eurlose;
    }

    public void setEurlose(BigDecimal eurlose) {
        this.eurlose = eurlose;
    }

    public BigDecimal getAswin() {
        return aswin;
    }

    public void setAswin(BigDecimal aswin) {
        this.aswin = aswin;
    }

    public BigDecimal getAslose() {
        return aslose;
    }

    public void setAslose(BigDecimal aslose) {
        this.aslose = aslose;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", matchtype=").append(matchtype);
        sb.append(", matchtime=").append(matchtime);
        sb.append(", matchleague=").append(matchleague);
        sb.append(", host=").append(host);
        sb.append(", guest=").append(guest);
        sb.append(", eurwin=").append(eurwin);
        sb.append(", eurdraw=").append(eurdraw);
        sb.append(", eurlose=").append(eurlose);
        sb.append(", aswin=").append(aswin);
        sb.append(", aslose=").append(aslose);
        sb.append(", updatetime=").append(updatetime);
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
        Soccer other = (Soccer) that;
        return (this.getMatchtype() == null ? other.getMatchtype() == null : this.getMatchtype().equals(other.getMatchtype()))
            && (this.getMatchtime() == null ? other.getMatchtime() == null : this.getMatchtime().equals(other.getMatchtime()))
            && (this.getMatchleague() == null ? other.getMatchleague() == null : this.getMatchleague().equals(other.getMatchleague()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getGuest() == null ? other.getGuest() == null : this.getGuest().equals(other.getGuest()))
            && (this.getEurwin() == null ? other.getEurwin() == null : this.getEurwin().equals(other.getEurwin()))
            && (this.getEurdraw() == null ? other.getEurdraw() == null : this.getEurdraw().equals(other.getEurdraw()))
            && (this.getEurlose() == null ? other.getEurlose() == null : this.getEurlose().equals(other.getEurlose()))
            && (this.getAswin() == null ? other.getAswin() == null : this.getAswin().equals(other.getAswin()))
            && (this.getAslose() == null ? other.getAslose() == null : this.getAslose().equals(other.getAslose()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMatchtype() == null) ? 0 : getMatchtype().hashCode());
        result = prime * result + ((getMatchtime() == null) ? 0 : getMatchtime().hashCode());
        result = prime * result + ((getMatchleague() == null) ? 0 : getMatchleague().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getGuest() == null) ? 0 : getGuest().hashCode());
        result = prime * result + ((getEurwin() == null) ? 0 : getEurwin().hashCode());
        result = prime * result + ((getEurdraw() == null) ? 0 : getEurdraw().hashCode());
        result = prime * result + ((getEurlose() == null) ? 0 : getEurlose().hashCode());
        result = prime * result + ((getAswin() == null) ? 0 : getAswin().hashCode());
        result = prime * result + ((getAslose() == null) ? 0 : getAslose().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    public Soccer(String matchtype, LocalDateTime matchtime, String matchleague, String host, String guest, BigDecimal eurwin,
            BigDecimal eurdraw, BigDecimal eurlose, BigDecimal aswin, BigDecimal aslose) {
        this.matchtype = matchtype;
        this.matchtime = matchtime;
        this.matchleague = matchleague;
        this.host = host;
        this.guest = guest;
        this.eurwin = eurwin;
        this.eurdraw = eurdraw;
        this.eurlose = eurlose;
        this.aswin = aswin;
        this.aslose = aslose;
    }
}