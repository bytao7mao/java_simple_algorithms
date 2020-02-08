package com.taozen.quithabit;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anupcowkur.herebedragons.SideEffect;
import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.taozen.quithabit.cardActivities.AchievmentsActivity;
import com.taozen.quithabit.cardActivities.FailLogsActivity;
import com.taozen.quithabit.optionsMenuActivities.AboutActivity;
import com.taozen.quithabit.cardActivities.ChallengeActivity;
import com.taozen.quithabit.cardActivities.SavingsActivity;
import com.taozen.quithabit.utils.MyHttpManager;
import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.TransitionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

import static com.taozen.quithabit.utils.Constants.SharedPreferences.CHALLENGES_STRING;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.CLICKED;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.COUNTER;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.DAYOFPRESENT;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.HOUR_OF_FIRSLAUNCH_SP;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.INITIAL_CIGG_PER_DAY;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.LIFEREGAINED;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.MODIFIED_CIGG_PER_DAY;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.CLICKDAY_SP;
import static com.taozen.quithabit.utils.Constants.SharedPreferences.SAVINGS_FINAL;

public class TestBrainReboot extends AppCompatActivity {

    String arr = "";

    public static final String HTTPS_PYFLASKTAO_HEROKUAPP_COM_BOOKS = "https://pyflasktao.herokuapp.com/books";

    boolean higherThanOne;
    private List<MainActivity.MyAsyncTask> tasks;
    private Timer timer;
    Float lifeRegained;
//    private int lifeRegainedInteger;
    //dialogs for fabs - messages
    private String normalMessageForDialog = "\"Did you abstained to smoke today ?\"";
    private String moreThanOneDayPassedMessageForDialog = "Did you abstained to smoke in the last days ?";
    private String firstMessageDialog = "Hello, this is your first day!\nSince you're here " +
            "it means that you made the first step in order to get rid of your habit";

    //TODO - ask user how many cigarettes smokes per day with dialog
    private int cigarettesPerDay;

    //Views
    @BindView(android.R.id.content) View parentLayout;
    //Fab
    @BindView(R.id.fab) FloatingActionButton fab;
    //CardViews
    @BindView(R.id.progressCardIdProgress) CardView progressCardView;
    @BindView(R.id.progressCardIdSavings) CardView savingsCardView;
    @BindView(R.id.progressCardIdChallenge) CardView challengeCardView;
    @BindView(R.id.progressCardIdLogs) CardView timeStampLogsCardview;
    @BindView(R.id.card_view_mainID) CardView cardViewMain;
    @BindView(R.id.progressCardIdAchievments) CardView achievementRanksCard;
    @BindView(R.id.progressCardId) CardView upperProgressPercentsCard;

    //TextViews
//    @BindView(R.id.rankFourIdText) TextView rankFourTxt;
//    @BindView(R.id.rankThreeIdText) TextView rankThreeTxt;
//    @BindView(R.id.rankTwoIdText) TextView rankTwoTxt;
//    @BindView(R.id.rankOneIdText) TextView rankOneTxt;
    @BindView(R.id.rank_master) TextView rank_masterTxt;
    @BindView(R.id.toolbar_subtitle) TextView subTextToolbar;
    @BindView(R.id.counterTextId) TextView counterText;
    @BindView(R.id.txtProgressIdForGums) TextView txtProgressForGums;
    @BindView(R.id.txtProgressIdForBreath) TextView txtProgressForBreath;
    @BindView(R.id.txtProgressIdForFatigue) TextView txtProgressForFatigue;
    @BindView(R.id.txtProgressIdForEnergy) TextView txtProgressForEnergyLevels;
    @BindView(R.id.targetTxtViewId) TextView targetTxtViewId;
    @BindView(R.id.moneyortimeId) TextView moneyOrTimeTextView;
    @BindView(R.id.remaining_days_Id) TextView remainingDaysTxt;
    @BindView(R.id.tipofthedayTxtViewId) TextView tipofthedayTxtView;
    @BindView(R.id.progressActivityId) TextView progressBarsTxt;
    @BindView(R.id.logsTxtId) TextView failLogsTxtView;
    @BindView(R.id.challengeTxtIdTitleId) TextView challengeTextViewTitle;
    @BindView(R.id.challengeTextId) TextView challengeTextViewSubtitle;
    @BindView(R.id.tvErrorId) TextView errorText;
    @BindView(R.id.textNonSmokerId) TextView textNonSmoker;
    @BindView(R.id.subTextSmokeId) TextView subTextNonSmoker;
    @BindView(R.id.subTextEnergyId) TextView subTextEnergy;
    @BindView(R.id.subTextBreathId) TextView subTextBreath;
    @BindView(R.id.subTextFatigueId) TextView subTextFatigue;
    @BindView(R.id.subTextGumsId) TextView subTextGums;
    @BindView(R.id.YourAchievmentsId) TextView yourAchievmentTxt;
    @BindView(R.id.YourProgressId) TextView yourProgressTxt;
    @BindView(R.id.YourSavingsId) TextView yourSavingsTxt;
    @BindView(R.id.YourLogsId) TextView yourLogsTxt;
    @BindView(R.id.YourProgressIdCigaretes) TextView userCigaretesProgressTxt;
    @BindView(R.id.YourProgressIdRank) TextView userRankProgressTxt;
    @BindView(R.id.YourProgressIdHours) TextView userHoursProgressTxt;
    //ProgressBar
    @BindView(R.id.loadingProgressId) ProgressBar progressBarLoading;
    @BindView(R.id.loadingProgressId2) ProgressBar progressBarLoading2;
    //ImageViews
    @BindView(R.id.counterImageId) ImageView counterImgView;
    @BindView(R.id.rankOneId) ImageView rankOneImg;
    @BindView(R.id.rankTwoId) ImageView rankTwoImg;
    @BindView(R.id.rankThreeId) ImageView rankThreeImg;
    @BindView(R.id.rankFourId) ImageView rankFourImg;
    @BindView(R.id.backgroundId) ImageView backgroundImgWall;
//  @BindView(R.id.imageViewMiddleId) ImageView imageViewMiddle;
    @BindView(R.id.pulsator) PulsatorLayout pulsator;

    //firstStart bool
    boolean isFirstStart;
    //counter for user
    private int counter;
    private long savings = 0;
    private int DAY_OF_CLICK = 0,
            DAY_OF_PRESENT = 0,
            HOUR_OF_DAYLIGHT = 0,
            HOUR_OF_FIRSTLAUNCH = 0;
    //wil start from 30 to 60 to 90
    private int userMaxCountForHabit = -1;
    //default false
    private boolean buttonClickedToday;
    //Toolbar
    private Toolbar toolbar;
    //Calendar
    private Calendar calendarOnClick,
            calendarForProgress;
    //shared pref
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    //circular progressbar
    private CircularProgressBar progressBarEnergyLevel,
            progressBarGumsLevel,
            progressBarFatigueLevel,
            progressBarBreathlevel;
    private DisplayMetrics metrics = new DisplayMetrics();
    private Configuration config;
    private String challs;
    private StringBuilder strBuilder = new StringBuilder();

    //fonts
    static Typeface montSerratBoldTypeface;
    static Typeface montSerratItallicTypeface;
    static Typeface montSerratLightTypeface;
    static Typeface montSerratMediumTypeface;
    static Typeface montSerratSemiBoldTypeface;
    static Typeface montSerratExtraBoldTypeface;
    static Typeface montSerratSimpleBoldTypeface;

    DecimalFormat numberFormat;
    ObjectAnimator anim,anim2;
    int i = 1;

    MainActivity.MyAsyncTask task;

//    public static void main(String[] args) {
//        DecimalFormat a = new DecimalFormat("#.##");
//        if (a instanceof DecimalFormat) {
//            System.out.println("yes it is object animator");
//        }
//    }


