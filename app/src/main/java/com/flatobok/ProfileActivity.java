package com.flatobok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

public class ProfileActivity extends AppCompatActivity {
    EditText et_UserName, et_Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(!SharedPrefManager.getInstance(this).IsLoggedIn()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }
        et_UserName = (EditText)findViewById(R.id.et_UserName);
        et_Email = (EditText)findViewById(R.id.et_Email);
        et_UserName.setText(SharedPrefManager.getInstance(this).getUsername());
        et_Email.setText(SharedPrefManager.getInstance(this).getUserEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).LoggedOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return true;
    }
}
