package com.example.common.network;

import android.util.Log;

import com.example.common.activity.BaseActivity;
import com.example.common.activity.Show;
import com.example.common.callback.ChapterCallBack;
import com.example.common.object.Book;
import com.example.common.object.Chapter;
import com.example.common.callback.BookListCallback;
import com.example.common.callback.ChapterListCallback;
import com.example.common.callback.StringCallBack;
import com.example.common.constant.URL;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";
    private static OkHttpClient client;

    public static OkHttpClient getOkHttpClient() {
        if (client == null) {
            synchronized (OkHttpUtil.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }

    public static void request(String url, StringCallBack callback) {
        /*if (!url.startsWith("https:")) {
            url = "https:" + url;
        }*/
        Request build = new Request.Builder()
                .url(url)
                .build();
        getOkHttpClient().newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String html = response.body().string();
                callback.string(html);
            }
        });
    }

    // 男生
    public static void boyList(BookListCallback callback) {
        request(URL.BOY_URL, html -> {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("#book-img-text > ul > li");
            ArrayList<Book> list = new ArrayList<>();
            for (Element e : elements) {
                String img = e.select("div.book-img-box > a > img").attr("src"); // 图片
                String title = e.select("div.book-mid-info > h2 > a").text();  // 书名
                String href = e.select("div.book-mid-info > h2 > a").attr("href");  // 书名
                String author = e.select("div.book-mid-info > p.author > a.name").text();  // 作者
                String intro = e.select("div.book-mid-info > p.intro").text();  // 简介
                String type = e.select("div.book-mid-info > p.author > a:nth-child(4)").text(); // 连载
                String type2 = e.select("div.book-mid-info > p.author > a.go-sub-type").text(); //
                String state = e.select("div.book-mid-info > p.author > span").text(); // 连载
                Book book = new Book();
                book.setImg("https:" + img);
                book.setTitle(title);
                book.setAuthor(author);
                book.setIntro(intro);
                book.setType(type);
                book.setType2(type2);
                book.setState(state);
                book.setHref("https:" + href);
                list.add(book);
                if (list.size() >= 3) {
                    break;
                }
            }
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(list));
        });
    }

    // 女生
    public static void girlList(BookListCallback callback) {
        request(URL.GIRL_URL, html -> {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("#book-img-text > ul > li");
            ArrayList<Book> list = new ArrayList<>();
            for (Element e : elements) {
                String img = e.select("div.book-img-box > a > img").attr("src"); // 图片
                String title = e.select("div.book-mid-info > h2 > a").text();  // 书名
                String href = e.select("div.book-mid-info > h2 > a").attr("href");  // 书名
                String author = e.select("div.book-mid-info > p.author > a.name").text();  // 作者
                String intro = e.select("div.book-mid-info > p.intro").text();  // 简介
                String type = e.select("div.book-mid-info > p.author > a:nth-child(4)").text(); // 连载
                String type2 = e.select("div.book-mid-info > p.author > a.go-sub-type").text(); //
                String state = e.select("div.book-mid-info > p.author > span").text(); // 连载
                Book book = new Book();
                book.setImg("https:" + img);
                book.setTitle(title);
                book.setAuthor(author);
                book.setIntro(intro);
                book.setType(type);
                book.setType2(type2);
                book.setState(state);
                book.setHref("https:" + href);
                list.add(book);
                if (list.size() >= 3) {
                    break;
                }
            }
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(list));
        });
    }

    // 目录
    public static void catalog(String url, ChapterListCallback callback) {
        request(url, html -> {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("#j-catalogWrap > div.volume-wrap > div:nth-child(1) > ul > li");
            //#j-catalogWrap > div.volume-wrap > div:nth-child(1) > ul > li:nth-child(1) > h2 > a
            ArrayList<Chapter> list = new ArrayList<>();
            for (Element e : elements) {
                String href = e.select("h2 > a").attr("href");  // 章节链接
                String title = e.select("h2 > a").text();  // 章节名
                Chapter chapter = new Chapter();
                chapter.setTitle(title);
                chapter.setHref("https:" + href);
                list.add(chapter);
                if (list.size() >= 20) {
                    break;
                }
            }
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(list));
        });
    }

    // 章节
    public static void chapter(String url, ChapterCallBack callback) {
        request(url, html -> {
            Document document = Jsoup.parse(html);
            String[] split = document.select("head > meta:nth-child(8)").attr("content").split("/");
            String id = split[split.length - 1];  // id
            String title = document.select("#chapter-" + id + " > div > div.text-head > h3 > span.content-wrap").text();
            Elements elements = document.select("#j_" + id + " > p");
            StringBuilder builder = new StringBuilder();
            for (Element e : elements) {
                String s = e.text();
                builder.append(s).append("\n\n");
            }
            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setContent(builder.toString());
            BaseActivity.getActivity().runOnUiThread(() -> callback.chapter(chapter));
        });
    }

    // 搜索
    public static void search(String title2, BookListCallback callback) {
        request(URL.SEARCH_URL + title2 + ".html", html -> {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("#result-list > div > ul > li");
            ArrayList<Book> list = new ArrayList<>();
            for (Element e : elements) {
                String img = e.select("div.book-img-box > a > img").attr("src");  // 图片
                String title = e.select("div.book-mid-info > h2 > a").text();  // 书名
                String author = e.select("div.book-mid-info > p.author > a.name").text();  // 作者
                String type = e.select("div.book-mid-info > p.author > a:nth-child(4)").text();  // 类型
                String state = e.select("div.book-mid-info > p.author > span").text();  // 状态
                String intro = e.select("div.book-mid-info > p.intro").text();  // 简介
                String href = e.select("div.book-mid-info > h2 > a").attr("href");  // 链接
                Book book = new Book();
                book.setImg("https:" + img);
                book.setTitle(title);
                book.setAuthor(author);
                book.setType(type);
                book.setState(state);
                book.setIntro(intro);
                book.setHref("https:" + href);
                list.add(book);
                if (list.size() >= 3) {
                    break;
                }
            }
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(list));
        });
    }
}
