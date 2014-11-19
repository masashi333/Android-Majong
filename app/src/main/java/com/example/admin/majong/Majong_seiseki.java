package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */
public class Majong_seiseki {
    protected int gameid;
    protected int memberid;
    protected int seiseki;


    public Majong_seiseki(int gameid, int memberid, int seiseki) {
        this.gameid = gameid;
        this.memberid = memberid;
        this.seiseki = seiseki;
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

