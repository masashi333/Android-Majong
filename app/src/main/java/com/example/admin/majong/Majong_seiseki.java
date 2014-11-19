package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */
public class Majong_seiseki {
    protected int id;
    protected String gameid;
    protected String memberid;
    protected String seiseki;


    public Majong_seiseki(int id,String gameid,String memberid){
        this.id = id;
        this.gameid = gameid;
        this.memberid = memberid;
        this.seiseki = seiseki;
    }

    public String getGameid(){
        return gameid;
    }
    public String getMemberid(){
        return memberid;
    }

    public String getSeiseki(){
        return seiseki;
    }

    public int getId(){
        return id;
    }


}
