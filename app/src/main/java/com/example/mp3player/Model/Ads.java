package com.example.mp3player.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ads implements Serializable {
    @SerializedName("Idquangcao")
    private String idQuangCao;

    @SerializedName("Hinhanh")
    private String hinhAnh;

    @SerializedName("Noidung")
    private String noiDung;

    @SerializedName("Idbaihat")
    private int iD;

    @SerializedName("Tenbaihat")
    private String tenBaiHat;

    @SerializedName("Hinhbaihat")
    private String hinhBaiHat;

    public Ads(String idQuangCao, String hinhAnh, String noiDung, int iD, String tenBaiHat, String hinhBaiHat) {
        this.idQuangCao = idQuangCao;
        this.hinhAnh = hinhAnh;
        this.noiDung = noiDung;
        this.iD = iD;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getIdQuangCao() {
        return idQuangCao;
    }

    public void setIdQuangCao(String idQuangCao) {
        this.idQuangCao = idQuangCao;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }
}
