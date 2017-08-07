package com.example.yamamotoai.flagquiz;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import android.util.Log;

/**
 * Created by yamamotoai on 2017-07-31.
 */

public class MainActivityFragment extends Fragment {
    private Handler handler;// In order to delay for the loading next quiz
    int questionCount = 1;

     private List<String> fileNameList; //For name of flag files
     private List<String> quizCountriesList; //For countries in current quiz
     private Set<String> regionsSet; //For world regions in current quiz
     public static int guessAns = 0;//How many times you answered

    //Create A int constant for total no of question in FLAGS_IN_QUIZ
    public static final int FLAGS_IN_QUIZ = 3;//Number of quiz

    private LinearLayout quizLinearLayout; //Layout that contains the quiz
    private TextView questionNumberTextView; //Shows current question #
    private ImageView flagImageView; //Displays a flag
    private LinearLayout[] guessLinearLayouts; //Rows of answer Buttons
    private TextView answerTextView; //Displays correct answer

    private int guessRows; // Number of rows displaying guess Buttons
    private SecureRandom random; // For randomize the countries
    private String correctAnswer; // Number of correct guesses

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        random = new SecureRandom();
        fileNameList = new ArrayList<>();
        quizCountriesList = new ArrayList<>();

        //Get references to GUI components
        quizLinearLayout = (LinearLayout)view.findViewById(R.id.quizLinearLayout);
        questionNumberTextView = (TextView)view.findViewById(R.id.questionNumberTextView);
        flagImageView = (ImageView)view.findViewById(R.id.flagImageView);
        guessLinearLayouts = new LinearLayout[4];
        guessLinearLayouts[0] = view.findViewById(R.id.row1LinearLayout);
        guessLinearLayouts[1] = view.findViewById(R.id.row2LinearLayout);
        guessLinearLayouts[2] = view.findViewById(R.id.row3LinearLayout);
        guessLinearLayouts[3] = view.findViewById(R.id.row4LinearLayout);
        answerTextView = (TextView) view.findViewById(R.id.answerTextView);

