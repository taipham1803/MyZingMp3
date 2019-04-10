package com.example.taipham.myzingmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Theloaitrongngay {

    @SerializedName("TheLoai")
    @Expose
    private List<TheLoai> theLoai = null;

    public List<TheLoai> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(List<TheLoai> theLoai) {
        this.theLoai = theLoai;
    }


}