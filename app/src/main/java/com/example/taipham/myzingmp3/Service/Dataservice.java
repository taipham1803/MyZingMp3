package com.example.taipham.myzingmp3.Service;

import com.example.taipham.myzingmp3.Model.Album;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.Model.ChuDe;
import com.example.taipham.myzingmp3.Model.ChuDeTheLoai;
import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.Model.Quangcao;
import com.example.taipham.myzingmp3.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("favoritesong.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("listplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlayList();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetDanhSachBaiHatTheoTheLoai(@Field("idchude") String idchude);

    @GET("allchude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("allalbum.php")
    Call<List<Album>> GetTatCaCacAlbum();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchsong.php")
    Call<List<Baihat>> GetSearchSong(@Field("keyword") String keyword);

}
