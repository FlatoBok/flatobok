package com.flatobok;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
 private EditText editTextUsername, editTextPassword,editTextEmail;
    Button btn_Regster;
    ProgressDialog progressDialog;
    TextView textViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).IsLoggedIn()){

            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPssoword);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);

        textViewLogin = (TextView)findViewById(R.id.textViewLogin);

        btn_Regster = (Button)findViewById(R.id.btn_Register);
        progressDialog = new ProgressDialog(this);

        btn_Regster.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

private void registeruser(){
  final   String email = editTextEmail.getText().toString().trim();
    final String username = editTextUsername.getText().toString().trim();
   final String password = editTextPassword.getText().toString().trim();

progressDialog.setMessage("In Process Please Wait....");
    progressDialog.show();

    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
    progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
    progressDialog.hide();
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("username",username);
            params.put("email",email);
            params.put("password",password);
            return  params;

        }
    };
    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
}

    @Override
    public void onClick(View v) {
    if(v == btn_Regster) {

            registeruser();
    }
        if(v == textViewLogin){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
    }
