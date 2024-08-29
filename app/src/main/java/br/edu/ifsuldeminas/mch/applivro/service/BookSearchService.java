package br.edu.ifsuldeminas.mch.applivro.service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookSearchService {
    private BookApiService bookApiService;

    public BookSearchService() {
        bookApiService = BookApiClient.getClient().create(BookApiService.class);
    }

    public void searchBooks(String query, final BookSearchCallback callback) {
        Call<BookResponse> call = bookApiService.searchBooks(query);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookInfo> bookInfos = new ArrayList<>();
                    for (BookResponse.Item item : response.body().getItems()) {
                        BookResponse.VolumeInfo volumeInfo = item.getVolumeInfo();
                        BookInfo bookInfo = new BookInfo(
                                volumeInfo.getTitle(),
                                String.join(", ", volumeInfo.getAuthors()),
                                volumeInfo.getDescription()
                        );
                        bookInfos.add(bookInfo);
                    }
                    callback.onSuccess(bookInfos);
                } else {
                    callback.onFailure("Erro na resposta da API");
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface BookSearchCallback {
        void onSuccess(List<BookInfo> bookInfos);
        void onFailure(String errorMessage);
    }
}