    //OnCreate [START]
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        //shared pref
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        editor = preferences.edit();
        startFirstActivity();
        Intent intent = getIntent();
        String name = intent.getStringExtra("data");
        tipofthedayTxtView.setText(name);

        numberFormat = new DecimalFormat("#.##");

        //anim for subtext
        anim = ObjectAnimator.ofInt(subTextNonSmoker,
                "TextColor",
                Color.WHITE, getResources().getColor(R.color.greish),
                getResources().getColor(R.color.greish));
        anim.setDuration(1200);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(Animation.INFINITE);
        //anim for counter
        anim2 = ObjectAnimator.ofInt(counterText,
                "TextColor",
                Color.WHITE, getResources().getColor(R.color.colorPrimary),
                Color.WHITE);
        anim2.setDuration(1500);
        anim2.setEvaluator(new ArgbEvaluator());
        anim2.setRepeatMode(ValueAnimator.REVERSE);
        anim2.setRepeatCount(Animation.INFINITE);


        //testing area
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        HOUR_OF_DAYLIGHT = calendar.get(Calendar.HOUR_OF_DAY);
        setTheHourOfFirstLaunch(calendar);
        setBackgroundForDaylightOrNight();
        tasks = new ArrayList<>();
        config = getResources().getConfiguration();
        //set text for checkin
        setCheckInText();

        //color of the FAB - NOW IS already changed in XML
        //fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //CONDITION TO SET TARGET TEXT AFTER CHECKINNG COUNTER
        if (preferences.contains(COUNTER)){ counter = preferences.getInt(COUNTER, -1);counterText.setText(String.valueOf(counter)); }
        if (preferences.contains(INITIAL_CIGG_PER_DAY)){cigarettesPerDay = preferences.getInt(INITIAL_CIGG_PER_DAY, 0);}
        if (preferences.contains(LIFEREGAINED)){ lifeRegained = preferences.getFloat(LIFEREGAINED, 0); }
        setTargetDays();
        firstCheckMax();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));
        progressBarLoading.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        progressBarLoading2.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//      getSupportActionBar().setElevation(0); //remove shadow - but now it is already removed in xml file

        progressCardView.setCardElevation(0);
        savingsCardView.setCardElevation(0);
        timeStampLogsCardview.setCardElevation(0);
        cardViewMain.setCardElevation(0);
        achievementRanksCard.setCardElevation(0);
        challengeCardView.setCardElevation(0);
        upperProgressPercentsCard.setCardElevation(0);

        //progress for percent - this is a circular bar
        progressBarEnergyLevel = findViewById(R.id.progress_bar_energy);
        progressBarFatigueLevel = findViewById(R.id.progress_bar_fatigue);
        progressBarBreathlevel = findViewById(R.id.progress_bar_breath);
        progressBarGumsLevel = findViewById(R.id.progress_bar_gums);

        //setMargin
        setMarginForProgress();

        //check online state
//        checkActivityOnline();
        setTxtViewForUserMaxCountDaysOnStringVersion(
                String.valueOf(userMaxCountForHabit),
                R.string.target_string, targetTxtViewId);

        montSerratBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Black.ttf");
        montSerratItallicTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Italic.ttf");
        montSerratLightTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        montSerratMediumTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");
        montSerratSemiBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        montSerratExtraBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-ExtraBold.ttf");
        montSerratSimpleBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");

        counterText.setTypeface(montSerratBoldTypeface);
//        targetTxtViewId.setTypeface(montSerratMediumTypeface);
//        remainingDaysTxt.setTypeface(montSerratMediumTypeface);
        remainingDaysTxt.setTypeface(montSerratSimpleBoldTypeface);
        targetTxtViewId.setTypeface(montSerratSimpleBoldTypeface);
        tipofthedayTxtView.setTypeface(montSerratItallicTypeface);
        txtProgressForEnergyLevels.setTypeface(montSerratBoldTypeface);
        txtProgressForFatigue.setTypeface(montSerratBoldTypeface);
        txtProgressForBreath.setTypeface(montSerratBoldTypeface);
        txtProgressForGums.setTypeface(montSerratBoldTypeface);
        moneyOrTimeTextView.setTypeface(montSerratSimpleBoldTypeface);
        challengeTextViewTitle.setTypeface(montSerratSimpleBoldTypeface);
        challengeTextViewSubtitle.setTypeface(montSerratLightTypeface);
        progressBarsTxt.setTypeface(montSerratBoldTypeface);
        failLogsTxtView.setTypeface(montSerratLightTypeface);
        textNonSmoker.setTypeface(montSerratBoldTypeface);
        subTextEnergy.setTypeface(montSerratMediumTypeface);
        subTextBreath.setTypeface(montSerratMediumTypeface);
        subTextFatigue.setTypeface(montSerratMediumTypeface);
        subTextGums.setTypeface(montSerratMediumTypeface);
        yourAchievmentTxt.setTypeface(montSerratSimpleBoldTypeface);
        yourProgressTxt.setTypeface(montSerratSimpleBoldTypeface);
        yourSavingsTxt.setTypeface(montSerratSimpleBoldTypeface);
        yourLogsTxt.setTypeface(montSerratSimpleBoldTypeface);
        userCigaretesProgressTxt.setTypeface(montSerratLightTypeface);
        userRankProgressTxt.setTypeface(montSerratLightTypeface);
        userHoursProgressTxt.setTypeface(montSerratLightTypeface);
        subTextNonSmoker.setTypeface(montSerratMediumTypeface);
        subTextToolbar.setTypeface(montSerratSemiBoldTypeface);
        rank_masterTxt.setTypeface(montSerratMediumTypeface);
//        rankFourTxt.setTypeface(montSerratMediumTypeface);
//        rankThreeTxt.setTypeface(montSerratMediumTypeface);
//        rankTwoTxt.setTypeface(montSerratMediumTypeface);
//        rankOneTxt.setTypeface(montSerratMediumTypeface);

        int tvWidth = subTextNonSmoker.getWidth();
        int tvHeight = subTextNonSmoker.getHeight();

        float density = getResources().getDisplayMetrics().density;
        float densityWidth = tvWidth / density;
        float densityHeight = tvHeight / density;
        Log.d("DENS", "widht: " + densityWidth + " height: " + densityHeight + density);

        try {
            if (preferences.contains(COUNTER)){
                counter = preferences.getInt(COUNTER, -1);
            }
            //setting the achievments images for user
            showEntireProgressForUserCard(userCigaretesProgressTxt, userRankProgressTxt, userHoursProgressTxt);
            setImagesForAchievementCard();
            setImprovementProgressLevels();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        achievementRanksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AchievmentsActivity.class);
                startActivity(intent);
            }
        });//achievementRanksCard[END]
        timeStampLogsCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: finish logs activity
                Intent intent = new Intent(MainActivity.this, FailLogsActivity.class);
                startActivity(intent);
            }
        });//timeStampCardView[END]
        savingsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SavingsActivity.class);
                intent.putExtra(SAVINGS_FINAL, savings);
                startActivity(intent);
            }
        });//savingsCardView[END]
        challengeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(intent);
                challs = "Tap to see your progress for your challenge!";
                editor.putString(CHALLENGES_STRING, challs);
                editor.apply();
            }
        });//challengeCardView[END]

        //retrieving the counter and minute values
        try {
            //run the task
            runningInBackground();
            //counter on click for fab button
            counterFabButtonInitializer();
            setTargetDays();

            moneyOrTimeAndGetAndSetValue();
            if (preferences.contains(COUNTER)){
                counter = preferences.getInt(COUNTER, -1);
            }
            if (preferences.contains(CLICKED)){
                buttonClickedToday = preferences.getBoolean(CLICKED, false);
            }
            setImprovementProgressLevels();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }//[END OF RETRIEVING VALUES]

        //milestone dialog ------------
