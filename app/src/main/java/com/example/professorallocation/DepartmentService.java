package com.example.professorallocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//Mapeia os métodos para usar no BackEnd
public interface DepartmentService {
    @POST("departments")
    Call<DepartmentResponse> createRequestPost(@Body DepartmentPost departmentPost);

    @PUT("departments/{department_id}")
    Call<DepartmentResponse> createRequestPut(@Body DepartmentPost departmentPost, @Path("department_id") int id);

    @DELETE("departments/{department_id}")
    Call<Object> createRequestDelete(@Path("department_id") int id);

    @GET("departments")
    Call<List<DepartmentResponse>> createRequestGetAll();
}
