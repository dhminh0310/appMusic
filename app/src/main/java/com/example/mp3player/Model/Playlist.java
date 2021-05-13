package com.example.mp3player.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Parcelable, Serializable {

    @SerializedName("mId")
    @Expose
    private String id;
    @SerializedName("mTenPlaylist")
    @Expose
    private String tenPlaylist;
    @SerializedName("mHinhNenPlaylist")
    @Expose
    private String hinhNenPlaylist;

    protected Playlist(Parcel in) {
        id = in.readString();
        tenPlaylist = in.readString();
        hinhNenPlaylist = in.readString();
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getTenPlaylist() {
        return tenPlaylist;
    }

    public void setTenPlaylist(String TenPlaylist) {
        this.tenPlaylist = TenPlaylist;
    }

    public String getHinhNenPlaylist() {
        return hinhNenPlaylist;
    }

    public void setHinhNenPlaylist(String HinhNenPlaylist) {
        this.hinhNenPlaylist = HinhNenPlaylist;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tenPlaylist);
        dest.writeString(hinhNenPlaylist);
    }
}
