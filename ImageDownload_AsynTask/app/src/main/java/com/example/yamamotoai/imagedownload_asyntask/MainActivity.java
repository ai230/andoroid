package com.example.yamamotoai.imagedownload_asyntask;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText selectiontext;
    ListView chooseImageList;
    String[] listofImages;
    String[] listofUrls;
    ProgressBar downloadImageProgress;
    TextView textprogress;

    Resources res;
    private File dir;
    public File file;
//    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/imagedownload";
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dir = new File (path + "/folder/subfolder");
        //dir.mkdir();
        //file = new File(path + "/folder/subfolder/image.jpg");

        selectiontext = (EditText) findViewById(R.id.urlselectionText);
        chooseImageList = (ListView) findViewById(R.id.chooseImageList);
        downloadImageProgress = (ProgressBar) findViewById(R.id.downloadProgress);
        textprogress = (TextView) findViewById(R.id.textviewprogress);
        listofImages = getResources().getStringArray(R.array.imageuris);
        chooseImageList.setOnItemClickListener(this);

        res = getResources();
        listofImages = res.getStringArray(R.array.imageNames);
        listofUrls = res.getStringArray(R.array.imageuris);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listofImages);
        chooseImageList.setAdapter(arrayAdapter);
        chooseImageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("---", "onItemClick" + position);
                selectiontext.setText(listofUrls[position]);

                downloadImageProgress.setVisibility(View.GONE);
                textprogress.setVisibility(View.GONE);
            }
        });

    }
    public void downloadImage(View view) {
        final String imageURL = selectiontext.getText().toString();
        if (imageURL != null && (imageURL.length()) > 0) {

            Log.d("----","downloadImage");
            new DownloadTask().execute(imageURL);

        }
    }

//    @Override
//    public void run() {
//        Log.d("--saveImage","");
//        saveImage();
//    }

    class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

//        public DownloadTask(Context context){
//            this.context = context;
//        }


        @Override
        protected void onPreExecute() {

            Log.d("--onPreExecute","");
            downloadImageProgress.setVisibility(View.VISIBLE);
            textprogress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... sUrl) {
            Log.d("--doInBackground", String.valueOf(sUrl[0]));
            // Ceate an instance of URL, HttpURLConnection, InputStream, FileOutputStream,File class
            // Create a boolean variable successfull and set its intial value to false
            // if image download succesfully set it to true. return a boolean value of success
            // Write a code that download the image from internet
            // count how many bytes are downloded for that image and use this count to show the progress

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(sUrl[0]);
                Log.d("---","ok");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                String saveFileName = new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date());
                path = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/" + saveFileName;
                File file = new File(path);

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }
                Log.i("DownloadTask","Response " + connection.getResponseCode());

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();
                Log.d("---","fileLength:" + fileLength);
                Log.d("---","ok2");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                //TODO)why 4096?
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                Log.d("---","publishProgress()");

                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
                Log.d("---PATH",path);
//                Toast.makeText(MainActivity.this,path +" : ADDED YOUR FILE!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
                Log.d("---Error",e.toString());
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }

            return null;
        }

        int count;
        int val_progress;
        @Override
        protected void onProgressUpdate(Integer... progress) {
            Log.d("--onProgressUpdate",progress.toString());
            //calculate the progress and show it on progressbar
            count++;
            // calculate the percentage
            downloadImageProgress.setProgress(progress[0]);
            textprogress.setText("Updating..." + progress[0] + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            //set visibility of progessbar to gone
            Toast.makeText(MainActivity.this,"ALL ITEMS DOWNLOADED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectiontext.setText(listofImages[position]);
    }

    Bitmap bitMapImg;
    public void saveImage() {
        try {
            Log.d("---saveImage","");
//            Toast.makeText(MainActivity.this,"File", Toast.LENGTH_SHORT).show();
            FileOutputStream out = new FileOutputStream(file);

            bitMapImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

            Toast.makeText(getApplicationContext(), "File is Saved in  " + file, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("---ERROR in savefile",e.toString());
            e.printStackTrace();
        }

    }
}
