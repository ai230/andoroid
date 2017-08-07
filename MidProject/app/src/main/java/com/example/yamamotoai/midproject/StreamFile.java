package com.example.yamamotoai.midproject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by yamamotoai on 2017-08-05.
 */

public class StreamFile extends Activity{


    private void sampleFileOutput(){

//        try{
//            AssetManager assetManager =
//            getAssets().open("sample.txt");
//            File file =new File("text.txt");
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            FileWriter fw = new FileWriter(file,true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter pw = new PrintWriter(bw);           //This will add a new line to the file content
//            pw.println("");
//          /* Below three statements would add three
//           * mentioned Strings to the file in new lines.
//           */
//            pw.println("This is first line");
//            pw.println("This is the second line");
//            pw.println("This is third line");
//            pw.close();
//
//            System.out.println("Data successfully appended at the end of file");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void sampleFileInput() throws IOException {


        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String text = "";

        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                inputStream = getAssets().open("test.txt");
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // １行ずつ読み込み、改行を付加する
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    text += str + "\n";
                    Log.d("-----str",str);
                }
                Log.d("---text",text);
            } finally {
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null) bufferedReader.close();
            }
        } catch (Exception e){
            Log.d("---Exception", String.valueOf(e));
            e.getMessage();
        }
    }
}
