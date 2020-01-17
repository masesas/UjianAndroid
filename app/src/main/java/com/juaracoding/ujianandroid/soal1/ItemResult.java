package com.juaracoding.ujianandroid.soal1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.juaracoding.ujianandroid.R;
import com.juaracoding.ujianandroid.adapter.AdapterListItem;
import com.juaracoding.ujianandroid.model.productresult.ProductResult;
import com.juaracoding.ujianandroid.service.APIClient;
import com.juaracoding.ujianandroid.service.APIInterfacesRest;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemResult extends AppCompatActivity {

    RecyclerView lstItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_result);

        lstItem = findViewById(R.id.lstItem);

        callProductResult();
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void callProductResult(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ItemResult.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ProductResult> call3 = apiInterface.getProduct();
        call3.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                progressDialog.dismiss();
                ProductResult data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {


                    AdapterListItem adapter = new AdapterListItem(ItemResult.this,data.getData().getProduct());

                    lstItem.setLayoutManager(new LinearLayoutManager(ItemResult.this));
                    lstItem.setItemAnimator(new DefaultItemAnimator());
                    lstItem.setAdapter(adapter);


                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ItemResult.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ItemResult.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }


}
