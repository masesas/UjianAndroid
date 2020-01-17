package com.juaracoding.ujianandroid.soal1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.juaracoding.ujianandroid.R;
import com.juaracoding.ujianandroid.model.postitem.PostItem;
import com.juaracoding.ujianandroid.model.variant.VariantOption;
import com.juaracoding.ujianandroid.service.APIClient;
import com.juaracoding.ujianandroid.service.APIInterfacesRest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputPelanggan extends AppCompatActivity {

    EditText etNamaProduct, etPrice, etDescription;
    ImageView img;
    Spinner spVariant;
    Button btnCamera, btnGallery, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_product);

        etNamaProduct = findViewById(R.id.etNamaProduct);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);

        img = findViewById(R.id.img);
        spVariant = findViewById(R.id.spVariant);

        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        btnSend = findViewById(R.id.btnSend);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postInputData(etNamaProduct.getText().toString(), etPrice.getText().toString(), etDescription.getText().toString(), "image", spVariant.getSelectedItem().toString());

            }
        });

        //calling spinner statement for generate list by API
        spVariant();
    }

    //Post Methode for post product field input
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    byte[] byteArray ;

    public RequestBody toRequestBody(String value) {
        if (value == null) {
            value = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public void postInputData(String productName, String price, String description, String img, String variant) {

        RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpeg"), byteArray);
        MultipartBody.Part bodyImg1 = MultipartBody.Part.createFormData("photo", "dewa.png", requestFile1);

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(InputPelanggan.this);
        progressDialog.setTitle("Sabar Gan!");
        progressDialog.show();
        Call<PostItem> call3 = apiInterface.postInputData(toRequestBody(productName), toRequestBody(price), toRequestBody(description), bodyImg1,  toRequestBody(variant) );
        call3.enqueue(new Callback<PostItem>() {
            @Override
            public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                progressDialog.dismiss();
                PostItem data = response.body();

                if (data != null) {

                    Toast.makeText(InputPelanggan.this, data.getMessage(), Toast.LENGTH_LONG).show();
                    Intent resultIntent = new Intent(InputPelanggan.this, ItemResult.class);
                    startActivity(resultIntent);

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(InputPelanggan.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(InputPelanggan.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<PostItem> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    //statement and methode for variant list
    public void spVariant(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        Call<VariantOption> call3 = apiInterface.getVariant();
        call3.enqueue(new Callback<VariantOption>() {
            @Override
            public void onResponse(Call<VariantOption> call, Response<VariantOption> response) {

                VariantOption data = response.body();

                if (data !=null) {


                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.getData().getVariant().size(); i++){
                        listSpinner.add(data.getData().getVariant().get(i).getVariant());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InputPelanggan.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spVariant.setAdapter(adapter);



                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(InputPelanggan.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(InputPelanggan.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<VariantOption> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });}

    //define Variable perintah
    private int CAMERA_REQUEST = 100;
    private int REQUEST_GALERI = 200;

    //methode intent button open camera
    void openCamera() {


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    //methode intent button open folder
    public void openFolder() {

        Intent folderIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(folderIntent, REQUEST_GALERI);

    }

    Bitmap bitmap;

    //statement for make the camera request to open and folder soon
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byteArray = baos.toByteArray();
            img.setImageBitmap(bitmap);

        } else if (requestCode == REQUEST_GALERI && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            img.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byteArray = baos.toByteArray();
        }
    }
}
