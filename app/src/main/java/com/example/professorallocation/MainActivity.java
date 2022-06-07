package com.example.professorallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etNomeCurso;
    private EditText etIdCurso;
    private Button btCadastrar;
    private Button btEditar;
    private ListView lvListaCurso;

    private CursoService requestCurso;
    private CursoResponse cursoResponse;
    private List<String> listaNomeCursos;

    private EditText etDepartmentName;
    private EditText etDepartmentId;
    private Button btSend;
    private Button btEdit;
    private ListView lvDepartmentsLists;

    private DepartmentService requestDepartment;
    private DepartmentResponse departmentResponse;
    private List<String> departmentsList;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNomeCursos = new ArrayList<>();

        etNomeCurso = findViewById(R.id.etNomeCurso);
        etIdCurso = findViewById(R.id.etIdCurso);
        btCadastrar = findViewById(R.id.btCadastrar);
        btEditar = findViewById(R.id.btEditar);
        lvListaCurso = findViewById(R.id.lvListaCursos);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaNomeCursos);
        lvListaCurso.setAdapter(adapter);

        requestCurso = new RetrofitConfig()
                .criarService();

        CursoPost sendCursoBody = new CursoPost();

        btCadastrar.setOnClickListener(view -> {
            sendCursoBody.setName(etNomeCurso.getText().toString());
            executarRequestPost(sendCursoBody);
        });

        btEditar.setOnClickListener(view -> {
            sendCursoBody.setName(etNomeCurso.getText().toString());
            String textoUsuario = etIdCurso.getText().toString();
            int idCurso = Integer.parseInt(textoUsuario);
            executarRequestPut(sendCursoBody, idCurso);
        });

        departmentsList = new ArrayList<>();

        etDepartmentName = findViewById(R.id.etDepartmentName);
        etDepartmentId = findViewById(R.id.etDepartmentId);
        btSend = findViewById(R.id.btSend);
        btEdit = findViewById(R.id.btEdit);
        lvDepartmentsLists = findViewById(R.id.lvDepartmentsLists);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, departmentsList);
        lvDepartmentsLists.setAdapter(adapter);

        requestDepartment = new RetrofitConfig()
                .createService();

        DepartmentPost sendDepartmentBody = new DepartmentPost();

        btSend.setOnClickListener(view -> {
            sendDepartmentBody.setName(etDepartmentName.getText().toString());
            executarRequestPost(sendDepartmentBody);
        });

        btEdit.setOnClickListener(view -> {
            sendDepartmentBody.setName(etDepartmentName.getText().toString());
            String textoUsuario = etDepartmentId.getText().toString();
            int idDepartment = Integer.parseInt(textoUsuario);
            executarRequestPut(sendDepartmentBody, idDepartment);
        });

    }

    public void executarRequestGetAll(View view) {

        requestCurso.createRequestGetAll().enqueue(new Callback<List<CursoResponse>>() {
            @Override
            public void onResponse(Call<List<CursoResponse>> call, Response<List<CursoResponse>> response) {
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                List<CursoResponse> cursoLista = response.body();

                for (CursoResponse curso : cursoLista) {
                    Log.i(">>> Resultado", curso.getId() + " " + curso.getName());

                    listaNomeCursos.add(curso.getName());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CursoResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void executarRequestDelete(View view) {
        String idDigitado = etIdCurso.getText().toString();
        int id = Integer.parseInt(idDigitado);

        requestCurso.createRequestDelete(id).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestPost(CursoPost cursoPost) {
        requestCurso.createRequestPost(cursoPost).enqueue(new Callback<CursoResponse>() {
            @Override
            public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                cursoResponse = response.body();
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CursoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestPut(CursoPost cursoPut, int id) {
        requestCurso.createRequestPut(cursoPut, id).enqueue(new Callback<CursoResponse>() {
            @Override
            public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CursoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestPost(DepartmentPost departmentPost) {
        requestDepartment.createRequestPost(departmentPost).enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                departmentResponse = response.body();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestPut(DepartmentPost departmentPut, int id) {
        requestDepartment.createRequestPut(departmentPut, id).enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestDelete2(View view) {
        String idDigitado = etDepartmentId.getText().toString();
        int id = Integer.parseInt(idDigitado);

        requestDepartment.createRequestDelete(id).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestGetAll2(View view) {
        requestDepartment.createRequestGetAll().enqueue(new Callback<List<DepartmentResponse>>() {
            @Override
            public void onResponse(Call<List<DepartmentResponse>> call, Response<List<DepartmentResponse>> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                List<DepartmentResponse> departmentList = response.body();

                for (DepartmentResponse department : departmentList) {
                    Log.i(">>> Result", department.getId() + " " + department.getName());

                    departmentsList.add(department.getName());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DepartmentResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });

    }


}