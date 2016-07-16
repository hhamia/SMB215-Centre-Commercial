package com.android.developer.animated.splashscreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    private String REGISTER_URL;
    //private static final String REGISTER_URL = "http://shopstock.esy.es/register.php";
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		 
	        editTextUsername = (EditText) findViewById(R.id.editText_new_pin);
	        editTextPassword = (EditText) findViewById(R.id.editText_confirm_pin);
	        editTextEmail = (EditText) findViewById(R.id.editTex_email);
	 
	        buttonRegister = (Button) findViewById(R.id.btn_register);
	        buttonRegister.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	 registerUser();
	            }
	        });
	       
	}
	 
	 private void registerUser() {
	       
	        String username = editTextUsername.getText().toString().trim().toLowerCase();
	        String password = editTextPassword.getText().toString().trim().toLowerCase();
	        String email = editTextEmail.getText().toString().trim().toLowerCase();
	        if(isEmailValid(email)==false){
	        	Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_LONG).show();
	        	
	        }else{
        	REGISTER_URL = "http://shopstock.esy.es/register.php?q="+username+"&q1="+password+"&q2="+email;

	        register(username,password,email);
	        }
	    }
	 public static boolean isEmailValid(String email) {
		    boolean isValid = false;

		    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		    CharSequence inputStr = email;

		    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(inputStr);
		    if (matcher.matches()) {
		        isValid = true;
		    }
		    return isValid;
		}
	    private void register(String username, String password, String email) {
	        String urlSuffix = "&username="+username+"&password="+password+"&email="+email;
	        class RegisterUser extends AsyncTask<String, Void, String>{
	 
	            ProgressDialog loading;
	 
	 
	            @Override
	            protected void onPreExecute() {
	                super.onPreExecute();
	                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s) {
	                super.onPostExecute(s);
	                loading.dismiss();
	                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            protected String doInBackground(String... params) {
	                String s = params[0];
	                BufferedReader bufferedReader = null;
	                
	                try {
	                    URL url = new URL(REGISTER_URL);
	                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	 
	                    String result;
	 
	                    result = bufferedReader.readLine();
	                    Toast.makeText(getApplicationContext(),"bashar el assad",Toast.LENGTH_LONG).show();
	                    return result;
	                }catch(Exception e){
	                    return null;
	                }
	            }
	        }
	 
	        RegisterUser ru = new RegisterUser();
	        ru.execute(urlSuffix);
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
