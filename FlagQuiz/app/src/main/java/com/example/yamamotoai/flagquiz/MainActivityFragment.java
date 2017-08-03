package com.example.yamamotoai.flagquiz;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by yamamotoai on 2017-07-31.
 */

public class MainActivityFragment extends Fragment {
    private Handler handler;
    int questionCount = 1;


    //TODO 8) Create a list for quiz
     private List<String> fileNameList; //for flag file names
     private List<String> quizCountriesList; //for countries in current quiz
     private Set<String> regionsSet; //for world regions in current quiz
     public static int guessAns = 0;

    //Create A int constant for total no of question in FLAGS_IN_QUIZ
    public static final int FLAGS_IN_QUIZ = 3;//value = 6

    //TODO 9) CREATE a reference variable for all GUI componnets
    private LinearLayout quizLinearLayout; // layout that contains the quiz
    private TextView questionNumberTextView; // shows current question #
    private ImageView flagImageView; // displays a flag
    private LinearLayout[] guessLinearLayouts; // rows of answer Buttons
    private TextView answerTextView; // displays correct answer

    private int guessRows; // number of rows displaying guess Buttons
    private SecureRandom random; // used to randomize the quiz
    private String correctAnswer; // number of correct guesses

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //TODO 10) creating the instance of securerandom class.
        random = new SecureRandom();
        fileNameList = new ArrayList<>();
        quizCountriesList = new ArrayList<>();

        //TODO 11) get references to GUI components
        quizLinearLayout = (LinearLayout)view.findViewById(R.id.quizLinearLayout);
        questionNumberTextView = (TextView)view.findViewById(R.id.questionNumberTextView);
        flagImageView = (ImageView)view.findViewById(R.id.flagImageView);
        guessLinearLayouts = new LinearLayout[4];
        guessLinearLayouts[0] = view.findViewById(R.id.row1LinearLayout);
        guessLinearLayouts[1] = view.findViewById(R.id.row2LinearLayout);
        guessLinearLayouts[2] = view.findViewById(R.id.row3LinearLayout);
        guessLinearLayouts[3] = view.findViewById(R.id.row4LinearLayout);
        answerTextView = (TextView) view.findViewById(R.id.answerTextView);

//        //setquestionNumberTextView's text
//        questionNumberTextView.setText(getString(R.string.question,questionCount,FLAGS_IN_QUIZ));
        answerTextView.setText("!!!!!");
        handler = new Handler();

