package br.edu.ifsuldeminas.mch.applivro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.applivro.adapters.BooksAdapter;
import br.edu.ifsuldeminas.mch.applivro.service.BookInfo;
import br.edu.ifsuldeminas.mch.applivro.service.BookSearchService;


public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText searchQuery;
    private Button searchButton;
    private RecyclerView recyclerView;
    private BooksAdapter bookAdapter;
    private BookSearchService bookSearchService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchQuery = findViewById(R.id.searchQuery);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BooksAdapter(new ArrayList<>());
        recyclerView.setAdapter(bookAdapter);

        bookSearchService = new BookSearchService();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String query = searchQuery.getText().toString().trim();
        if (!TextUtils.isEmpty(query)) {
            bookSearchService.searchBooks(query, new BookSearchService.BookSearchCallback() {
                @Override
                public void onSuccess(List<BookInfo> bookInfos) {
                    bookAdapter.updateBooks(bookInfos);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(SearchActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(SearchActivity.this, "Por favor insira uma consulta de pesquisa", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SearchActivity.this, InicialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finaliza a SearchActivity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
