//package com.example.sashok.jsoupparserapp.helper;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//
//import com.example.sashok.jsoupparserapp.model.Answer;
//import com.example.sashok.jsoupparserapp.model.Folder;
//import com.example.sashok.jsoupparserapp.model.Word;
//import com.example.sashok.jsoupparserapp.model.photo.FlickrAnswer;
//import com.example.sashok.jsoupparserapp.model.photo.Photo;
//import com.example.sashok.jsoupparserapp.network.ApiClient;
//import com.example.sashok.jsoupparserapp.network.ApiInterface;
//import com.example.sashok.jsoupparserapp.network.PhotoApiClient;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by sashok on 2.10.17.
// */
//
//public class JsoupProvider {
//    public static  void getFolders(final Activity context, final FolderCallBack callBack){
//        List<Word> folders=new ArrayList<>();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    final List<Folder> folders = new ArrayList<Folder>();
//                    Document doc = Jsoup.connect("http://studynow.ru/5000englishwords").get();
//                    Element body = doc.body();
//                    Elements str = body.getElementsByTag("td");
//                    for (Element elements : str) {
//                        Elements a = elements.getElementsByTag("a");
//                        for (Element aa : a) {
//                            String text = aa.text();
//                            Folder folder = new Folder();
//                            int pos = text.indexOf(".");
//                            folder.setID(Integer.parseInt(text.substring(0, pos)));
//                            text = text.substring(pos + 1, text.length());
//                            String[] names = text.split(",");
//                            for (String name : names) {
//                                folder.set_rus_name(name);
//                            }
//
//                            folders.add(folder);
//                        }
//                    }
//                    folders.addAll(folders);
//                    translateFolders(folders,callBack,folders);
//                    context.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            callBack.onFoldersGetted(folders);
//                        }
//                    });
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    public static void setPhotoToFolder(final Folder folder, final int pos,int size) {
//        ApiInterface apiService =
//                PhotoApiClient.getClient().create(ApiInterface.class);
//
//        Call<FlickrAnswer> call = apiService.searchPhoto(folder.get_eng_names().get(0));
//        call.enqueue(new Callback<FlickrAnswer>() {
//            @Override
//            public void onResponse(Call<FlickrAnswer> call, Response<FlickrAnswer> response) {
//                Photo photo = response.body().getPhotos().getPhoto().get(0);
//                String url = "http://c1.staticflickr.com/" + photo.getFarm() + "/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + "_c.jpg";
//                folder.setFolderURL(url);
//                //FolderUtil.saveFolder(MainActivity.this, folder);
//             //   if (pos == size - 1) pushFolders.callback();;
//            }
//
//            @Override
//            public void onFailure(Call<FlickrAnswer> call, Throwable t) {
//                Log.i("TAG", "error");
//            }
//        });
//    }
//
//    public static void translateFolders(List<Folder> folders, FolderCallBack callBack, List<Folder> folderList) {
////        int cur_pos = 0;
////        for (Folder folder : folders) {
////            translateText(folder.get_rus_names().get(0), folder, cur_pos,folders.size());
////            cur_pos++;
////        }
//
//    }
//
//    private static void translateText(String text, final Folder folder, final int pos,final int size) {
//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<Answer> call = apiService.translateText("ru-en", "trnsl.1.1.20170331T164351Z.c8c530eb26574681.e18b8525868010f58a52240f5de443b1eed8d0e6", text);
//        call.enqueue(new Callback<Answer>() {
//            @Override
//            public void onResponse(Call<Answer> call, Response<Answer> response) {
//                Log.i("TAG", String.valueOf(pos));
//                folder.set_eng_names(response.body().getText());
//                setPhotoToFolder(folder, pos,size);
//
//            }
//
//            @Override
//            public void onFailure(Call<Answer> call, Throwable t) {
//                Log.i("TAG", "error");
//            }
//        });
//    }
//
//}
