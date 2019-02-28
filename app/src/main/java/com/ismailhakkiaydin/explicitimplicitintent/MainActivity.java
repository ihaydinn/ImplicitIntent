package com.ismailhakkiaydin.explicitimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void tikla(View view){

        Intent intent, chooser;

        if (view.getId()==R.id.button1){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:42,36"));
            chooser = Intent.createChooser(intent, "Uygulama Seçiniz");
            startActivity(chooser);
        }
        else if (view.getId()==R.id.button2){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
            chooser = Intent.createChooser(intent,"Uygulama Seç");
            startActivity(chooser);
        }
        else if (view.getId()==R.id.button3){
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"deneme@deneme.com", "dene@deneme.com"};
            intent.putExtra(intent.EXTRA_EMAIL, to);
            intent.putExtra(intent.EXTRA_SUBJECT, "Bir konu seçiniz...");
            intent.putExtra(intent.EXTRA_TEXT,"Mail içeriğini giriniz...");
            intent.setType("message:/rfc822");
            chooser = Intent.createChooser(intent,"Uygulama Seç");
            startActivity(chooser);
        }
        else if(view.getId()==R.id.button4){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String[] resimDosyaları = file.list();
            Uri uri;
            ArrayList<Uri> arrayList = new ArrayList<Uri>();
            for (String sirala:resimDosyaları){
                Toast.makeText(this,file.toString() + "/"+resimDosyaları,Toast.LENGTH_LONG).show();
                uri = Uri.parse("file://"+file.toString()+"/"+resimDosyaları);
                arrayList.add(uri);
            }
            intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image:/*");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Konu giriniz...");
            intent.putExtra(Intent.EXTRA_TEXT,"İçerik giriniz...");
            intent.putExtra(Intent.EXTRA_STREAM,arrayList);
            chooser = Intent.createChooser(intent, "Uygulama Seç");
            startActivity(chooser);
        }
        else {
            Toast toast = new Toast(this);
            LayoutInflater layoutInflater = getLayoutInflater();

            View view1=layoutInflater.inflate(R.layout.ozel_toast,(ViewGroup) findViewById(R.id.root));
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(view1);
            toast.show();
        }

    }

}