//        AlertDialog.Builder milestoneAlert = new AlertDialog.Builder(this);
//        final EditText editTextForMilestone = new EditText(MainActivity.this);
//        milestoneAlert.setMessage("Set your milestone ?");
//        milestoneAlert.setTitle("Milestone!");
//        milestoneAlert.setView(editTextForMilestone);
//        milestoneAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //What ever you want to do with the value
////                Editable milestone = editTextForMilestone.getText();
//                String getMilestone = editTextForMilestone.getText().toString();
//                userMaxCount = Integer.parseInt(getMilestone);
//
//            }
//        });
//        milestoneAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //do nothing
//            }
//        });
//        milestoneAlert.show();

        //choose your habit ------------
//        AlertDialog.Builder habitAlert = new AlertDialog.Builder(this);
//        final EditText editTextForChoosingHabit = new EditText(MainActivity.this);
//        habitAlert.setMessage("Type your habit ?");
//        habitAlert.setTitle("Habit!");
//        habitAlert.setView(editTextForChoosingHabit);
//        habitAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //What ever you want to do with the value
////                Editable habit = editTextForChoosingHabit.getText();
//                habitString = editTextForChoosingHabit.getText().toString();
//
//            }
//        });
//        habitAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //do nothing
//            }
//        });
//        habitAlert.show();

//        //dialog ------------
//        new BottomDialog.Builder(this)
//                .setTitle("Awesome!")
//                .setContent("What can we improve? Your feedback is always welcome.")
//                .setPositiveText("OK")
//                .setPositiveBackgroundColorResource(R.color.colorPrimary)
//                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
//                .setPositiveTextColorResource(android.R.color.white)
//                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
//                .onPositive(new BottomDialog.ButtonCallback() {
//                    @Override
//                    public void onClick(BottomDialog dialog) {
//                        Log.d("BottomDialogs", "Do something!");
//                    }
//                }).show();
        //fancy dialog gif ------------
//        new FancyGifDialog.Builder(this)
//                .setTitle("Granny eating chocolate dialog box")
//                .setMessage("This is a granny eating chocolate dialog box. This library is used to help you easily create fancy gify dialog.")
//                .setNegativeBtnText("Cancel")
//                .setPositiveBtnBackground("#FF4081")
//                .setPositiveBtnText("Ok")
//                .setNegativeBtnBackground("#FFA9A7A8")
//                .setGifResource(R.drawable.braingif)   //Pass your Gif here
//                .isCancellable(true)
//                .OnPositiveClicked(new FancyGifDialogListener() {
//                    @Override
//                    public void OnClick() {
//                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .OnNegativeClicked(new FancyGifDialogListener() {
//                    @Override
//                    public void OnClick() {
//                        Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();
    }//[END OF ONCREATE]

    private void setTheHourOfFirstLaunch(Calendar calendar) {
        //my personal method to save a value and keep it every time i launch on create :)
        if (preferences.contains(HOUR_OF_FIRSLAUNCH_SP)) {
            HOUR_OF_FIRSTLAUNCH = preferences.getInt(HOUR_OF_FIRSLAUNCH_SP, -1);
            Log.d("TAOZEN1", "share prefs contains: firsthour = " + HOUR_OF_FIRSTLAUNCH);
        } else {
            Log.d("TAOZEN1", "share prefs DOES NOT contains: firsthour");
            HOUR_OF_FIRSTLAUNCH = calendar.get(Calendar.HOUR_OF_DAY);
            editor.putInt(HOUR_OF_FIRSLAUNCH_SP, HOUR_OF_FIRSTLAUNCH);
            editor.apply();
            strBuilder.append(String.format(getString(R.string.checkinStr), HOUR_OF_FIRSTLAUNCH));
            strBuilder.append("\nWe will start tutorial now.");
//            showCustomDialogOnFirstLaunch("Welcome", strBuilder);
        }
    }

    @SideEffect
    private void setBackgroundForDaylightOrNight() {
        //change wallpaper during nighttime
        if (HOUR_OF_DAYLIGHT <= 6 || HOUR_OF_DAYLIGHT >= 22) {
//        if (HOUR_OF_DAYLIGHT >= 6 && HOUR_OF_DAYLIGHT <= 20) {
            backgroundImgWall.setBackgroundResource(R.drawable.ppp);
            tipofthedayTxtView.setTextColor(getResources().getColor(R.color.white));
            tipofthedayTxtView.setAlpha(0.8f);
            counterText.setTextColor(getResources().getColor(R.color.white));
            counterText.setAlpha(1.0f);
            remainingDaysTxt.setTextColor(getResources().getColor(R.color.white));
            remainingDaysTxt.setAlpha(0.8f);
            targetTxtViewId.setTextColor(getResources().getColor(R.color.white));
            targetTxtViewId.setAlpha(0.8f);
            textNonSmoker.setTextColor(getResources().getColor(R.color.white));
            textNonSmoker.setAlpha(1.0f);
//            subTextNonSmoker.setTextColor(getResources().getColor(R.color.greish));
            subTextNonSmoker.setBackground(getResources().getDrawable(R.drawable.custom_button_round));
            subTextNonSmoker.setAlpha(1.0f);
            backgroundImgWall.setAlpha(1.0f);
        } else {
            //change wallpaper during daytime
            backgroundImgWall.setBackgroundResource(R.drawable.bgday);
            tipofthedayTxtView.setTextColor(getResources().getColor(R.color.greish));
            counterText.setTextColor(getResources().getColor(R.color.greish));
            remainingDaysTxt.setTextColor(getResources().getColor(R.color.greish));
            targetTxtViewId.setTextColor(getResources().getColor(R.color.greish));
            textNonSmoker.setTextColor(getResources().getColor(R.color.greish));
            textNonSmoker.setAlpha(0.8f);
//            subTextNonSmoker.setTextColor(getResources().getColor(R.color.greish));
            subTextNonSmoker.setBackground(getResources().getDrawable(R.drawable.custom_button_round));
            subTextNonSmoker.setAlpha(0.7f);
//            backgroundImgWall.setAlpha(0.05f);
            backgroundImgWall.setAlpha(0f);
        }
    }

    private void firstCheckMax() {
        if (userMaxCountForHabit == -1) {
            userMaxCountForHabit = 30;
            editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);
            editor.apply();
        } else {
            userMaxCountForHabit = preferences.getInt(getString(R.string.maxCounter), -1);
        }
    }

    //dialog when user pass a day
    private void positiveDialogAfterPassDay() {
        //dialog ------------
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setPositiveText("OK")
                .setPositiveBackgroundColorResource(R.color.colorPrimary)
                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                .setPositiveTextColorResource(android.R.color.white)
                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        //call on destroy
//                        finish();
                        Log.d("BottomDialogs", "Do something!");
                    }
                }).show();
    }
//    //dialog when user pass a day
//    private void showCustomDialogOnFirstLaunch(String title, StringBuilder content){
//        //dialog ------------
//        new BottomDialog.Builder(this)
//                .setTitle(title)
//                .setContent(content)
//                .setPositiveText("OK")
//                .setCancelable(false)
//                .setPositiveBackgroundColorResource(R.color.colorPrimary)
//                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
//                .setPositiveTextColorResource(android.R.color.white)
//                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
//                .onPositive(new BottomDialog.ButtonCallback() {
//                    @Override
//                    public void onClick(BottomDialog dialog) {
//                        Log.d("BottomDialogs", "Do something!");
//                        //intro activity check in a separate thread
////                        startIntroActivity();
////                        showDialogForSavingSum();
//                    }
//                }).show();
//    }
    //dialog when user pass a day
    private void negativeDialogAfterRelapse() {
        //dialog ------------
        new BottomDialog.Builder(this)
                .setTitle("It's ok to fail!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setPositiveText("OK")
                .setCancelable(false)
                .setPositiveBackgroundColorResource(R.color.colorPrimary)
                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                .setPositiveTextColorResource(android.R.color.white)
                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("BottomDialogs", "Do something!");
                    }
                }).show();
    }

