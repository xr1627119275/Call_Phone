package com.example.dev.miss_you;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    private Button bt_ToMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
        initView();
        button.setOnClickListener(this);

    }

    private void initView(){
        editText=(EditText) findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button1);
        bt_ToMain2=(Button)findViewById(R.id.button_ToMain2);
    }
    private void permission(){
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.CALL_PHONE)
// .setDeniedMessage()
// .setDeniedCloseBtn()
// .setDeniedSettingBtn()
// .setRationalMessage()
// .setRationalBtn()
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this,permissions.toString() + "权限拒绝",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                String tel=editText.getText().toString();
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+tel));
                startActivity(intent);
                Toast.makeText(MainActivity.this,"正在拨打电话",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ToMain2:
                Intent intent1=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"添加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
