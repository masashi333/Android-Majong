package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */
public class Majong_seiseki {
    protected int gameid;
    protected int memberid;
    protected int seiseki;
    protected String title;


    public Majong_seiseki(String title,int gameid, int memberid, int seiseki) {
        this.title = title;
        this.gameid = gameid;
        this.memberid = memberid;
        this.seiseki = seiseki;
    }
    public String getTitle() {
        return title;
    }

    public int getGameid() {
        return gameid;
    }

    public int getMemberid() {
        return memberid;
    }

    public int getSeiseki() {
        return seiseki;
    }
}

