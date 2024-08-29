package br.edu.ifsuldeminas.mch.applivro.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.applivro.R;
import br.edu.ifsuldeminas.mch.applivro.service.BookInfo;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    private List<BookInfo> books;

    public BooksAdapter(List<BookInfo> books) {
        this.books = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookInfo book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.description.setText(book.getDescription());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateBooks(List<BookInfo> newBooks) {
        books.clear();
        books.addAll(newBooks);
        notifyDataSetChanged();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView description;

        public BookViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            author = itemView.findViewById(R.id.authorsTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
