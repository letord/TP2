package com.appli.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button envoyer=null;
    Button reset = null;
    EditText taille = null;
    EditText poids = null;
    CheckBox commentaire = null;
    RadioGroup group = null;
    TextView result = null;
    private final String texteInit = "Cliquez sur le bouton « Calculer l'IMC » pour obtenir un résultat.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        envoyer = (Button)findViewById(R.id.calcul);
        reset = (Button)findViewById(R.id.reset);
        taille = (EditText)findViewById(R.id.taille);
        poids = (EditText)findViewById(R.id.poids);
        commentaire = (CheckBox)findViewById(R.id.commentaire);
        group = (RadioGroup)findViewById(R.id.group);
        result = (TextView)findViewById(R.id.result);


        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t= taille.getText().toString();
                String p=poids.getText().toString();
                float tValue = Float.valueOf(t);
                float pValue = Float.valueOf(p);

                if(tValue<=0){
                    Toast.makeText(MainActivity.this, "la taille doit être positive",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pValue<=0){
                        Toast.makeText(MainActivity.this, "le poids doit être positive",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (group.getCheckedRadioButtonId() == R.id.radio_centimetre) tValue = tValue / 100;
                        float imc = pValue / (tValue * tValue);
                        String resultat="Votre IMC est " + imc+" . ";
                        if(commentaire.isChecked()) resultat +=interpreteIMC(imc);
                        result.setText(resultat);

                    }
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taille.getText().clear();
                poids.getText().clear();
                result.setText(texteInit);
            }
        });
        commentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()) {
                    result.setText(texteInit);
                }
            }
        });
        taille.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                result.setText(texteInit);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        poids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                result.setText(texteInit);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    String interpreteIMC(double imc){
        if(imc<16.5){
            return "famine";
        }
        else if (16.5<imc && imc<18.5){
            return "maigreur";
        }
        else if (18.5<imc && imc<25){
            return "corpulence normale";
        }
        else if (25<imc && imc<30){
            return "surpoids";
        }
        else if (30<imc && imc<35){
            return "obésité modérée";
        }
        else if (35<imc && imc<40){
            return "obésité sévère";
        }
        else {
            return "obésité morbide ou massive";
        }
    }
}
