package com.example.mp3player.Service;

import com.example.mp3player.Model.Ads;
import com.example.mp3player.Model.Album;
import com.example.mp3player.Model.Playlist;
import com.example.mp3player.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //get data Advertisiment
    @GET("songbanner.php")
    Call<List<Ads>> getListAds();

    //get data Song by Ads id
    @FormUrlEncoded
    @POST("getSongWithAds.php")
    Call<List<Song>> getListSongByAdsId(@Field("idquangcao") String idAds);

    //get top 3 Playlist by day
    @GET("getPlaylist.php")
    Call<List<Playlist>> getListPlaylist();

    //get Song by Playlist id
    @FormUrlEncoded
    @POST("getSongWithPlaylistid.php")
    Call<List<Song>> getListSongByPlaylistId(@Field("idPlaylist") String idPlaylist);

    //get all playlist
    @GET("getAllPlaylist.php")
    Call<List<Playlist>> getListAllPlaylist();

    //get top 3 Album
    @GET("getalbum.php")
    Call<List<Album>> getListAlbum();

    //get list song by Album id
    @FormUrlEncoded
    @POST("getSongWithAlbumId.php")
    Call<List<Song>> getListSongByAlbumId(@Field("idAlbum") String idAlbum);

    //get all album
    @GET("getAllAlbum.php")
    Call<List<Album>> getAllAlbum();

    //get list favourite
    @GET("getfavoritesong.php")
    Call<List<Song>> getListFavoriteSong();

    @FormUrlEncoded
    @POST("searchSong.php")
    Call<List<Song>> getListSearchSong(@Field("keySearch") String searchKey);
}