        handler = new Handler();
        return view;

    }

    public void upDateGuessRows(SharedPreferences sharedPreferences){
        //Get the number of guess buttons that should be displayed from setting the preference
        String choices = sharedPreferences.getString(MainActivity.CHOICES,null);
        guessRows = Integer.parseInt(choices)/2;

        // Hide all quess buttons in the LinearLayouts
        for (LinearLayout layout : guessLinearLayouts)
            layout.setVisibility(View.GONE);

        // Display appropriate guess button LinearLayouts
        for(int row = 0; row < guessRows; row++) {
            guessLinearLayouts[row].setVisibility(View.VISIBLE);
        }
    }

    //Getting resions from preference
    public void upDateRegions(SharedPreferences sharedPreferences) {
        //Set of countries
        regionsSet = sharedPreferences.getStringSet(MainActivity.REGIONS, null);
        if(regionsSet.size() == 0){
            regionsSet.add("Asia");
            Toast.makeText(getActivity().getApplicationContext(),R.string.default_region_message,Toast.LENGTH_SHORT).show();
        }
    }

    public void startQuiz(){
        int i=0;
        //Use AssetManager to get image files in this regions
        AssetManager assets = getActivity().getAssets();
        try {
            // Loop through each region
            for (String region : regionsSet) {
                // Get a list of all flag image files in this region
                String[] paths = assets.list(region);
                for (String path: paths)
                    fileNameList.add(path.replace(".png",""));
            }
        }catch (IOException exception){
            Log.e("IN FLAG QUIZ : ", "Error loading image file names", exception);
        }
        int flagCounter = 1;
        int numberOfFlags = fileNameList.size();
        // Add FLAGS_IN_QUIZ random file names to the quizCountriesList
        while (flagCounter <= FLAGS_IN_QUIZ) {
            int randomIndex = random.nextInt(numberOfFlags);
            // Get the random file name
            String filename = fileNameList.get(randomIndex);
            // If the region is enabled and it hasn't already been chosen
            if (!quizCountriesList.contains(filename)) {
                quizCountriesList.add(filename); // add the file to the list
                ++flagCounter;
            }
        }
        loadtheflag();
    }

    private void loadtheflag() {

        //setquestionNumberTextView's text
        questionNumberTextView.setText(getString(R.string.question,questionCount,FLAGS_IN_QUIZ));

        // Get file name of the next flag and remove it from the list
        String nextImage = quizCountriesList.remove(0);
        correctAnswer = nextImage; // Update the correct answer

        answerTextView.setText(""); // Clear answerTextView

        // Extract the region from the next image's name
        String region = nextImage.substring(0, nextImage.indexOf('-'));

        // Use AssetManager to load next image from assets folder
        AssetManager assets = getActivity().getAssets();

        // Get an InputStream to the asset representing the next flag
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

        // Put the correct answer at the end of fileNameList
        int correct = fileNameList.indexOf(correctAnswer);
        fileNameList.add(fileNameList.remove(correct));
        // Add 2, 4, 6 or 8 guess Buttons based on the value of guessRows
        for (int row = 0; row < guessRows; row++) {
            // Place Buttons in currentTableRow
            for (int column = 0;
                 column < guessLinearLayouts[row].getChildCount();
                 column++) {
                // Get reference to Button to configure
                Button newGuessButton =
                        (Button) guessLinearLayouts[row].getChildAt(column);
                newGuessButton.setEnabled(true);

                // Get country name and set it as newGuessButton's text
                String filename = fileNameList.get((row * 2) + column);
                newGuessButton.setText(getCountryName(filename));
                newGuessButton.setOnClickListener(guessButtonListener);
            }
        }

        // Randomly replace one Button with the correct answer
        int row = random.nextInt(guessRows); // Pick random row
        int column = random.nextInt(2); // Pick random column
        LinearLayout randomRow = guessLinearLayouts[row]; // Get the row
        String countryName = getCountryName(correctAnswer);
        ((Button) randomRow.getChildAt(column)).setText(countryName);
    }

    private View.OnClickListener guessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button guessButton = ((Button) v);
            String guess = ((Button) v).getText().toString();
            String answer = getCountryName(correctAnswer);
            ++guessAns;
            //If your guess is correct display correct answer in green text
            if(guess.equals(answer))
            {
                questionCount++;

                answerTextView.setText(answer + "!");
                //Set the text in green Color
                answerTextView.setTextColor(getResources().getColor(R.color.correct_answer,null));
                disableAllButtons();

                handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                if(questionCount > FLAGS_IN_QUIZ){
                                    doneQuiz();
                                }else{
                                    // Animation
                                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
                                    flagImageView.startAnimation(animation);
                                    loadtheflag();
                                }
                            }
                        },2000);
            }
            //If your answer is incorrect setText and textcolor will be red
            else
            {
                // Animation shake
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.incorrect_shake);
                animation.setRepeatCount(2);
                flagImageView.startAnimation(animation);
                answerTextView.setText(R.string.incorrect_answer);
                answerTextView.setTextColor(getResources().getColor(R.color.incorrect_answer,null));
                guessButton.setEnabled(false); // disable incorrect answer
            }
        }
    };

    public void doneQuiz(){
        showResultDialogAlert();
        answerTextView.setText(R.string.reset_quiz);
        answerTextView.setTextColor(Color.parseColor("#FF0000"));
    }
    //Utility method that disables all of answer Buttons first
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

    // Parses the country flag file name and returns the country name
    private String getCountryName(String name) {
        return name.substring(name.indexOf('-') + 1).replace('_', ' ');
    }

    private void showResultDialogAlert() {
        FragmentManager fm = getActivity().getFragmentManager();
        ResultDialogAlert resultDialogAlert = new ResultDialogAlert();
        resultDialogAlert.show(fm, "fragment_alert");
    }
}
