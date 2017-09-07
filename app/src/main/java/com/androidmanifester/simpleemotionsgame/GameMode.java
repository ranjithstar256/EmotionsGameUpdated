package com.androidmanifester.simpleemotionsgame;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class GameMode extends AppCompatActivity {
    ArrayList<String> words, Happy, Sad, Good, Bad, Weird, All;
    ArrayList<Integer> BalloonColor;
    LinkedList<TextView> tvlist;
    int height, width, score;
    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tvscore, tvcount;
    String SelectedWord;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private SoundHelper mSoundHelper;

    @Override
    protected void onPause() {
        super.onPause();
        mSoundHelper.stopMusic();
        editor.putInt("asdfaf", 0).commit();
        Log.d("abcdeonpause", "" + sharedPreferences.getInt("COUNT", 0));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (((getIntent().getIntExtra("ori", 1)) == 1)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        mSoundHelper = new SoundHelper(this);
        mSoundHelper.prepareMusicPlayer(this);
        mSoundHelper.playMusic();
        tv0 = (TextView) findViewById(R.id.textView0);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        tv8 = (TextView) findViewById(R.id.textView8);
        tvcount = (TextView) findViewById(R.id.tvcountid);
        tvscore = (TextView) findViewById(R.id.tvscoreid);

        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        SelectedWord = sharedPreferences.getString("selectedword", "None");
        words = new ArrayList<String>();
        tvlist = new LinkedList<TextView>();
        BalloonColor = new ArrayList<Integer>();
        All = new ArrayList<String>();
        Weird = new ArrayList<String>();
        Bad = new ArrayList<String>();
        Happy = new ArrayList<String>();
        Sad = new ArrayList<String>();
        Good = new ArrayList<String>();
        words.add("Happy");
        words.add("Sad");
        words.add("Good");
        words.add("Bad");
        words.add("Weird");

        tvlist.add(tv0);
        tvlist.add(tv1);
        tvlist.add(tv2);
        tvlist.add(tv3);
        tvlist.add(tv4);
        tvlist.add(tv5);
        tvlist.add(tv6);
        tvlist.add(tv7);
        tvlist.add(tv8);

        Happy.add("Contented");
        Happy.add("Cheerful");
        Happy.add("Cheery");
        Happy.add("Merry");
        Happy.add("Joyful");


        Sad.add("unhappy");
        Sad.add("Sorrowful");
        Sad.add("Dejected");
        Sad.add("Regretful");
        Sad.add("Depressed");
        Good.add("Fine");
        Good.add("Of high quality");
        Good.add("Of a high standard");
        Good.add("Quality");
        Good.add("Superior");

        Bad.add("Poor");
        Bad.add("Imperfect");
        Bad.add("Defective");
        Bad.add("Faulty");
        Bad.add("Substandard");
        Weird.add("Mysterious");
        Weird.add("Supernatural");
        Weird.add("Unearthly");
        Weird.add("Unreal");
        Weird.add("Eerie");
        All.add("Contented");
        All.add("Cheerful");
        All.add("Cheery");
        All.add("Merry");
        All.add("Joyful");
        All.add("Unhappy");
        All.add("Sorrowful");
        All.add("Dejected");
        All.add("Regretful");
        All.add("Depressed");
        All.add("Fine");
        All.add("Of high quality");
        All.add("Of a high standard");
        All.add("Quality");
        All.add("Superior");
        All.add("Poor");
        All.add("Imperfect");
        All.add("Defective");
        All.add("Faulty");
        All.add("Substandard");
        All.add("Mysterious");
        All.add("Supernatural");
        All.add("Unearthly");
        All.add("Unreal");
        All.add("Eerie");

        BalloonColor.add(R.drawable.balloon_blue);
        BalloonColor.add(R.drawable.balloon_green);
        BalloonColor.add(R.drawable.balloon_orange);
        BalloonColor.add(R.drawable.balloon_red);
        BalloonColor.add(R.drawable.balloon_yellow);


        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Collections.shuffle(tvlist, new Random(System.nanoTime()));
                Collections.shuffle(BalloonColor, new Random(System.nanoTime()));
                Collections.shuffle(All, new Random(System.nanoTime()));

                int u = sharedPreferences.getInt("asdfaf", 0);//0
                if (u < tvlist.size()) {
                    // Collections.shuffle(tvlist, new Random(System.nanoTime()));
                    tvlist.get(u).setGravity(Gravity.CENTER);//0
                    tvlist.get(u).setBackgroundResource(BalloonColor.get(0));//0
                    animat(tvlist.get(u), All.get(0), sharedPreferences.getInt("ge", 7));//0

                    u = ++u + (++u);
                    editor.putInt("asdfaf", u).commit();//1


                } else {
                    editor.putInt("asdfaf", 0).commit();
                    Collections.shuffle(tvlist, new Random(System.nanoTime()));
                    tvlist.get(2).setBackgroundResource(BalloonColor.get(0));
                    tvlist.get(2).setGravity(Gravity.CENTER);
                    animat(tvlist.get(2), All.get(0), sharedPreferences.getInt("ge", 6));
                }

      /* and here comes the "trick" */
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(runnable, 100);


//        final Timer t2 = new Timer();
//        t2.scheduleAtFixedRate(new TimerTask() {
//                                   @Override
//                                   public void run() {
//                                       runOnUiThread(new Runnable() {
//                                           @Override
//                                           public void run() {
//
//                                           }
//                                       });
//                                   }
//                               },//Set how long before to start calling the TimerTask (in milliseconds)
//                0,//Set the amount of time between each execution (in milliseconds)
//                2000);

        new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvcount.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tvcount.setText("Time's Up!");
                handler.removeCallbacks(runnable);

                AlertDialog.Builder dialog = new AlertDialog.Builder(GameMode.this);
                dialog.setMessage("Time's Up!" + "" +
                        "\n" + "Your Score " + tvscore.getText().toString());
                dialog.setIcon(R.drawable.happy);
                dialog.setTitle("Good Job!");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(GameMode.this, GameSummary.class).putExtra("scr", "Your Score " + tvscore.getText().toString()));
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        }.start();
    }


    public void animat(final TextView t, final String v, int z) {
        // if(z==7){

        // } else {
        final ObjectAnimator mover = ObjectAnimator.ofFloat(t, "translationY", 0, -height);

        switch (sharedPreferences.getInt("skilevel", 1)) {

            case 0:
                mover.setDuration(10000);
                break;
            case 1:
                mover.setDuration(10000);
                break;
            case 2:
                mover.setDuration(10000);
                break;
            case 3:
                mover.setDuration(10000);
                break;
            case 4:
                mover.setDuration(10000);
                break;
            case 5:
                mover.setDuration(10000);
                break;
            default:
                mover.setDuration(10000);
                break;
        }
            t.setText(v);
            mover.start();
        mover.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                editor.putInt("ge", 7).commit();

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                editor.putInt("ge", 6).commit();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = t.getText().toString();
                boolean state;
                switch (SelectedWord) {
                    case "Happy":
                        state = Happy.contains(buttonText);
                        break;
                    case "Sad":
                        state = Sad.contains(buttonText);
                        break;
                    case "Good":
                        state = Good.contains(buttonText);
                        break;
                    case "Bad":
                        state = Bad.contains(buttonText);
                        break;
                    case "Weird":
                        state = Weird.contains(buttonText);
                        break;
                    default:
                        state = false;
                        break;
                }
                if (state) {
                    score = score + 10;
                    tvscore.setText(score + "");
                    mSoundHelper.playSound(t, 0);
                    mover.end();
                } else {
                    score = score - 10;
                    tvscore.setText(score + "");
                    mSoundHelper.playSound(t, 1);
                    mover.end();
                }
                t.setClickable(false);
            }
        });
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.words, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Dialog dialog = new Dialog(GameMode.this);
        dialog.setContentView(R.layout.dia);

        ListView listView = (ListView) dialog.findViewById(R.id.lv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GameMode.this, android.R.layout.simple_spinner_dropdown_item, words);
        listView.setChoiceMode(1);
        listView.setAdapter(arrayAdapter);
        dialog.setTitle("Select Word");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedWord = words.get(position);
                editor.putString("selectedword", SelectedWord).commit();
                Toast.makeText(GameMode.this, SelectedWord + " selected", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mSoundHelper.stopMusic();

        new MaterialDialog.Builder(this)
                .title("Confirm Exit")
                .content("Are You Sure?")
                .positiveText("Exit")
                .negativeText("Cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                        mSoundHelper.stopMusic();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        mSoundHelper.playMusic();
                    }
                })
                .show();
    }