        return view;

    }

    // TODO 3) update guessRows based on value in SharedPreferences
    public void upDateGuessRows(SharedPreferences sharedPreferences){

        //get the number of guess buttons that should be displayed
        String choices = sharedPreferences.getString(MainActivity.CHOICES,null);
        guessRows = Integer.parseInt(choices)/2;

        // hide all quess button LinearLayouts
        for (LinearLayout layout : guessLinearLayouts)
            layout.setVisibility(View.GONE);

        // display appropriate guess button LinearLayouts
        for(int row = 0; row < guessRows; row++) {
            guessLinearLayouts[row].setVisibility(View.VISIBLE);
        }
    }
    // TODO 4) update world regions for quiz based on values in SharedPreferences
    public void upDateRegions(SharedPreferences sharedPreferences) {
        //set of countries
        regionsSet = sharedPreferences.getStringSet(MainActivity.REGIONS, null);
        for(String r : regionsSet){
            Log.d("---REGIONS: ",r);
        }

    }

    // TODO 12 ) set up and start the next quiz
    public void startQuiz(){
        int i=0;
        //use AssetManager to get image files in this regions
        AssetManager assets = getActivity().getAssets();
        try {
            // loop through each region
            for (String region : regionsSet) {
                // get a list of all flag image files in this region
                String[] paths = assets.list(region);
                for (String path: paths)
                    fileNameList.add(path.replace(".png",""));
            }
        }catch (IOException exception){
            Log.e("IN FLAG QUIZ : ", "Error loading image file names", exception);
        }
        int flagCounter = 1;
        int numberOfFlags = fileNameList.size();
        // add FLAGS_IN_QUIZ random file names to the quizCountriesList
        while (flagCounter <= FLAGS_IN_QUIZ) {
            int randomIndex = random.nextInt(numberOfFlags);
            // get the random file name
            String filename = fileNameList.get(randomIndex);
            // if the region is enabled and it hasn't already been chosen
            if (!quizCountriesList.contains(filename)) {
                quizCountriesList.add(filename); // add the file to the list
                ++flagCounter;
            }

        }
        loadtheflag();

    }
    // TODO 13) load the the flag
    private void loadtheflag() {

        //setquestionNumberTextView's text
        questionNumberTextView.setText(getString(R.string.question,questionCount,FLAGS_IN_QUIZ));

        // loadNextFlag(); // start the quiz by loading the first flag
        // get file name of the next flag and remove it from the list
        String nextImage = quizCountriesList.remove(0);
        Log.d("---nextImage: ",nextImage);
        correctAnswer = nextImage; // update the correct answer

        answerTextView.setText(""); // clear answerTextView

        // extract the region from the next image's name
        String region = nextImage.substring(0, nextImage.indexOf('-'));

        // use AssetManager to load next image from assets folder
        AssetManager assets = getActivity().getAssets();

        // get an InputStream to the asset representing the next flag
        // and try to use the InputStream
        try (InputStream stream =
                     assets.open(region + "/" + nextImage + ".png")) {
            // load the asset as a Drawable and display on the flagImageView
            Drawable flag = Drawable.createFromStream(stream, nextImage);
            flagImageView.setImageDrawable(flag);

        }
        catch (IOException exception) {
            Log.e("in the flag quiz ", "Error loading " + nextImage, exception);
        }

        Collections.shuffle(fileNameList); // shuffle file names

        // put the correct answer at the end of fileNameList
        int correct = fileNameList.indexOf(correctAnswer);
        fileNameList.add(fileNameList.remove(correct));
        // add 2, 4, 6 or 8 guess Buttons based on the value of guessRows
        for (int row = 0; row < guessRows; row++) {
            // place Buttons in currentTableRow
            for (int column = 0;
                 column < guessLinearLayouts[row].getChildCount();
                 column++) {
                // get reference to Button to configure
                Button newGuessButton =
                        (Button) guessLinearLayouts[row].getChildAt(column);
                newGuessButton.setEnabled(true);

                // get country name and set it as newGuessButton's text
                String filename = fileNameList.get((row * 2) + column);
                newGuessButton.setText(getCountryName(filename));
                newGuessButton.setOnClickListener(guessButtonListener);
            }
        }

        // randomly replace one Button with the correct answer
        int row = random.nextInt(guessRows); // pick random row
        int column = random.nextInt(2); // pick random column
        LinearLayout randomRow = guessLinearLayouts[row]; // get the row
        String countryName = getCountryName(correctAnswer);
        ((Button) randomRow.getChildAt(column)).setText(countryName);
    }

    private View.OnClickListener guessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button guessButton = ((Button) v);
            String guess = ((Button) v).getText().toString();
            String answer = getCountryName(correctAnswer);
            Log.d("---answer: ", answer);
            ++guessAns;
            //if the guess is correct
            // display correct answer in green text
            if(guess.equals(answer))
            {
                questionCount++;
                Log.d("---questionCount: ", String.valueOf(questionCount));

                answerTextView.setText(answer + "!");
                //set the text in green Color
                answerTextView.setTextColor(Color.parseColor("#00CC00"));
                disableAllButtons();

                handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                if(questionCount > FLAGS_IN_QUIZ){
                                    doneQuiz();
                                }else{
                                    // play shake
                                    Animation animation = AnimationUtils.loadAnimation(getContext(),
                                            R.anim.fade);
                                    flagImageView.startAnimation(animation);
                                    loadtheflag();
                                }
                            }
                        },2000);
            }
            else
            {

                // play shake
                Animation animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.incorrect_shake);
                animation.setRepeatCount(2);
                flagImageView.startAnimation(animation);
                // display "Incorrect!" in red
                answerTextView.setText(R.string.incorrect_answer);
                answerTextView.setTextColor(Color.parseColor("#FF0000"));
                //set the text in red Color
                guessButton.setEnabled(false); // disable incorrect answer
            }

        }
    };

    public void doneQuiz(){
        showResultDialogAlert();
        answerTextView.setText("Fin!");
        answerTextView.setTextColor(Color.parseColor("#FF0000"));
    }
    // utility method that disables all answer Buttons
    public void disableAllButtons()
    {
        for(int row=0;row<guessRows;row++)
        {
            LinearLayout guessRow = guessLinearLayouts[row];
            for(int i=0;i<guessRow.getChildCount();i++)
            {
                guessRow.getChildAt(i).setEnabled(false);
            }
        }
    }

    // parses the country flag file name and returns the country name
    private String getCountryName(String name) {
        return name.substring(name.indexOf('-') + 1).replace('_', ' ');
    }

    private void showResultDialogAlert() {
        FragmentManager fm = getActivity().getFragmentManager();
        ResultDialogAlert resultDialogAlert = new ResultDialogAlert();
        resultDialogAlert.show(fm, "fragment_alert");


    }

}
