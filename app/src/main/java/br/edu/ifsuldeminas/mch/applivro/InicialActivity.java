package br.edu.ifsuldeminas.mch.applivro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        Button buttonCadBook = findViewById(R.id.button_casdastrar_book);
        buttonCadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicialActivity.this, FormActivity.class);
                startActivity(intent); // Inicie a nova Activity
            }
        });

        Button buttonListarBook = findViewById(R.id.button_listar_book);
        buttonListarBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicialActivity.this, MainActivity.class);
                startActivity(intent); // Inicie a nova Activity
            }
        });
    }
}