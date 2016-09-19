package com.example.user.either.Either;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.either.Data.PrefManager;
import com.example.user.either.Model.User;
import com.example.user.either.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends AppCompatActivity {

    private EditText inputLogin, inputPassword,inputRePassword,inputName;
    private TextInputLayout inputLayoutLogin, inputLayoutPassword,inputLayoutRePassword,inputLayoutName;
    private Button btnSignup;

    PrefManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputLayoutLogin = (TextInputLayout) findViewById(R.id.input_layout_login);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutRePassword = (TextInputLayout) findViewById(R.id.input_layout_repassword);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);

        inputLogin = (EditText) findViewById(R.id.input_login);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputRePassword= (EditText) findViewById(R.id.input_repassword);
        inputName = (EditText) findViewById(R.id.input_name);

        btnSignup = (Button) findViewById(R.id.btn_signup);

        prefs = new PrefManager(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    public void login() {

        if (!validate()) {
            return;
        }

        btnSignup.setEnabled(false);

        String login = inputLogin.getText().toString();
        String password = inputPassword.getText().toString();
        String name = inputName.getText().toString();

        Log.v("Data sent",login + "  " + password + "  " + name);
        String url = "http://apistorage.name/wyr_arabic/add_user.php?login=" + login + "&password=" + password+"&name="+name;

        new loginServerGEt().execute(url);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btnSignup.setEnabled(true);
        User user = new User(inputName.getText().toString(),inputLogin.getText().toString(),inputPassword.getText().toString());
        prefs.createLogin(user);
        startActivity(new Intent(SignUp.this, MainActivity.class));
        finish();
    }

    public void onLoginFailed() {
        showDialog();
        btnSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = inputLogin.getText().toString();
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();
        String repassword = inputRePassword.getText().toString();

        if (email.isEmpty()) {
            inputLogin.setError(getString(R.string.err_msg_login));
            valid = false;
        } else {
            inputLogin.setError(null);
        }

        if (name.isEmpty()) {
            inputLogin.setError(getString(R.string.err_msg_login));
            valid = false;
        } else {
            inputLogin.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            inputPassword.setError(getString(R.string.err_msg_password));
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        if(!password.equals(repassword)){
            inputPassword.setError(getString(R.string.err_msg_password));
            inputRePassword.setError(getString(R.string.err_msg_password));
            valid = false;
        }else {
            inputPassword.setError(null);
            inputRePassword.setError(null);
        }

        return valid;
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        dialog.getWindow().setContentView(R.layout.error_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView text1 = (TextView) dialog.findViewById(R.id.text_dialog_error);

        text1.setText("Login Failed..Invalid login or password, Try again");

        ImageButton cancelDialogButton = (ImageButton) dialog.findViewById(R.id.cancel_dialog);

        cancelDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private class loginServerGEt extends AsyncTask<String, Integer, String> {

        final ProgressDialog progressDialog = new ProgressDialog(SignUp.this,
                R.style.AppTheme_Dark_Dialog);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loggin in...");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // mybar. setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String jsonStr = "";
            String state = "false";
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                jsonStr = buffer.toString();
                Log.v("Data JSON ", jsonStr);
                if (jsonStr != null) {
                    if (jsonStr.contains("good")){
                        state = "true";
                        return state;
                    }
                } else {
                    Log.e("ServiceHandler", "Couldn't get any from the url");
                    progressDialog.dismiss();
                }

            } catch (Exception e) {
                Log.e("Error HTTP Connection", e.toString());
                progressDialog.dismiss();
            }
            return jsonStr;
        }

        // RESULT
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.v("data ", result);
            if (result.equals("true")) {
                onLoginSuccess();
            } else {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                onLoginFailed();
            }
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}
