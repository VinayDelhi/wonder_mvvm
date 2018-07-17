package com.example.wonder.wonder_mvvm.data.network;

import com.example.wonder.wonder_mvvm.data.model.Note;
import com.example.wonder.wonder_mvvm.data.model.NoteList;
import com.example.wonder.wonder_mvvm.data.model.NotePostReq;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("notes/{userId}/")
    Observable<ApiResponse<NoteList>> getNotes(
            @Path("userId") String userId,
            @Query("fromDate") String fromDate,
            @Query("toDate") String toDate
    );

    @POST("notes/{userId}/")
    Observable<ApiResponse<Note>> postNote(
            @Path("userId") String userId,
            @Body HashMap<String,NotePostReq> mapNote

    );

}