//    @SideEffect
//    private void startIntroActivity() {
//        //intro
//        //code for INTRO
//        Thread threadForSlider = new Thread(new Runnable() {
//            @Override
//            public void run() {
////                //  Create a new boolean and preference and set it to true
////                Log.d("taozenD", "thread separat: " + Thread.currentThread().getName());
////                if (preferences.contains("firstStart")) {
////                    isFirstStart = preferences.getBoolean("firstStart", false);
////                } else {
////                    //on first launch this will trigger
////                    isFirstStart = true;
////                    editor.putBoolean("firstStart", false);
////                    editor.apply();
////                }
////                //  If the activity has never started before...
//                if (isFirstStart) {
////                    if (preferences.contains(COUNTER)) {
////                        counter = preferences.getInt(COUNTER, -1);
////                        editor.putInt(COUNTER, counter);
////                        editor.apply();
////                    }
//                    //  Launch app intro
//                    final Intent i = new Intent(MainActivity.this, IntroActivity.class);
//                    runOnUiThread(new Runnable() {
//                        @Override public void run() {
//                            Log.d("taozenD", "thread din ui: " + Thread.currentThread().getName());
//                            startActivity(i);
//                        }
//                    });
//                }
//            }
//        });
//        threadForSlider.start();
//    }//end of INTRO

    @SideEffect
    private void startFirstActivity() {
        //intro
        //code for INTRO
        Thread threadForSlider = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Create a new boolean and preference and set it to true
                Log.d("taozenD", "thread separat: " + Thread.currentThread().getName());
                if (preferences.contains("firstStart")) {
                    isFirstStart = preferences.getBoolean("firstStart", false);
                } else {
                    //on first launch this will trigger
                    isFirstStart = true;
                    editor.putBoolean("firstStart", false);
                    editor.apply();
                    //fake first day of user to be day of present -1 to enable him/her to check in for the first time
                    //[calendar area]
                    calendarForProgress = Calendar.getInstance();
                    calendarForProgress.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                    DAY_OF_PRESENT = calendarForProgress.get(Calendar.DAY_OF_YEAR);
                    DAY_OF_CLICK = DAY_OF_PRESENT - 1;
                    editor.putInt(CLICKDAY_SP, DAY_OF_CLICK);
                    editor.apply();
                    Log.d("DAYOFTAOZEN", DAY_OF_CLICK + " ");
                }
                //  If the activity has never started before...
                if (isFirstStart) {
                    if (preferences.contains(COUNTER)) {
                        counter = preferences.getInt(COUNTER, -1);
                        editor.putInt(COUNTER, counter);
                        editor.apply();
                    }
                    //  Launch app intro
                    final Intent i = new Intent(MainActivity.this, FirstScreenActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            Log.d("taozenD", "thread din ui: " + Thread.currentThread().getName());
                            startActivity(i);
//                            startIntroActivity();
                        }
                    });
                }
            }
        });
        threadForSlider.start();
    }//end of INTRO

    @SideEffect
    private void counterFabButtonInitializer() {
        //active when user passed a day
        //inactive when user wait
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (preferences.contains(COUNTER)){
                        counter = preferences.getInt(COUNTER, -1);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                buttonClickedToday = true;
                editor.putBoolean(CLICKED, buttonClickedToday);
                editor.apply();
                cigarettesPerDay = preferences.getInt(INITIAL_CIGG_PER_DAY, 0);
                //[calendar area]
                calendarOnClick = Calendar.getInstance();
                calendarOnClick.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                DAY_OF_CLICK = calendarOnClick.get(Calendar.DAY_OF_YEAR);
                editor.putInt(CLICKDAY_SP, DAY_OF_CLICK);
                editor.apply();
                setImagesForAchievementCard();
                Log.d("INTROTAO", "counter from onclick = " + counter + buttonClickedToday);
                String messageForDialog = "";
                messageForDialog = higherThanOne ? moreThanOneDayPassedMessageForDialog : normalMessageForDialog;
                //between 1 and 29
                if (counter == 0) {
                    normalFancyDialog("WELCOME TO QUIT HABIT!", firstMessageDialog);
                } else if (counter > 0 && counter < 29) {
                    normalFancyDialog("BEAT YOUR MILESTONE - 30 DAYS!", messageForDialog);
                    //between 29(to show up in 30) and 60
                } else if (counter > 28 && counter < 59) {
                    normalFancyDialog("BEAT YOUR MILESTONE - 60 DAYS!", messageForDialog);
                    //between 59(to show up in 60) and 90
                } else if (counter > 58 && counter < 91) {
                    normalFancyDialog("BEAT YOUR MILESTONE - 90 DAYS!", messageForDialog);
                    //SHOW FANCY TOAST WITH CONGRATS
                }//[END OF ELSE IFS DIALOGS]
                fab.hide();
                anim.cancel();
                subTextNonSmoker.setTextColor(getResources().getColor(R.color.greish));
            }
        });
    }

    private void normalFancyDialog(String title, String message) {
        new FancyGifDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeBtnText("NO")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("YES")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.source)   //Pass your Gif here
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        setCheckInText();
                        if (counter == 0) {
                            savings = preferences.getLong("taoz10", -10);
                        } else {
                            savings = savings + preferences.getLong("taoz10", 0);
                        }
                        if (preferences.contains("diff") && higherThanOne){
                            Log.d("COUNTERTAO", "before - counter is raised with: " + counter);
                            counter = counter + preferences.getInt("diff", -100);
                            Log.d("COUNTERTAO", "after - counter is raised with: " + counter);
                            higherThanOne = false;
                        } else {
                            Log.d("COUNTERTAO", "before - counter is raised with: " + counter);
                            counter++;
                            higherThanOne = false;
                            Log.d("COUNTERTAO", "after - counter is raised with: " + counter);
                        }
                        i = 1;
                        editor.putInt(COUNTER, counter);
                        int tempCigarettes = cigarettesPerDay * counter;
                        userCigaretesProgressTxt.setText("Ciggaretes not smoked: " + tempCigarettes);
                        editor.putInt(MODIFIED_CIGG_PER_DAY, tempCigarettes);
                        lifeRegained = Float.valueOf((5f * Float.valueOf(tempCigarettes)) / 60f);
                        userHoursProgressTxt.setText("Life regained: " + numberFormat.format(lifeRegained) + " hours");
                        editor.putFloat(LIFEREGAINED, lifeRegained);
                        editor.putLong(SAVINGS_FINAL, savings);
                        editor.apply();
                        checkActivityOnline();
//                        setTheSavingsPerDay();
                        moneyOrTimeAndGetAndSetValue();
                        setImprovementProgressLevels();
                        setImagesForAchievementCard();
                        counterText.setText(String.valueOf(counter));
                        setTargetDays();
//                        showEntireProgressForUserCard(userCigaretesProgressTxt, userRankProgressTxt, userHoursProgressTxt);
//                                    Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        //var 1 - dialog after answer
                        positiveDialogAfterPassDay();
                        //var 2 - custom toast after answer
