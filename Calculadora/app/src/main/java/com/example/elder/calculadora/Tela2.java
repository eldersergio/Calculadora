package com.example.elder.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {
    TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Intent it = getIntent();
        if (it != null){
            Bundle par = it.getExtras();
            if (par != null) {
                txt2 = (TextView) findViewById(R.id.txt2);
                String resultado = par.getString("res");
                txt2.setText(resultado);
            }
        }
    }
}
