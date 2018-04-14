package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends Activity {
    private final static String TAG = "BroadcastService";
    private final int scoreIncrement = 10;

    private static boolean isQuestionsAdded = false;
    //detect if answer has already sent in order to decide whether we should...
    //...call still stop the counter service or not
    private static ArrayList<QuestionSet> questions;
    private TextView txtQuestion, txtScore, txtTime;
    private ImageView life1, life2, life3;
    private Button txtChoice1, txtChoice2, txtChoice3, txtChoice4;

    private static int currentCategory;

    private static int level = 1, score = 0, initiallives = 3, lives = initiallives;
    private static ArrayList<Integer> levelsLoaded = new ArrayList<>();
    private Intent intent;

    SoundPlayer soundPlayer;

    private boolean hasAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        soundPlayer = new SoundPlayer(this);

        int category = this.getIntent().getIntExtra("category", -1);

        if (category != -1){
            currentCategory = category;
        }

        if (!isQuestionsAdded){
            addQuestions();
            System.out.println("QUESTIONS ADDED! ");
            isQuestionsAdded = true;
        }

        txtQuestion = findViewById(R.id.txtQuestion);
        txtChoice1 = findViewById(R.id.choice1);
        txtChoice2 = findViewById(R.id.choice2);
        txtChoice3 = findViewById(R.id.choice3);
        txtChoice4 = findViewById(R.id.choice4);
        txtTime = findViewById(R.id.txtTime);
        txtScore = findViewById(R.id.txtScore);
        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);


        System.out.println("ONCREATE");
        intent = new Intent(this, BroadcastService.class);
        startService(intent);
        Log.i(TAG, "Started service");
        setFonts();
        System.out.println("LIVES: " + lives);

        loadListeners();

        initiateLives();

        Random random = new Random();
        int rand = random.nextInt(questions.size()) + 1;
        registerNewQuestion(rand);
    }

    private void addQuestions() {
        questions = new ArrayList<>();

        if (currentCategory == CATEGORIES.BREEDS_AND_BREEDING){

            questions.add(new QuestionSet("It is known for fast and efficient " +
                    "growth, reproductive efficiency, cleanness and meat " +
                    "flavor and value.what kind of breed is this?",
                    "Berkshire",
                    "Chester White",
                    "Hampshire",
                    1
            ));

            questions.add(new QuestionSet("Known as “America’s Sowherd.” Females are heavy milkers and often farrow large pigs.",
                    "Duroc",
                    "Landrace",
                    "Yorkshire",
                    3
            ));

            questions.add(new QuestionSet("These are white hogs with droopy, medium-sized ears are known for their mothering ability, durability and soundness.",
                    "Berkshire",
                    "Chester White",
                    "Hampshire",
                    2
            ));


            questions.add(new QuestionSet("The second-most recorded breed of swine in the United States. They are the red pigs with the drooping ears and are valued for their product quality, carcass yield, fast growth and lean-gain efficiency. ",
                    "Duroc",
                    "Landrace",
                    "Yorkshire",
                    1
            ));

            questions.add(new QuestionSet("They are found in almost every state, with the highest populations being in Illinois, Indiana, Iowa, Nebraska and Ohio. THey are known for their muscle, with a high proportion of lean meat and low backfat. ",
                    "Duroc",
                    "Landrace",
                    "Yorkshire",
                    3
            ));





        }else if (currentCategory == CATEGORIES.SWINE_PRODUCTIVITY_PHYSIOLOGY){

            questions.add(new QuestionSet("There are three stages of pre-forrowing period.arrange them from stage 1 to 3",
                    "The pre-farrowing period- The farrowing process- Delivery of the placenta",
                    "The farrowing process- The pre-farrowing period- Delivery of the placenta",
                    "Delivery of the placenta-- The farrowing process- The pre-farrowing period",
                    1
            ));

            questions.add(new QuestionSet("This is where the womb has just stopped contracting",
                    "Uterine inertia",
                    "Difficult presentation",
                    "Rotation of the horns of the womb",
                    1
            ));

            questions.add(new QuestionSet("This sometimes occurs when very large litters are present",
                    "Stimulating a piglet to breath ",
                    "Rotation of the horns of the womb ",
                    "Difficult presentations ",
                    2
            ));


            questions.add(new QuestionSet("4-5 days before birth, her ______ will begin to swell",
                    "Teeth",
                    "Milk",
                    "Vulva",
                    3
            ));

        }else if (currentCategory == CATEGORIES.PREGNANCY_PERIOD){

            questions.add(new QuestionSet("The gestation period for a female potbellied pig is?",
                    "105 to 110",
                    "112 to 115",
                    "120 to 130",
                    2
            ));

            questions.add(new QuestionSet("After the eggs are fertilised in the fallopian " +
                    "tubes, the embryos move around the two horns of the womb to become equally " +
                    "spaced.What method is this?",
                    "Daily observations for oestrus",
                    "Amplitude tests ",
                    "Döppler tests",
                    1
            ));

            questions.add(new QuestionSet("This technique involves the removal of a small piece of the vaginal mucous membrane using a special instrument",
                    "Serum analysis",
                    "Vaginal biopsy ",
                    "Ultra sound",
                    2
            ));

            questions.add(new QuestionSet("This can be carried out after day 22 by using a small styllette to puncture the ear vein.",
                    "Serum analysis",
                    "Vaginal biopsy ",
                    "Ultra sound",
                    1
            ));

        }else if (currentCategory == CATEGORIES.LACTATION_PERIOD){

            questions.add(new QuestionSet("This is the time when the pregnant gilt or sow enters the farrowing barn several days prior to the expected forrowing date",
                    "Assembly of the partners",
                    "Getting Going",
                    "Ejecting the product",
                    2
            ));

            questions.add(new QuestionSet("At the time of farrowing,the mammary gland undergoes how many stage?",
                    "2",
                    "5",
                    "3",
                    1
            ));

            questions.add(new QuestionSet("Milk ejection may be thought of as the climax of the sow-litter interplay?",
                    "Ejecting the product",
                    "Factors affecting the results",
                    1
            ));

            questions.add(new QuestionSet("When the sow starts farrowing each of her mammary glands has developed " +
                    "structurally and functionally to the point of producing some colostrum.What stage of lactation is this?",
                    "Getting going",
                    "Synchrony of the motion",
                    2
            ));

        }else if (currentCategory == CATEGORIES.FEEDING_MANAGEMENT){

            questions.add(new QuestionSet("This soybeans can also be fed to swine " +
                    "but only after they are heated (by extrusion or roasting) to inactivate " +
                    "the trypsin inhibitors and other heat-labile antinutritional factors.",
                    "Canola meal",
                    "Soybean meal",
                    "Cereal grains",
                    2
            ));

            questions.add(new QuestionSet("What is the major energy source for pigs?",
                    "Grain sorghum",
                    "Wheat ",
                    "Corn",
                    1
            ));

            questions.add(new QuestionSet("Pigs weaned at an early age of",
                    "3-4 Weeks",
                    "5-10 Weeks",
                    "7-8 Weeks",
                    1
            ));

            questions.add(new QuestionSet("This is the first milk rich in disease " +
                    "preventing immunoglobins and most important factor related to a " +
                    "piglets survival and longterm health. ",
                    "Crossfostering",
                    "Colostrum",
                    2
            ));


            questions.add(new QuestionSet("What is most effective way " +
                    "to reduce within litter piglet weight variation?",
                    "Crossfostering",
                    "Colostrum",
                    1
            ));


            questions.add(new QuestionSet("In Preventing Chilling in swine," +
                    " what temperature is “a hot one for the newborn piglets”?",
                    "30-45 °F",
                    "85- 95 °F",
                    "60-65 °F",
                    2
            ));

            questions.add(new QuestionSet("In Preventing Chilling in swine, what tem" +
                    "perature is “a cool one for the sow”?",
                    "60-65 °F",
                    "30-45 °F",
                    "85- 95 °F",
                    1
            ));



        }else if (currentCategory == CATEGORIES.HEALTH_AND_VACCINATION_PROGRAM_FOR_PREGNANT_SOW){

            questions.add(new QuestionSet("What part of the pig is preffered for injection into muscle?",
                    "Neck",
                    "Leg",
                    "Stomach",
                    1
            ));

            questions.add(new QuestionSet("Different vaccines can be given on the same day.",
                    "True",
                    "False",
                    1
            ));

            questions.add(new QuestionSet("Syringes must be sterelise by either boiling for _____.",
                    "1 hour",
                    "15 minutes",
                    "30 minutes",
                    2
            ));

            questions.add(new QuestionSet("This virus causes reproductive failure.",
                    "Leptospirosis",
                    "Porcine parvovirus",
                    "Weaner scours",
                    2
            ));

            questions.add(new QuestionSet("This causes fever, diamond skin disease, arthritis, abortion, heart disease and death.",
                    "Ersypelas",
                    "Weaner scours",
                    "Leptospirosis",
                    2
            ));





        }else if (currentCategory == CATEGORIES.BABY_PIG_MANAGEMENT){

            questions.add(new QuestionSet("The practice of self-feeding concentrates to young piglets in a separate enclosure away from their mother is known as?",
                    "Creep feeding",
                    "Feeding of growing and finishing pigs",
                    "Feeding of Farrowing Sow and Litter",
                    1
            ));

            questions.add(new QuestionSet("When a sow dies or fails to produce milk or does " +
                    "not claim her pigs, the piglings should be promptly shifted to a " +
                    "foster mother.What do they called this?",
                    "Orphan Pigs",
                    "Abandoned pigs",
                    "Pigs",
                    1
            ));


            questions.add(new QuestionSet("After farrowing, the _________ is finished serving " +
                    "its purpose of carrying nutrients into " +
                    "the fetus and transferring waste products out",
                    "Teeth clipping",
                    "Navel cord care",
                    "Thermal requirement",
                    2
            ));

            questions.add(new QuestionSet("Piglets are born with a pair " +
                    "of needle-sharp milk teeth at each of the front four corners of their jaws",
                    "Teeth clipping",
                    "Fostering",
                    "Thermal requirement",
                    1
            ));

            questions.add(new QuestionSet("What is the other term of low birth weight?",
                    "Anoxic",
                    "Immature",
                    "Defective",
                    2
            ));



        }else if (currentCategory == CATEGORIES.DISEASE_PREVENTION){


            questions.add(new QuestionSet("Causes stillbirths, high piglet mortality rates " +
                    "and abortions, and can cause non-reproductive illness in humans.What disease is this?",
                    "Porcine parvovirus",
                    "Leptospirosis",
                    "E. coli scours (Colibacillosis)",
                    2
            ));

            questions.add(new QuestionSet("Whether you can vaccinate or not you should protect your investment by applying appropriate ______ measures",
                    "Biosecurity",
                    "Iron supplement",
                    "Tai docking",
                    1
            ));


            questions.add(new QuestionSet("Should not feed ______ products unless they have been thoroughly cooked.",
                    "Pig meat",
                    "Chicken meat",
                    1
            ));



            questions.add(new QuestionSet("If some or all of your pig buildings are open sided or easy to access, they should be ring-fenced or walled off to protect your herd from stray animals, (particularly wild pigs), or human intruders entering your pig buildings",
                    "Physical barriers",
                    "Incoming pigs",
                    "Pig meat product",
                    1
            ));

        }







    }

    private void registerNewQuestion(int index) {
        System.out.println("INDEX: " + index);

        //if all the questions has been answered
        //end game
        if (levelsLoaded.size() >= questions.size()){
            endGame("winall");
            System.out.println("ALL QUESTIONS DONE");
        } else {

            //check if the question is not been answered previously
            if (!levelsLoaded.contains(index)){
                level = index;
                levelsLoaded.add(level);
                System.out.println("Generated new random question");
                System.out.println("SIZE: " + levelsLoaded.size());
            }
            //else if already answered, then change the question
            else {
                if (index < questions.size()){
                    //if already answered
                    //then increment value
                    index++;
                    registerNewQuestion(index);
                } else {
                    //if the index already exceeds with the number of questions
                    //then go back to start
                    index = 1;
                    registerNewQuestion(index);
                }
            }

        }

    }


    private void loadListeners() {
        txtChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer(1);
            }
        });txtChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer(2);
            }
        });txtChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer(3);
            }
        });txtChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer(4);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadQuestion(level);
        txtScore.setText("Score: " + String.valueOf(score));
    }

    private void loadQuestion(int level) {

        txtQuestion.setText(questions.get(level - 1).getQuestion());

        if (questions.get(level - 1).getChoice1() != null)
            txtChoice1.setText(questions.get(level - 1).getChoice1());

        if (questions.get(level - 1).getChoice2() != null)
            txtChoice2.setText(questions.get(level - 1).getChoice2());

        if (questions.get(level - 1).getChoice3() != null){
            txtChoice3.setText(questions.get(level - 1).getChoice3());
            txtChoice3.setVisibility(View.VISIBLE);
        }

        else txtChoice3.setVisibility(View.GONE);

        if (questions.get(level - 1).getChoice4() != null) {
            txtChoice4.setText(questions.get(level - 1).getChoice4());
            txtChoice4.setVisibility(View.VISIBLE);
        }
        else txtChoice4.setVisibility(View.GONE);


    }

    private void sendAnswer(int answer) {

        highlightUserAnswer(answer);
        int correctAnswer = questions.get(level - 1).getCorrectAnswer();
        System.out.println(answer);
        System.out.println(correctAnswer);

        if (!hasAnswered){

            hasAnswered = true;
            if (answer == correctAnswer){
                soundPlayer.playCorrect();
                winLevel(correctAnswer);
            } else {
                soundPlayer.playWrong();
                loseLevel(correctAnswer);
            }

        }

        stopService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Send Answer: Stopped service");
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Stop the service only if the application is in background
        if (Foreground.get().isBackground()){
            stopService(new Intent(this, BroadcastService.class));
            Log.i(TAG, "Game onDestroy: Stopped service");
        }


        System.out.println("ONDESTROY CALLED");
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
        detectState();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broadcast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            soundPlayer.playBeep();
            int secondsRemaining = BroadcastService.secondsRemaining;
            txtTime.setText(String.valueOf(secondsRemaining));
        }

        if (BroadcastService.secondsRemaining == 0){
            if (!hasAnswered){
                soundPlayer.playTime();
                hasAnswered = true;
                int correctAnswer = questions.get(level - 1).getCorrectAnswer();
                loseLevel(correctAnswer);

                stopService(new Intent(this, BroadcastService.class));
                Log.i(TAG, "updateGUI: Stopped service");
            }
        }
    }

    private void detectState(){

        if (BroadcastService.secondsRemaining == 0){
            int correctAnswer = questions.get(level - 1).getCorrectAnswer();
            loseLevel(correctAnswer);
            stopService(new Intent(this, BroadcastService.class));
            Log.i(TAG, "updateGUI: Stopped service");
        }

    }


    private void loseLevel(final int correctAnswer){
        lives--;
        showAnswer(true, correctAnswer);
        enableButtons(false);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showAnswer(false, correctAnswer);

                if (lives == 3) {
                    nextLevel("lose");
                } else if (lives == 2){
                    nextLevel("lose");
                    life3.setVisibility(View.GONE);
                } else if (lives == 1) {
                    nextLevel("lose");
                    life2.setVisibility(View.GONE);
                } else {
                    life1.setVisibility(View.GONE);
                    endGame("over");
                }

            }
        }, 1000);



    }

    private void winLevel(final int correctAnswer){
        showAnswer(true, correctAnswer);
        enableButtons(false);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showAnswer(false, correctAnswer);

                nextLevel("win");

            }
        }, 1000);


    }

    private void highlightUserAnswer(int ans){

        //get the padding of the button
        //to avoid scaling after changing the background
        int bottom = txtChoice1.getPaddingBottom();
        int top = txtChoice1.getPaddingTop();
        int right = txtChoice1.getPaddingRight();
        int left = txtChoice1.getPaddingLeft();

        switch (ans){
            case 1: txtChoice1.setBackgroundResource(R.drawable.bg_btn_active);
                txtChoice1.setPadding(left, top, right, bottom);
                break;
            case 2: txtChoice2.setBackgroundResource(R.drawable.bg_btn_active);
                txtChoice2.setPadding(left, top, right, bottom);
                break;
            case 3: txtChoice3.setBackgroundResource(R.drawable.bg_btn_active);
                txtChoice3.setPadding(left, top, right, bottom);
                break;
            case 4: txtChoice4.setBackgroundResource(R.drawable.bg_btn_active);
                txtChoice4.setPadding(left, top, right, bottom);
                break;
        }
    }

    private void showAnswer(boolean show, int correctAnswer) {

        //get the padding of the button
        //to avoid scaling after changing the background
        int bottom = txtChoice1.getPaddingBottom();
        int top = txtChoice1.getPaddingTop();
        int right = txtChoice1.getPaddingRight();
        int left = txtChoice1.getPaddingLeft();

        if (show){
            switch (correctAnswer){
                case 1: txtChoice1.setBackgroundResource(R.drawable.bg_btn_answer);
                    txtChoice1.setPadding(left, top, right, bottom);
                    break;
                case 2: txtChoice2.setBackgroundResource(R.drawable.bg_btn_answer);
                    txtChoice2.setPadding(left, top, right, bottom);
                    break;
                case 3: txtChoice3.setBackgroundResource(R.drawable.bg_btn_answer);
                    txtChoice3.setPadding(left, top, right, bottom);
                    break;
                case 4: txtChoice4.setBackgroundResource(R.drawable.bg_btn_answer);
                    txtChoice4.setPadding(left, top, right, bottom);
                    break;
            }
        } else {
            switch (correctAnswer){
                case 1: txtChoice1.setBackgroundResource(R.drawable.bg_btn);
                    txtChoice1.setPadding(left, top, right, bottom);
                    break;
                case 2: txtChoice2.setBackgroundResource(R.drawable.bg_btn);
                    txtChoice2.setPadding(left, top, right, bottom);
                    break;
                case 3: txtChoice3.setBackgroundResource(R.drawable.bg_btn);
                    txtChoice3.setPadding(left, top, right, bottom);
                    break;
                case 4: txtChoice4.setBackgroundResource(R.drawable.bg_btn);
                    txtChoice4.setPadding(left, top, right, bottom);
                    break;
            }
        }
    }

    private void enableButtons(boolean b) {
        txtChoice1.setEnabled(b);
        txtChoice2.setEnabled(b);
        txtChoice3.setEnabled(b);
        txtChoice4.setEnabled(b);
    }

    private void nextLevel(String result){

        if (result.equals("win")){
            String correctAnswer = questions.get(level - 1).getCorrectAnswerString();
            Intent intent = new Intent(this, Win.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("correctAnswer", correctAnswer);

            finish();
            startActivity(intent);
            overridePendingTransition(0,0);
            score += scoreIncrement;
        }
        else {
            String correctAnswer = questions.get(level - 1).getCorrectAnswerString();
            Intent intent = new Intent(this, Lose.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("correctAnswer", correctAnswer);

            finish();
            startActivity(intent);
            overridePendingTransition(0,0);

        }


    }

    private void endGame(String result){

        if (result.equals("over")){
            Intent intent = new Intent(this, Gameover.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("Score", score);
            startActivity(intent);
            overridePendingTransition(0,0);
        } else if (result.equals("winall")){
            Intent intent = new Intent(this, Winner.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("Score", score);
            startActivity(intent);
            overridePendingTransition(0,0);
        }

        lives = initiallives;
        score = 0;
        levelsLoaded.clear();
        questions.clear();
        isQuestionsAdded = false;
        stopService(new Intent(this, BroadcastService.class));
        finish();

    }




    private void setFonts(){
        TextView[] tv1 = {txtScore, txtChoice1, txtChoice2, txtChoice3, txtChoice4};
        TextView[] tv2 = {txtTime, txtQuestion};

        FontCustomizer fontCustomizer = new FontCustomizer(this);
        fontCustomizer.setToNexa(tv1);
        fontCustomizer.setToNexaBlack(tv2);
    }

    private void initiateLives(){
        if (lives == 3) {

        }

        if (lives == 2){
            life3.setVisibility(View.GONE);
        }
        if (lives == 1) {
            life3.setVisibility(View.GONE);
            life2.setVisibility(View.GONE);
        }

        if (lives <= 0)
        {
            life3.setVisibility(View.GONE);
            life2.setVisibility(View.GONE);
            life1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

    }


}