//                        FancyToast.makeText(MainActivity.this, "Congratulations! One day healthier than yesterday!",
//                                    20, FancyToast.SUCCESS, true).show();

                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        i = 1;
                        //get time of relapse and put it into arraylist to send in logs activity
                        Calendar calendarOnClick2 = Calendar.getInstance();
                        calendarOnClick2.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                        String tem = calendarOnClick2.getTime().toString() + " ole\n";
                        if (preferences.contains("arr")){
                            arr = tem + preferences.getString("arr", "no value");
                        } else {
                            arr = tem;
                        }
                        editor.putString("arr", arr);
                        editor.apply();
                        counter = 0;
                        savings = 0;
                        //to see
                        editor.putLong(SAVINGS_FINAL, savings);//off
                        //maybe
                        editor.putInt(COUNTER, counter);
                        //new edit
                        int tempCigarettes = preferences.getInt(INITIAL_CIGG_PER_DAY, 0);
                        userCigaretesProgressTxt.setText("Ciggaretes not smoked: " + tempCigarettes);
                        editor.putInt(MODIFIED_CIGG_PER_DAY, tempCigarettes);
                        lifeRegained = Float.valueOf((5f * Float.valueOf(tempCigarettes)) / 60f);
                        userHoursProgressTxt.setText("Life regained: " + numberFormat.format(lifeRegained) + " hours");
                        editor.putFloat(LIFEREGAINED, lifeRegained);
                        editor.apply();
                        checkActivityOnline();
