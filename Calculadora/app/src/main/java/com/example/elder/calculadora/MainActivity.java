package com.example.elder.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String resultato;
    TextView result;
    TextView operacao;
    double resultado_final = 0;
    double anterior = 0;
    double num1;
    double num2;
    int click_operacao = 1;
    String numero1;
    String numero2;
    String anterior1 = "";
    String op = "";
    String op_anterior = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        operacao = (TextView) findViewById(R.id.visor_pacial);
        result = (TextView)findViewById(R.id.visor_resultado);
    }

    @Override
    protected void onStart() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }

    @Override
    protected void onRestart() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onRestart();
    }

    @Override
    protected void onPause() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        String respostaBotao = resultato+" = "+resultado_final;
        if(resultato != null) {
            Toast.makeText(getApplicationContext(), respostaBotao, Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();
    }

    //Aqui eu estou tratando as operações
    public void acao1(View v) {
        Button btn = (Button) v;
        String res_btn = btn.getText().toString();
        //Aqui eu estou validadndo para que não se digite o sinal sem ter dititado um numero antes
        if (resultato == null) {
            Toast.makeText(getApplicationContext(), "Ops você precisa digitar um numero primeiro", Toast.LENGTH_SHORT).show();
        }else{

            op = res_btn;

            //Esta variavel esta responsável por escutar o click nas operações
            click_operacao = 1;

            anterior1 = "";

            //Estas operações condicionais são responsáveis para identificar qual operação foi digitada
            if(op.equals("x!")){
                double fat = 1;
                for( int i = 2; i <= num1; i++ )
                {
                    fat *= i;
                }
                resultado_final = fat;
                operacao.setText(resultato+"!");
            }else if(op.equals("v")){
                resultado_final = Math.sqrt(num1);
                operacao.setText("raiz("+resultato+")");
            }else if(op.equals("SIN")){
                double rad = Math.toRadians(num1);
                resultado_final = Math.sin(rad);
                operacao.setText("sin("+resultato+")");
            }else if(op.equals("COS")){
                double rad = Math.toRadians(num1);
                resultado_final = Math.cos(rad);
                operacao.setText("cos("+resultato+")");
            }else if(op.equals("TAN")){
                double rad = Math.toRadians(num1);
                resultado_final = Math.tan(rad);
                operacao.setText("tan("+resultato+")");
            }else if(op.equals("x^")){
                resultato += "^(";
                operacao.setText(resultato);
            }else if(op.equals("%")){
                double porcento = 0;
                switch (op_anterior) {
                    case "+":
                        porcento = (num1*num2)/100;
                        resultado_final = num1 + porcento;

                        break;
                    case "-":
                        porcento = (num1*num2)/100;
                        resultado_final = num1 - porcento;
                        break;
                    case "*":
                        porcento = (num1*num2)/100;
                        resultado_final = num1 * porcento;

                        break;
                    case "÷":
                        porcento = (num1*num2)/100;
                        resultado_final = num1 / porcento;
                        break;
                }
                resultato += "%";
                operacao.setText(resultato);
            }else if(op.equals("+/-")){
                if(op_anterior.equals("+")){
                    resultato += "(-"+num2;
                    resultado_final = resultado_final-num2;
                }else{
                    resultato += "(+"+num2;
                    resultado_final = resultado_final+num2;
                }

                operacao.setText(resultato);
            }else{
                resultato += res_btn;
                operacao.setText(resultato);
            }

            op_anterior = op;
        }

    }

    //Aqui eu estou tratando os numeros
    public void acao(View vew) {
        //faca um cast para Button
        Button botao = (Button) vew;

        //pegue o texto
        String respostaBotao = botao.getText().toString();

        if (respostaBotao.equals("=")){
            String res = Double.toString(resultado_final);
            Log.i("Resultado:",resultato+" = "+res);
                result.setText(res);
        }else{
            //Aqui eu estou verificando se já foi escolhida alguma operação se não continua como o prirmeiro numero
            if(op.equals("")){
                if (resultato == null) {
                    resultato = respostaBotao;
                }else{
                    resultato += respostaBotao;
                }
                if(numero1 == null){
                    numero1 =  respostaBotao;
                }else {
                    numero1 += respostaBotao;
                }
                num1 = Double.parseDouble(numero1);
                //Toast.makeText(getApplicationContext(), numero1, Toast.LENGTH_SHORT).show();
            }else{
                resultato += respostaBotao;
                if(numero2 == null || click_operacao == 1){
                    numero2 =  respostaBotao;
                    click_operacao = 0;
                }else{
                    numero2 +=  respostaBotao;
                }
                num2 = Double.parseDouble(numero2);
                //Aqui eu estou tratando as operações normais
                switch (op) {
                    case "+":
                        if(resultado_final == 0) {
                            resultado_final = num1 + num2;
                        }else{
                            if(anterior1 != ""){
                                anterior = Double.parseDouble(anterior1);
                                resultado_final = resultado_final-anterior;
                            }
                            resultado_final = resultado_final+num2;
                        }

                        break;
                    case "-":
                        if(resultado_final == 0) {
                            resultado_final = num1 - num2;
                        }else{
                            if(anterior1 != ""){
                                anterior = Double.parseDouble(anterior1);
                                resultado_final = resultado_final+anterior;
                            }
                            resultado_final = resultado_final-num2;
                        }
                        break;
                    case "*":
                        if(resultado_final == 0) {
                            resultado_final = num1 * num2;
                        }else{
                            if(anterior1 != ""){
                                anterior = Double.parseDouble(anterior1);
                                resultado_final = resultado_final/anterior;
                            }
                            resultado_final = resultado_final*num2;
                        }
                        break;
                    case "÷":
                        if(resultado_final == 0) {
                            resultado_final = num1 / num2;
                        }else{
                            if(anterior1 != ""){
                                anterior = Double.parseDouble(anterior1);
                                resultado_final = resultado_final*anterior;
                            }
                            resultado_final = resultado_final/num2;
                        }
                        break;
                    case "x^":
                        if(resultado_final == 0) {
                            double numero = num1;
                            for (int i=1; i<num2; i++) {
                                numero = numero*num1;
                            }
                            resultado_final = numero;
                        }else{
                            Toast.makeText(getApplicationContext(), "Desculpe mas você só pode fazer uma operação com esta operação", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "%":
                        if(anterior1 != ""){
                            double porc = (num2*num1)/100;
                            Toast.makeText(getApplicationContext(), "resultado é "+porc, Toast.LENGTH_SHORT).show();
                            resultado_final = porc;
                        }
                        break;
                }

                if(anterior1.equals("")){
                    anterior1 = respostaBotao;
                }else{
                    anterior1 += respostaBotao;
                }
            }
        }
        //Aqui eu estou reescrevendo a TextView responsavel por todas as operações
        switch (op) {
            case "x!":
                operacao.setText(resultato+"!");
                break;
            case "v":
                operacao.setText("raiz("+resultato+")");
                break;
            case "SIN":
                operacao.setText("sin("+resultato+")");
                break;
            case "COS":
                operacao.setText("cos("+resultato+")");
                break;
            case "TAN":
                operacao.setText("tan("+resultato+")");
                break;
            default:
                operacao.setText(resultato);
        }
        //Toast.makeText(getApplicationContext(), resultato, Toast.LENGTH_SHORT).show();
    }

    //Aqui eu estou limpando todas as variáveis
    public void limpar(View v) {

        resultado_final = 0;
        num1 = 0;
        numero1 = null;
        numero2 = null;
        resultato = null;
        operacao.setText("");
        result.setText("");
        op = "";
    }

    //Aqui eu vou enviar para outra activity
    public void enviar(View v){

        //faca um cast para Button
        Button botao = (Button)v;
        //pegue o texto
        String respostaBotao = resultato+" = "+resultado_final;
        Intent it = new Intent(this, Tela2.class);
        Bundle pa = new Bundle();
        pa.putString("res", respostaBotao);
        it.putExtras(pa);
        startActivity(it);

    }
}
