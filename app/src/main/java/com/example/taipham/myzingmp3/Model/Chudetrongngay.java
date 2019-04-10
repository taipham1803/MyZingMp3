package com.example.taipham.myzingmp3.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chudetrongngay {

    @SerializedName("ChuDe")
    @Expose
    private List<ChuDe> chuDe = null;

    public List<ChuDe> getChuDe() {
        return chuDe;
    }

    public void setChuDe(List<ChuDe> chuDe) {
        this.chuDe = chuDe;
    }

}