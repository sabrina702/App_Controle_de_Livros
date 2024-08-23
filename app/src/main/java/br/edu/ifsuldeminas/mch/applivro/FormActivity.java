package br.edu.ifsuldeminas.mch.applivro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ifsuldeminas.mch.applivro.model.Book;
import br.edu.ifsuldeminas.mch.applivro.model.db.BookDAO;

public class FormActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText bookTitle;
    private EditText bookAuthor;
    private EditText bookPages;
    private RadioGroup bookstatus;
    private Book book;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent chooserIntent = getIntent();
        book = (Book) chooserIntent.getSerializableExtra("id");
        if (book != null) {
            TextInputEditText textInputEditText = findViewById(R.id.book_title);
            TextInputEditText textInputEditText1 = findViewById(R.id.book_author);
            TextInputEditText textInputEditText2 = findViewById(R.id.book_pages);
            RadioGroup statusRadioGroup = findViewById(R.id.radio_group);
            textInputEditText.setText(book.getTitle());
            textInputEditText1.setText(book.getAuthor());
            textInputEditText2.setText(String.valueOf(book.getPages()));
            switch (book.getStatus()) {
                case "Lido":
                    statusRadioGroup.check(R.id.radio_lido);
                    break;
                case "Lendo":
                    statusRadioGroup.check(R.id.radio_lendo);
                    break;
                case "Quero ler":
                    statusRadioGroup.check(R.id.radio_quero_ler);
                    break;
                default:
                    statusRadioGroup.clearCheck();
                    break;
            }

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_book: {

                bookTitle = findViewById(R.id.book_title);
                Editable editable = bookTitle.getText();
                String title = editable != null ? editable.toString() : "";

                bookAuthor = findViewById(R.id.book_author);
                Editable editable1 = bookAuthor.getText();
                String author = editable1 != null ? editable1.toString() : "";

                bookPages = findViewById(R.id.book_pages);
                Editable editable2 = bookPages.getText();
                String pages = editable2 != null ? editable2.toString() : "";

                bookstatus = findViewById(R.id.radio_group);
                int statusSelecionadoId = bookstatus.getCheckedRadioButtonId();
                RadioButton statusSelecionado = findViewById(statusSelecionadoId);
                String status = statusSelecionado != null ? statusSelecionado.getText().toString() : "";

                if (title.isEmpty()) {
                    Toast toast = Toast.makeText(this,
                            "Título não pode ser vazio!", Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                }

                if (author.isEmpty()) {
                    Toast toast = Toast.makeText(this,
                            "Autor não pode ser vazio!", Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                }

                if (status.isEmpty()) {
                    Toast.makeText(this, "Status do livro deve ser selecionado!", Toast.LENGTH_LONG).show();
                    return false;
                }

                BookDAO dao = new BookDAO(this);
                if (this.book == null) {
                    book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setPages(Integer.parseInt(pages));
                    book.setStatus(status);
                    dao.save(book);
                    Toast toast = Toast.makeText(this,
                            "Livro salvo com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setPages(Integer.parseInt(pages));
                    book.setStatus(status);
                    dao.update(book);
                    Toast toast = Toast.makeText(this,
                            "Livro atualizado com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
                break;
            }
        };

        return super.onOptionsItemSelected(item);
    }
}