//                        setTheSavingsPerDay();
                        moneyOrTimeAndGetAndSetValue();
                        setImprovementProgressLevels();
                        try {
                            counter = preferences.getInt(COUNTER, -1);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        counterText.setText(String.valueOf(counter));
                        setTargetDays();
//                        showEntireProgressForUserCard(userCigaretesProgressTxt, userRankProgressTxt, userHoursProgressTxt);
//                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                        negativeDialogAfterRelapse();
                    }
                })
                .build();//[END of NORMAL DIALOG]
    }

    private void setTxtViewForUserSavingValueOfMoneyOrTime(
            String string,
            int androiId, TextView textView) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        String value = formatter.format(Integer.parseInt(string)*365);
        //target counter string
        String finalS = getString(androiId, string) + "\nper year: " + value + "$";
        textView.setText(finalS);
    }

    private void setTxtViewForUserMaxCountDaysOnStringVersion(
            String string,
            int androiId,
            TextView textview) {
        //target counter string
        textview.setText(getString(androiId, string));
    }

    private void moneyOrTimeAndGetAndSetValue() {
        if (preferences.contains(SAVINGS_FINAL)) {
            try {
                savings = preferences.getLong(SAVINGS_FINAL, 1);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        } else {
            savings = 0;
            editor.putLong(SAVINGS_FINAL, savings);
            editor.apply();
        }
        setTxtViewForUserSavingValueOfMoneyOrTime(String.valueOf(savings), R.string.money_time, moneyOrTimeTextView);
        moneyOrTimeTextView.setBackground(getResources().getDrawable(R.drawable.custom_button_round));
    }

    @SideEffect
    private void setTargetDays() {
        try {
            //format string of MAX target txt view
            if (preferences.contains(COUNTER)) {
                counter = preferences.getInt(COUNTER, 0);
            }
            if (counter == 0) {
                textNonSmoker.setText("Press on the leaf to begin");
            } else {
                textNonSmoker.setText("Non-smoker since");
            }
            if (counter>=60) {
                userMaxCountForHabit = 90;
                editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);
                editor.apply();
            } else if (counter >= 30) {
                userMaxCountForHabit = 60;
                editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);
                editor.apply();
            } else {
                userMaxCountForHabit = 30;
                editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);
                editor.apply();
            }
            userMaxCountForHabit = preferences.getInt(getString(R.string.maxCounter), -1);
            setTxtViewForUserMaxCountDaysOnStringVersion(String.valueOf(userMaxCountForHabit),
                    R.string.target_string, targetTxtViewId);
            if (!preferences.contains(CHALLENGES_STRING)) {
                challs = "Tap to start a challenge!";
                challengeTextViewSubtitle.setText(challs);
            } else {
                challs = preferences.getString(CHALLENGES_STRING, challs);
                challengeTextViewSubtitle.setText(challs);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //remaining days -- + "  " for space between number of days and text
        String calcDaysTarget = (userMaxCountForHabit - counter) + "";
        String targetCalcDaysTarget = getString(R.string.remaining_days, calcDaysTarget);
        remainingDaysTxt.setText(targetCalcDaysTarget);
    }

    //running task
    @SideEffect
    private void runningInBackground() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("targaryen", "this AsyncTask running on: " + Thread.currentThread().getName());
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Log.d("targaryen", "this scheduleAtFixedRate running on: " + Thread.currentThread().getName());
                        //run till status bar is 100%
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startTheEngine();
                            }//run from runonuithread
                        });//runonuithread
                    }//run from Timertask
                }, 100, 10_000);//Timertask once per 10 SECONDS
            }//run from async
        });//async
    }//runningInBackground

    @SideEffect
    private void startTheEngine() {
        try {
            //set text for checkin
            setCheckInText();
            showEntireProgressForUserCard(userCigaretesProgressTxt, userRankProgressTxt, userHoursProgressTxt);
            if (preferences.contains(COUNTER)) {
                counter = preferences.getInt(COUNTER, -1);editor.putInt(COUNTER, counter);
                editor.apply();
            }
            setImagesForAchievementCard();
            setImprovementProgressLevels();
            DAY_OF_CLICK = preferences.getInt(CLICKDAY_SP, 0);
            buttonClickedToday = preferences.getBoolean(CLICKED, false);
            Log.d("INTROTAO", "value for boolean clicked = " + buttonClickedToday);
            //[calendar area]
            calendarForProgress = Calendar.getInstance();
            calendarForProgress.setTimeZone(TimeZone.getTimeZone("GMT+2"));
            DAY_OF_PRESENT = calendarForProgress.get(Calendar.DAY_OF_YEAR);
            //for later usage
            editor.putInt(DAYOFPRESENT, DAY_OF_PRESENT);
            editor.apply();
            //testing area
            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            HOUR_OF_DAYLIGHT = calendar.get(Calendar.HOUR_OF_DAY);
            Log.d("taolenZ", "hour of now: " + HOUR_OF_DAYLIGHT);
            setTargetDays();
            //if the button/check in is already clicked today,
            //we disable it by checking if buttonClickedToday is true
            updateButton();
            updateConditionGreenState();
            //end of the year condition is when clickdate is higher than presentdate
            //for example clickdate remain the day 365 and in the new year eve presentdate day is 1 january
            endOfTheYearCondition();
            //green condition is when present day is higher than click day
            //in order to run our condition = to enable our button, our check in for user
            greenCondition();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SideEffect
    private void setCheckInText() {
        if (preferences.contains(HOUR_OF_FIRSLAUNCH_SP)) {
            HOUR_OF_FIRSTLAUNCH = preferences.getInt(HOUR_OF_FIRSLAUNCH_SP, 0);
        }
        //temporarily integer
        int hoursTillCheckIn = 24 - (HOUR_OF_DAYLIGHT - HOUR_OF_FIRSTLAUNCH);
//        int tempHourOfDayLight = HOUR_OF_DAYLIGHT == 0 ? 24 : HOUR_OF_DAYLIGHT;
        Log.d("TAOZEN10", "hours of now: " + HOUR_OF_DAYLIGHT + "\nhour of firstlaunch: "
                + HOUR_OF_FIRSTLAUNCH + "\nbutton clicked today ? " + buttonClickedToday);
        if (DAY_OF_PRESENT>=DAY_OF_CLICK+2) {
            subTextNonSmoker.setText("Check-in now! (+1 days)");
            Log.d("TAOZEN10", "I AM HERE OKKOKKO: " + "presetnday: " + DAY_OF_PRESENT + " clickday: " + DAY_OF_CLICK);
//        } else if (DAY_OF_CLICK == DAY_OF_PRESENT && buttonClickedToday) {
//            subTextNonSmoker.setText("Check-in tommorow!");
        } else if (DAY_OF_PRESENT == DAY_OF_CLICK+1) {
            //if fab was already pressed (buttonClickedToday == true)
            if (HOUR_OF_DAYLIGHT > HOUR_OF_FIRSTLAUNCH) { //&& buttonClickedToday
                if (HOUR_OF_FIRSTLAUNCH > HOUR_OF_DAYLIGHT) {
                    hoursTillCheckIn = HOUR_OF_FIRSTLAUNCH - HOUR_OF_DAYLIGHT;
                    subTextNonSmoker.setText("Check-in: " + hoursTillCheckIn + " hours");
                } else if (HOUR_OF_DAYLIGHT == 0) {
                    hoursTillCheckIn = 24 - HOUR_OF_FIRSTLAUNCH;
                    subTextNonSmoker.setText("Check-in: " + hoursTillCheckIn + " hours");
                } else if ((HOUR_OF_DAYLIGHT >= HOUR_OF_FIRSTLAUNCH) && !buttonClickedToday) {
                    subTextNonSmoker.setText("Check-in now!");
                    //if fab was pressed and hour is present hour equal with today hour
                } else if ((HOUR_OF_DAYLIGHT < HOUR_OF_FIRSTLAUNCH) && !buttonClickedToday) {
                    Log.d("CHECKTEXT", "  + } else if ((HOUR_OF_DAYLIGHT < HOUR_OF_FIRSTLAUNCH) && !buttonClickedToday) {");
                    subTextNonSmoker.setText("Check-in now!");
                } else {
                    subTextNonSmoker.setText("Check-in now! from else ... ");
                }
            } else if (HOUR_OF_FIRSTLAUNCH > HOUR_OF_DAYLIGHT) {
                subTextNonSmoker.setText("Check-in now!");
            } else {
                subTextNonSmoker.setText("Check-in now!");
            }
        } else {
        subTextNonSmoker.setText("Check-in: tommorow!");
        }
    }

    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        setCheckInText();
        try {
            //Only retrieve and save in onpause
            //-3 default values
            if (preferences.contains(getString(R.string.maxCounter))){userMaxCountForHabit = preferences.getInt(getString(R.string.maxCounter), -3);}
            if (preferences.contains(COUNTER)){ counter = preferences.getInt(COUNTER, -3);}
            if (preferences.contains(INITIAL_CIGG_PER_DAY)){cigarettesPerDay = preferences.getInt(INITIAL_CIGG_PER_DAY, -3);}
            if (preferences.contains(LIFEREGAINED)){ lifeRegained = preferences.getFloat(LIFEREGAINED, -3); }
            if (preferences.contains(SAVINGS_FINAL)) {savings = preferences.getLong(SAVINGS_FINAL, -3); }
//            if (preferences.contains(CLICKED)){buttonClickedToday = preferences.getBoolean(CLICKED, false);}
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
//        checkActivityOnline();
        updateButton();
        setImagesForAchievementCard();
        runningInBackground();
        moneyOrTimeAndGetAndSetValue();
        setTargetDays();
        setCheckInText();
        Log.d("INTROTAO", "values in onResume: " + "cigperday " + cigarettesPerDay+ " savings: " + savings + " counter: " + counter);
    }//[END of ONRESUME]

    //onPause
    @Override
    protected void onPause() {
        super.onPause();
        //-5 default
        try {
        if (preferences.contains(INITIAL_CIGG_PER_DAY)){cigarettesPerDay = preferences.getInt(INITIAL_CIGG_PER_DAY, -5);editor.putInt(INITIAL_CIGG_PER_DAY, cigarettesPerDay);editor.apply();}
        if (preferences.contains(SAVINGS_FINAL)) {savings = preferences.getLong(SAVINGS_FINAL, -5); editor.putLong(SAVINGS_FINAL, savings);editor.apply();}
        if (preferences.contains(COUNTER)){ counter = preferences.getInt(COUNTER, -5);editor.putInt(COUNTER, counter);editor.apply();}
        if (preferences.contains(LIFEREGAINED)){ lifeRegained = preferences.getFloat(LIFEREGAINED, -5);editor.putFloat(LIFEREGAINED, lifeRegained);editor.apply(); }
        if (preferences.contains(getString(R.string.maxCounter))){userMaxCountForHabit = preferences.getInt(getString(R.string.maxCounter), -5);editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);editor.apply();}
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d("INTROTAO", "values in onPause: " + "cigperday " + cigarettesPerDay+ " savings: " + savings+ " counter: " + counter);
    }//[END of ONPAUSE]

    //onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            editor.putInt(getString(R.string.maxCounter), userMaxCountForHabit);
            editor.putInt(COUNTER, counter);
            editor.putBoolean(CLICKED, buttonClickedToday);
            editor.putInt(INITIAL_CIGG_PER_DAY, cigarettesPerDay);
            editor.putFloat(LIFEREGAINED, lifeRegained);
            editor.putLong(SAVINGS_FINAL, savings);
            editor.apply();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d("INTROTAO", "values in onDestroy: " + "cigperday " + cigarettesPerDay+ " savings: " + savings+ " counter: " + counter);
        if (task != null && !task.isCancelled()) { task.cancel(true); }
    }//[END of ONDESTROY]

    private void updateButton() {
        if (buttonClickedToday) {
            fab.hide();
        }
    }
    //[ENABLE BOOLEAN FOR DAY PASSED]
    @SideEffect
    private void updateConditionGreenState() {
        //when click day is lower than today (present) && button was already clicked
        //we make the boolean false (in order for greenCondition to work) and for enabling button
        if (DAY_OF_PRESENT > DAY_OF_CLICK && buttonClickedToday) {
            buttonClickedToday = false;
            editor.putBoolean(CLICKED, buttonClickedToday);
            editor.apply();
            fab.hide();
            //when days are the same and user already clicked
        } else if (DAY_OF_PRESENT == DAY_OF_CLICK && buttonClickedToday) {
            fab.hide();
        }
    }

    //[ENABLE BUTTON]
    @SideEffect
    private void greenCondition() {
        if (preferences.contains(COUNTER)) {
            counter = preferences.getInt(COUNTER, 0);
        }
        Log.d("DAYZEN", "DAY OF CLICK " + DAY_OF_CLICK
                + " DAY OF PRESENT " + DAY_OF_PRESENT + "\n" +
                " HOUR_OF_FIRSTLAUNCH " + HOUR_OF_FIRSTLAUNCH + " HOUR_OF_DAYLIGHT " + HOUR_OF_DAYLIGHT);
        if (DAY_OF_PRESENT > DAY_OF_CLICK && (DAY_OF_PRESENT == DAY_OF_CLICK+1)) {
            if (HOUR_OF_FIRSTLAUNCH <= HOUR_OF_DAYLIGHT ) {
                fab.show();
                setCheckInText();
                if (i == 1)
                    startAnimatorForUpperCard();
                anim.start();
                editor.putInt(COUNTER, counter);
                editor.apply();
                Log.d("DAYZEN", "if (HOUR_OF_FIRSTLAUNCH <= HOUR_OF_DAYLIGHT ) {" + " ACTIVATED");
            } else if ((HOUR_OF_FIRSTLAUNCH > HOUR_OF_DAYLIGHT) && !buttonClickedToday) {
                fab.show();
                setCheckInText();
                if (i == 1)
                    startAnimatorForUpperCard();
                anim.start();
                editor.putInt(COUNTER, counter);
                editor.apply();
            }
        } else if (DAY_OF_PRESENT > DAY_OF_CLICK+1) {
            higherThanOne = true;
            int diff = DAY_OF_PRESENT - DAY_OF_CLICK;
            editor.putInt("diff", diff);
            editor.apply();
            fab.show();
            setCheckInText();
            if (i == 1)
                startAnimatorForUpperCard();
            anim.start();
            Log.d("DAYZEN", "} else if (DAY_OF_PRESENT > DAY_OF_CLICK+1) {" + " ACTIVATED");
        } else {
            setCheckInText();
            anim.cancel();
//                subTextNonSmoker.setTextColor(getResources().getColor(R.color.greish));
            fab.hide();
            Log.d("DAYZEN", "BIG ELSE FROM GREENCONDITION " + " ACTIVATED");
        }
    }//END OF -> [ENABLE BUTTON]

    private void startAnimatorForUpperCard() {
        View myView = findViewById(R.id.card_view_mainID);
        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, myView.getWidth() - cx);
        int dy = Math.max(cy, myView.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1500);
        animator.start();
        i = 2;
    }

    @SideEffect
    private void endOfTheYearCondition() {
        if ((DAY_OF_CLICK >= 365 && DAY_OF_PRESENT > 0) && (!buttonClickedToday)) {
            fab.show();
            //instead of counter add dialog to ask user if he did his habit
            //dialog and onclick counter++;
            buttonClickedToday = preferences.getBoolean(CLICKED, false);
            editor.putInt(COUNTER, counter);
            editor.apply();
        }
    }

    @SideEffect
    private void setImprovementProgressLevels() {
        try {
            if (preferences.contains(COUNTER)) {
                counter = preferences.getInt(COUNTER, 0);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d("taolenX", "[setImprovementProgressLevels] counter is = " + counter);
        //TODO: setup the levels
        //energy levels
        if (counter > 0 && counter < 10) {
            txtProgressForEnergyLevels.setText(10 + "%");
            progressBarEnergyLevel.setProgress(10);
        } else if (counter > 9 && counter < 25) {
            txtProgressForEnergyLevels.setText(25 + "%");
            progressBarEnergyLevel.setProgress(25);
        } else if (counter > 24 && counter < 40) {
            txtProgressForEnergyLevels.setText(50 + "%");
            progressBarEnergyLevel.setProgress(50);
        } else if (counter > 39 && counter < 56) {
            txtProgressForEnergyLevels.setText(75 + "%");
            progressBarEnergyLevel.setProgress(75);
        } else if (counter > 55) {
            txtProgressForEnergyLevels.setText(100 + "%");
            progressBarEnergyLevel.setProgress(100);
//            setMargins (txtProgressForEnergyLevels, 0, 0, -15, 0);
        }

        //fatigue levels
        if (counter > 0 && counter < 15) {
            txtProgressForFatigue.setText(10 + "%");
            progressBarFatigueLevel.setProgress(10);
        } else if (counter > 14 && counter < 30) {
            txtProgressForFatigue.setText(25 + "%");
            progressBarFatigueLevel.setProgress(25);
        } else if (counter > 29 && counter < 60) {
            txtProgressForFatigue.setText(50 + "%");
            progressBarFatigueLevel.setProgress(50);
        } else if (counter > 59 && counter < 80) {
            txtProgressForFatigue.setText(75 + "%");
            progressBarFatigueLevel.setProgress(75);
        } else if (counter > 79) {
            txtProgressForFatigue.setText(100 + "%");
            progressBarFatigueLevel.setProgress(100);
        }

        //gums levels
        if (counter > 0 && counter < 20) {
            txtProgressForGums.setText(25 + "%");
            progressBarGumsLevel.setProgress(25);
        } else if (counter > 19 && counter < 40) {
            txtProgressForGums.setText(50 + "%");
            progressBarGumsLevel.setProgress(50);
        } else if (counter > 39 && counter < 60) {
            txtProgressForGums.setText(75 + "%");
            progressBarGumsLevel.setProgress(75);
        } else if (counter > 59) {
            txtProgressForGums.setText(100 + "%");
            progressBarGumsLevel.setProgress(100);
        }

        //breath levels
        if (counter > 0 && counter < 10) {
            txtProgressForBreath.setText(25 + "%");
            progressBarBreathlevel.setProgress(25);
        } else if (counter > 9 && counter < 20) {
            txtProgressForBreath.setText(50 + "%");
            progressBarBreathlevel.setProgress(50);
        } else if (counter > 19 && counter < 30) {
            txtProgressForBreath.setText(75 + "%");
            progressBarBreathlevel.setProgress(75);
        } else if (counter > 29) {
            txtProgressForBreath.setText(100 + "%");
            progressBarBreathlevel.setProgress(100);
        }
    }

    //MENU DRAWER
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //[MENU]
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            counter++;
            Calendar calendar = Calendar.getInstance();
            int zet2 = calendar.get(Calendar.MINUTE);
            int zet = preferences.getInt(CLICKDAY_SP, 0);
            editor.putInt(CLICKDAY_SP, zet2);
            Log.d("taolenZ777", "zet = " + zet2);
            counterText.setText(String.valueOf(counter));
            editor.putInt(COUNTER, counter);
            editor.apply();
            setImprovementProgressLevels();
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_help) {
            counter = 80;
            counterText.setText(String.valueOf(counter));
            editor.putInt(COUNTER, counter);
            editor.apply();
        } else if (id == R.id.check_in_hour) {
            showDialogForChangingCheckInDate();
        }
        return super.onOptionsItemSelected(item);
    }//END OF -> [MENU]

    @SideEffect
    protected boolean isOnline() {
        ConnectivityManager connManager =
                (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =
                connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }//isOnline[END]
    private void requestDataById(int id) {
        task = new MainActivity.MyAsyncTask();
        task.execute(MainActivity.HTTPS_PYFLASKTAO_HEROKUAPP_COM_BOOKS + "/" + id);
    }
    @SideEffect
    private void checkActivityOnline() {
        if (isOnline()) {
            try {
                if (preferences.contains(COUNTER)) {
                    counter = preferences.getInt(COUNTER, 0);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (counter == 0) {
                requestDataById(1);
            } else {
                requestDataById(counter);
            }
//            requestDataById(DAY_OF_PRESENT);
        } else {
            errorText.setVisibility(View.VISIBLE);
            tipofthedayTxtView.setText("ERROR 404");
            Snackbar snackbar;
            snackbar = Snackbar.make(parentLayout, "NO INTERNET CONNECTION!", Snackbar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.RED);
            snackbar.show();
        }
    }

    private void updateDisplayString(String message) {
        tipofthedayTxtView.setText(new StringBuilder()
                        .append(message)
                        .append("\n")
                        .toString()
        );
    }

    @SuppressLint("StaticFieldLeak")
    public class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            counter = preferences.getInt(COUNTER, counter);
            counterText.setText(String.valueOf(counter));
            updateDisplayString("Starting to fetch data from heroku ...");
            if (tasks.size() == 0) {
                progressBarLoading.setVisibility(View.VISIBLE);
                progressBarLoading2.setVisibility(View.VISIBLE);
                counterImgView.setVisibility(View.INVISIBLE);
                counterText.setVisibility(View.INVISIBLE);
            }
            //if we click we add a task
            tasks.add(this);
        }//onPreExecute[END]
        @Override
        protected String doInBackground(String... params) {
            try {
                //using GSON
                JsonParser parser = new JsonParser();
                //using MyHttpManager getData static method
                String content = MyHttpManager.getData(params[0]);
//                Thread.sleep(1000);
                //using MyHttpCoreAndroid
//                String content = MyHttpCoreAndroid.getData(params[0]);
                assert content != null;
                JsonElement rootNode = parser.parse(content);
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement nameNode = details.get("name");
                return nameNode.getAsString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }//using GSON[END]
        }//doInBackground[END]
        @Override
        protected void onPostExecute(String result) {
            //using raw JSON PARSER
            updateDisplayString(result);
            //we get rid of the task that we created
            tasks.remove(this);
            if (tasks.size() == 0) {
                progressBarLoading.setVisibility(View.INVISIBLE);
                progressBarLoading2.setVisibility(View.GONE);
                counterImgView.setVisibility(View.VISIBLE);
                counterText.setVisibility(View.VISIBLE);
            }
            if (result == null) {
                Toast.makeText(MainActivity.this, "Can't connect to web service",
                        Toast.LENGTH_LONG).show();
            }
        }//onPostExecute[END]
        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplayString(values[0]);
        }//onProgressUpdate[END]
    }//MyAsyncTask[END]

    @SideEffect
    private void setImagesForAchievementCard() {
        try {
            if (preferences.contains(COUNTER)) {
                counter = preferences.getInt(COUNTER, 0);
            }
        } catch (NullPointerException e){e.printStackTrace();}
        Log.d("taoAchiev", "counter from achiev = " + counter);
        if (counter>=0&&counter<10) {
            //user have between a day and a week
            rankOneImg.setBackgroundResource(R.mipmap.chevron7);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron8);
            rankTwoImg.setAlpha(0.2f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron9);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron11);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Recruit");
        } else if (counter>9&&counter<20) {
            rankOneImg.setBackgroundResource(R.mipmap.chevron7);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron8);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron9);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron11);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Recruit II");
        } else if (counter>19&&counter<30) {
            rankOneImg.setBackgroundResource(R.mipmap.chevron7);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron8);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron9);
            rankThreeImg.setAlpha(1.0f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron11);
            rankFourImg.setAlpha(1.0f);
            editor.putString("rank", "Recruit III");
        } else if (counter>29&&counter<40) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron19);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron20);
            rankTwoImg.setAlpha(0.2f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron21);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron10);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Silver");
        } else if (counter>39&&counter<50) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron19);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron20);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron21);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron10);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Silver II");
        } else if (counter>49&&counter<60) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron19);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron20);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron21);
            rankThreeImg.setAlpha(1.0f);
            rankFourImg.setBackgroundResource(R.mipmap.chevron10);
            rankFourImg.setAlpha(1.0f);
            editor.putString("rank", "Silver III");
        } else if (counter>59&&counter<70) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron16);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron17);
            rankTwoImg.setAlpha(0.2f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron18);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.gnm);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Gold");
        } else if (counter>69&&counter<80) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron16);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron17);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron18);
            rankThreeImg.setAlpha(0.2f);
            rankFourImg.setBackgroundResource(R.mipmap.gnm);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Gold nova");
        } else if (counter>79&&counter<90) {
            //when user pass 1 week
            rankOneImg.setBackgroundResource(R.mipmap.chevron16);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron17);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron18);
            rankThreeImg.setAlpha(1.0f);
            rankFourImg.setBackgroundResource(R.mipmap.gnm);
            rankFourImg.setAlpha(0.2f);
            editor.putString("rank", "Gold nova expert!");
            //WHEN USER REACH DAY 90 - GREATEST MILESTONE
        } else if (counter == 90) {
            rankOneImg.setBackgroundResource(R.mipmap.chevron16);
            rankOneImg.setAlpha(1.0f);
            rankTwoImg.setBackgroundResource(R.mipmap.chevron17);
            rankTwoImg.setAlpha(1.0f);
            rankThreeImg.setBackgroundResource(R.mipmap.chevron18);
            rankThreeImg.setAlpha(1.0f);
            rankFourImg.setBackgroundResource(R.mipmap.gnm);
            rankFourImg.setAlpha(1.0f);
            editor.putString("rank", "Gold nova master!");
        }
    }

    private void showDialogForChangingCheckInDate() {
        final EditText editTextForChoosingSavings =
                new EditText(MainActivity.this);
        editTextForChoosingSavings.setInputType(InputType.TYPE_CLASS_NUMBER);
        //impl bottom dialog instead of normal dialog
        new BottomDialog.Builder(this)
                .setTitle("Check in customization!")
                .setContent("Type in what time is suitable for you to check in.")
                .setPositiveText("SET")
                .setCancelable(false)
                .setCustomView(editTextForChoosingSavings)
                .setPositiveBackgroundColorResource(R.color.colorPrimary)
                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                .setPositiveTextColorResource(android.R.color.white)
                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Editable editM = editTextForChoosingSavings.getText();
                        if (TextUtils.isEmpty(editM)){
                            editTextForChoosingSavings.setError("Please input numbers!");
                            return;
                        }
                        HOUR_OF_FIRSTLAUNCH = Integer.parseInt(editM.toString());
                        editor.putInt(HOUR_OF_FIRSLAUNCH_SP, HOUR_OF_FIRSTLAUNCH);
                        editor.apply();
                        Log.d("INTROTAO", "money saved in INTROACTIVITY ? :  " + HOUR_OF_FIRSTLAUNCH);
                    }
                }).show();
    }
