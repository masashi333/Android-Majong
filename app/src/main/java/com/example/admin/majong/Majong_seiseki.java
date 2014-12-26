package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */
public class Majong_seiseki {
    protected int gameid;
    protected String member;
    protected int seiseki;


    public Majong_seiseki(int gameid, String member, int seiseki) {
        this.gameid = gameid;
        this.member = member;
        this.seiseki = seiseki;
    }

    public int getGameid() {
        return gameid;
    }

    public String getMember() {
        return member;
    }

    public int getSeiseki() {
        return seiseki;
    }
}