//
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu, menu);
//    }
//
//    public boolean onContextItemSelected(MenuItem item) {
//
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        String[] names = getResources().getStringArray(R.array.states);
//        switch (item.getItemId()) {
//            case R.id.edit:
//                Toast.makeText(this, "You have chosen the " + "iki" +
//                                " context menu option for " + names[(int) info.id],
//                        Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }


    // int f = sharedPreferences.getInt("it",0);
    //    Log.d("abcdet1enternce",""+sharedPreferences.getInt("COUNT",0));
    //  if(sharedPreferences.getInt("COUNT",0)<tvs.size()){
    // tvs.get(sharedPreferences.getInt("COUNT",0)).setGravity(Gravity.CENTER);
    // tvs.get(sharedPreferences.getInt("COUNT",0)).setBackgroundResource(BalloonColor.get(0));
    //Collections.shuffle(BalloonColor, new Random(System.nanoTime()));
    // tvs.get(now).setGravity(Gravity.CENTER);
    // tvs.get(now).setBackgroundResource(BalloonColor.get(0));
    // Collections.shuffle(All, new Random(System.nanoTime()));
    ///og.d("abcdeanimating numer",""+sharedPreferences.getInt("COUNT",0));

    //animat(tvs.get(sharedPreferences.getInt("COUNT",0)), All.get(0));
    // int nr=sharedPreferences.getInt("COUNT",0)+1;
    //    Log.d("abcdeadded+1?",""+nr);

    //   editor.putInt("COUNT",nr).commit();
    //   int cv=tvs.size()-1;
    //  Log.d("abcdecv size",""+cv);

    // if(nr==cv){

    //      Collections.shuffle(tvs, new Random(System.nanoTime()));
    //   editor.putInt("COUNT",0).commit();
    //       Log.d("abcdechanged?",""+sharedPreferences.getInt("COUNT",0));

    //  }
    //  }
    //  else {
    //      Collections.shuffle(tvs, new Random(System.nanoTime()));
    ////      editor.putInt("COUNT",0).commit();
    //      Log.d("abcderesetted?",""+sharedPreferences.getInt("COUNT",0));

    //animat(tvs.get(sharedPreferences.getInt("chnAis",0)), All.get(0));
    //  }
    ///if (!mover.isRunning()){
    ///  Toast.makeText(this, "runing", Toast.LENGTH_SHORT).show();
    ///}else {}
    /// //                    case 0:
//                        mover.setDuration(10000);
//                        break;
//                    case 1:
//                        mover.setDuration(9000);
//                        break;
//                    case 2:
//                        mover.setDuration(8000);
//                        break;
//                    case 3:
//                        mover.setDuration(7000);
//                        break;
//                    case 4:
//                        mover.setDuration(6000);
//                        break;
//                    case 5:
//                        mover.setDuration(5000);
//                        break;
//                    default:
//                        mover.setDuration(5000);
//                        break;
    //	unhappy, sorrowful,
    // dejected, regretful, depressed, downcast,
    //contented, content, cheerful,
    // cheery, merry, joyful
    //	fine, of high quality, of a high standard, quality, superior
    //	substandard, poor, inferior, second-rate, second-class, unsatisfactory, inadequate, unacceptable,
    // not up to scratch, not up to par,
    // deficient, imperfect, defective, faulty, shoddy, amateurish, careless, negligent
    //	uncanny, eerie, unnatural, preternatural, supernatural, unearthly,
    // other-worldly, unreal, ghostly, mysterious
}