//    private void showDialogForSavingCiggarettesNumber() {
//        final EditText editTextForChoosingCiggs = new EditText(MainActivity.this);
//        editTextForChoosingCiggs.setInputType(InputType.TYPE_CLASS_NUMBER);
//        //impl bottom dialog instead of normal dialog
//        new BottomDialog.Builder(this)
//                .setTitle("CIGGARETTES!")
//                .setContent("How much ciggarettes do you smoke per day ?")
//                .setPositiveText("OK")
//                .setCustomView(editTextForChoosingCiggs)
//                .setCancelable(false)
//                .setPositiveBackgroundColorResource(R.color.colorPrimary)
//                //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
//                .setPositiveTextColorResource(android.R.color.white)
//                //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
//                .onPositive(new BottomDialog.ButtonCallback() {
//                    @Override
//                    public void onClick(BottomDialog dialog) {
//                            Editable editM = editTextForChoosingCiggs.getText();
//                            int cigInt = Integer.parseInt(editM.toString());
//                            editor.putInt(INITIAL_CIGG_PER_DAY, cigInt);
//                            editor.apply();
//                            Log.d("INTROTAO", "cigg saved in INTROACTIVITY ? :  " + cigInt);
////                            startIntroActivity();
//                    }
//                }).show();
//
//
//
//    }



    @SideEffect
    private void showEntireProgressForUserCard(TextView userCigarettesProgressTat,
                                               TextView userRankProgressTxt,
                                               TextView userHoursProgressTxt) {
        try {
            String theLatestRank = preferences.getString("rank", "unranked yet");
            userRankProgressTxt.setText("Your rank: " + theLatestRank);

            String rank_master = getString(R.string.rank_master, theLatestRank);
            rank_masterTxt.setText(rank_master);
            rank_masterTxt.setBackground(getResources().getDrawable(R.drawable.custom_button_round));

            if (counter == 0) {
                userCigarettesProgressTat.setText("Ciggaretes: press leaf to calculate");
                userHoursProgressTxt.setText("Life regained: " + "press leaf to calculate" + " hours");
            } else {
                if (preferences.contains(LIFEREGAINED)) {
                    lifeRegained = preferences.getFloat(LIFEREGAINED, -1);
                    numberFormat.format(lifeRegained);
                } else {
                    lifeRegained = Float.valueOf((5f * Float.valueOf(preferences.getInt(MODIFIED_CIGG_PER_DAY, 0))) / 60f);
                    numberFormat.format(lifeRegained);
                }
                userCigarettesProgressTat.setText("Ciggaretes not smoked: " + preferences.getInt(MODIFIED_CIGG_PER_DAY, 0));
                userHoursProgressTxt.setText("Life regained: " + numberFormat.format(lifeRegained) + " hours");
                editor.putFloat(LIFEREGAINED, lifeRegained);
                editor.apply();
            }
        } catch(NullPointerException e){ e.printStackTrace(); } catch (Exception e) {e.printStackTrace();}
    }
    private void setMarginForProgress() {
        Resources r = this.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(px, 0, 0, 0);
        Log.d("TAOLEN10", " works the SETMARGIN + " + progressBarEnergyLevel.getProgress());
        if (progressBarEnergyLevel.getProgress() == 100) {
//            txtProgressForEnergyLevels.setLayoutParams(params);
            setMargins(txtProgressForEnergyLevels, px, 0 , 0, 0);
            Log.d("TAOLEN10", " works the SETMARGIN + " + progressBarEnergyLevel.getProgress());
        }
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}//[END OF MAIN CLASS]
