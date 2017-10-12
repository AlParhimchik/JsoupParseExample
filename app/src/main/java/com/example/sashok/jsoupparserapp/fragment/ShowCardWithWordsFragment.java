package com.example.sashok.jsoupparserapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.sashok.jsoupparserapp.R;
import com.example.sashok.jsoupparserapp.adapter.CardWithWordsAdapter;
import com.example.sashok.jsoupparserapp.model.Folder;
import com.example.sashok.jsoupparserapp.model.Word;
import com.example.swipacardlibrary.view.Card;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 24.9.17.
 */

public class ShowCardWithWordsFragment extends AbsFragment  {
    private ArrayList<Word> al;
    private CardWithWordsAdapter arrayAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout parent_layout;
    private int folderID;
    public com.example.swipacardlibrary.view.Card swipeCardView;
    public static final String TAG = "TAG";
    public static final String BUNDLE_FOLDER_ID = "folder_id";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        al = new ArrayList<>();
        setDataList();
    }

    private void setDataList() {
        al.clear();
        if (getArguments() != null) {
            int folder_id = getArguments().getInt(BUNDLE_FOLDER_ID);
            parseFolder(folder_id);

        }
    }

    public void parseFolder(final int folderId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Folder> folders = new ArrayList<Folder>();
                    Document doc = Jsoup.connect("http://studynow.ru/5000englishwords/list&cat=" + folderId).get();
                    Element body = doc.body();
                    Element tbody_elems = body.getElementsByTag("script").get(5);
                    String body_str = tbody_elems.toString();
                    String str_to_find = "words[1]=new Word";
                    int pos = body_str.indexOf("words[1]=new Word");
                    body_str = body_str.substring(pos);
                    int pos_next;
                    String word_str = null;
                    final List<Word> words = new ArrayList<Word>();
                    do {
                        pos_next = body_str.indexOf(";words");
                        if (pos_next != -1) {
                            word_str = body_str.substring(0, pos_next);
                            body_str = body_str.substring(pos_next + 1, body_str.length());
                            String[] strings = word_str.split("'");
                            Word new_word = new Word();
                            new_word.setTranslation(strings[5]);
                            new_word.setTranscription(strings[3]);
                            new_word.setOriginal(strings[1]);
                            new_word.setExample(strings[9].replace("&rsquo;","'"));
                            new_word.setExapmleTranslate(strings[11]);
                            words.add(new_word);
                        }

                    } while (pos_next != -1);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onWordsRetrieved(words);

                        }
                    });
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();

    }

    public void onWordsRetrieved(List<Word> words) {
        al.clear();
        al.addAll(words);
        swipeCardView.onDataSetChanged();
        setVisibility();
    }


    public ShowCardWithWordsFragment() {

    }

    public static ShowCardWithWordsFragment newInstance(Bundle args) {

        ShowCardWithWordsFragment fragment = new ShowCardWithWordsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.word_card_fragment, container, false);
        swipeCardView = (Card) view.findViewById(R.id.card_view);
        //swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        arrayAdapter = new CardWithWordsAdapter(getContext(), al);
        swipeCardView.setAdapter(arrayAdapter);

        setVisibility();

        return view;

    }


    private void setVisibility() {
        if (al.size() == 0) {
//            swipeRefreshLayout.setRefreshing(true);
            swipeCardView.setVisibility(View.INVISIBLE);
        } else {
//            swipeRefreshLayout.setRefreshing(false);
            swipeCardView.setVisibility(View.VISIBLE);
        }
    }
}
