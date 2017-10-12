package com.example.swipacardlibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swipacardlibrary.R;
import com.example.swipacardlibrary.adapter.WordAdapter;
import com.example.swipacardlibrary.helper.AnimatorHelper;
import com.example.swipacardlibrary.listener.CardListener;
import com.example.swipacardlibrary.listener.DataSetObserver;
import com.example.swipacardlibrary.model.WordInterface;


public class Card extends CardView implements View.OnTouchListener, CardListener {

    private Context mContext;

    private final static float RIGHT_DISABLE_COEFFICIENT = 3f / 5;
    private final static float LEFT_DISABLE_COEFFICIENT = 2f / 5;
    private final static float NOT_MOVE_TIME_VALUE = 100f;
    private final static float NOT_MOVE_DISTANCE_VALUE = 1f;

    private float xCurrent;
    private float yCurrent;
    private float xStartCard;
    private float yStartCard;
    private Point sizeWindow;

    private Card cardView;

    private TextView mainWordTextView;
    private TextView transcriptionTextView;
    private TextView exampleTextView;

    private TextView helpPositivTextView;
    private TextView helpNegativeTextView;
    private Button positiveBtn;
    private Button negativeBtn;


    private boolean isEnglish = true;
    private boolean DefaultLang = true; //if english is default lang
    private WordInterface current_word;
    private WordAdapter adapter;
    private int backgroundColor;
    private int primaryColor;
    private int back_color;

    private final DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onDataSetChanged() {

        }
    };

    public WordAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(WordAdapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterDataObserver();
        }
        this.adapter = adapter;
        if (this.adapter != null) {
            this.adapter.registerDataObserver(dataSetObserver);
        }
        initViews();
        setupAttrs();
        setListeners();
        current_word = (WordInterface) adapter.getCurrentItem();
        if (current_word != null) showWordOnCard(current_word);
    }

    private void setListeners() {
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveButtonClicked();
            }
        });
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNegativeButtonClicked();
            }
        });
    }

    private void setupAttrs() {
        mainWordTextView.setTextColor(primaryColor);
        cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
    }

    private void initViews() {

        Resources r = getResources();
        int dp_10 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        int dp_30 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        int dp_120 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, r.getDisplayMetrics());
        int dp_1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, r.getDisplayMetrics());

        FrameLayout frame = new FrameLayout(mContext);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frame.setLayoutParams(layoutparams);


        LinearLayout linearLayout_child = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout_child.setOrientation(LinearLayout.VERTICAL);
        linearLayout_child.setGravity(Gravity.CENTER);
        linearLayout_child.setLayoutParams(params);


        mainWordTextView = new TextView(mContext);
        LinearLayout.LayoutParams params_textview = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3);

        mainWordTextView.setLayoutParams(params_textview);
        mainWordTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        mainWordTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        linearLayout_child.addView(mainWordTextView);

        LinearLayout layout_tr_and_example = new LinearLayout(mContext);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 4);
        layout_tr_and_example.setLayoutParams(params);
        layout_tr_and_example.setOrientation(LinearLayout.VERTICAL);

        params_textview = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        transcriptionTextView = new TextView(mContext);
        transcriptionTextView.setLayoutParams(params_textview);
        transcriptionTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);

        params_textview = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(dp_10, dp_10, dp_10, dp_10);
        exampleTextView = new TextView(mContext);
        exampleTextView.setLayoutParams(params_textview);
        exampleTextView.setEllipsize(TextUtils.TruncateAt.END);
        exampleTextView.setMaxLines(4);
        exampleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        exampleTextView.setGravity(Gravity.CENTER);


        layout_tr_and_example.addView(transcriptionTextView);
        layout_tr_and_example.addView(exampleTextView);


        linearLayout_child.addView(layout_tr_and_example);


        helpPositivTextView = new TextView(mContext);
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.RIGHT | Gravity.TOP;
        params1.setMargins(dp_30, dp_30, dp_30, dp_30);
        helpPositivTextView.setLayoutParams(params1);
        helpPositivTextView.setGravity(Gravity.RIGHT);
        helpPositivTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        helpPositivTextView.setBackgroundResource(R.drawable.textview_positiv);
        helpPositivTextView.setTextColor(ContextCompat.getColor(mContext, R.color.positive_color));
        helpPositivTextView.setVisibility(INVISIBLE);
        frame.addView(helpPositivTextView);


        helpNegativeTextView = new TextView(mContext);
        FrameLayout.LayoutParams frame_params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frame_params.setMargins(dp_30, dp_30, dp_30, dp_30);
        frame_params.gravity = Gravity.LEFT | Gravity.TOP;
        helpNegativeTextView.setLayoutParams(frame_params);
        helpNegativeTextView.setGravity(Gravity.CENTER);
        helpNegativeTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        helpNegativeTextView.setBackgroundResource(R.drawable.text_view_negative);
        helpNegativeTextView.setTextColor(ContextCompat.getColor(mContext, R.color.negative_color));
        helpNegativeTextView.setVisibility(INVISIBLE);
        helpNegativeTextView.setText(R.string.negativ_text);
        helpPositivTextView.setText(R.string.positiv_text);

        frame.addView(helpNegativeTextView);

        frame.addView(linearLayout_child);

        cardView.addView(frame);

        //add positive and negativ  buttons
        LinearLayout cardview_parent_layout = (LinearLayout) cardView.getParent();

        LinearLayout buttons_parent_layout = new LinearLayout(mContext);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        params.gravity = Gravity.CENTER;
        buttons_parent_layout.setOrientation(LinearLayout.HORIZONTAL);
        buttons_parent_layout.setLayoutParams(params);


        LinearLayout layout_btn_left = new LinearLayout(mContext);
        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.gravity = Gravity.CENTER;
        params.rightMargin = dp_1;
        layout_btn_left.setLayoutParams(params);
        layout_btn_left.setGravity(Gravity.RIGHT);

        positiveBtn = new Button(mContext);
        LinearLayout.LayoutParams positive_btn_params = new LinearLayout.LayoutParams(dp_120, ViewGroup.LayoutParams.WRAP_CONTENT);
        positive_btn_params.gravity = Gravity.RIGHT;
        positiveBtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        positiveBtn.setGravity(Gravity.CENTER);
        positiveBtn.setBackgroundResource(R.drawable.btn_positive);
        positiveBtn.setLayoutParams(positive_btn_params);
        positiveBtn.setText(R.string.positiv_text);

        layout_btn_left.addView(positiveBtn);

        LinearLayout layout_btn_negative = new LinearLayout(mContext);
        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.gravity = Gravity.CENTER;
        params.leftMargin = dp_1;
        layout_btn_negative.setLayoutParams(params);
        layout_btn_negative.setGravity(Gravity.LEFT);

        negativeBtn = new Button(mContext);
        LinearLayout.LayoutParams negative_btn_params = new LinearLayout.LayoutParams(dp_120, ViewGroup.LayoutParams.WRAP_CONTENT);
        negativeBtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        negativeBtn.setGravity(Gravity.CENTER);
        negativeBtn.setBackgroundResource(R.drawable.btn_negative);
        negativeBtn.setLayoutParams(negative_btn_params);
        negativeBtn.setText(R.string.negativ_text);
        layout_btn_negative.addView(negativeBtn);


        buttons_parent_layout.addView(layout_btn_left);
        buttons_parent_layout.addView(layout_btn_negative);

        cardview_parent_layout.addView(buttons_parent_layout);

    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        negativeBtn.setVisibility(visibility);
        positiveBtn.setVisibility(visibility);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Card,
                0, 0);
        try {
            backgroundColor = a.getColor(R.styleable.Card_card_background, 0);
            primaryColor = a.getColor(R.styleable.Card_primary_color, 0);
            back_color = a.getColor(R.styleable.Card_back_color, 0);


        } finally {
            a.recycle();
        }
        mContext = context;
        cardView = this;


        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        sizeWindow = new Point();
        display.getSize(sizeWindow);
        cardView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xStartCard = cardView.getX();
                yStartCard = cardView.getY();
                xCurrent = motionEvent.getX();
                yCurrent = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                cardView.setX(cardView.getX() + (motionEvent.getX() - xCurrent));
                cardView.setY(cardView.getY() + (motionEvent.getY() - yCurrent));
                if (cardView.getX() - xStartCard > 0) {
                    float alpha = (cardView.getX() - xStartCard) / (LEFT_DISABLE_COEFFICIENT * sizeWindow.x);
                    helpNegativeTextView.setVisibility(VISIBLE);
                    helpNegativeTextView.setAlpha(alpha);
                } else {
                    float alpha = (xStartCard-cardView.getX()) / (cardView.getWidth()+xStartCard-RIGHT_DISABLE_COEFFICIENT * sizeWindow.x);
                    helpPositivTextView.setVisibility(VISIBLE);
                    helpPositivTextView.setAlpha(alpha);
                }

                break;
            case MotionEvent.ACTION_UP:
                helpPositivTextView.setVisibility(GONE);
                helpNegativeTextView.setVisibility(GONE);
                if ((motionEvent.getEventTime() - motionEvent.getDownTime() < NOT_MOVE_TIME_VALUE
                        && motionEvent.getEventTime() - motionEvent.getDownTime() < NOT_MOVE_TIME_VALUE)
                        || (Math.abs(xStartCard - cardView.getX()) < NOT_MOVE_DISTANCE_VALUE &&
                        Math.abs(yStartCard - cardView.getY()) < NOT_MOVE_DISTANCE_VALUE)) {
//                     when touch on card
                    onCardClicked();
                } else if ((cardView.getX() + cardView.getWidth()) < RIGHT_DISABLE_COEFFICIENT * sizeWindow.x) {
                    onSwipedLeft();
                } else if (cardView.getX() - xStartCard > LEFT_DISABLE_COEFFICIENT * sizeWindow.x) {
                    // when swipe card
                    onSwipedRight(); //
                }
                cardView.setX(xStartCard);
                cardView.setY(yStartCard);
                break;
        }
        return true;
    }

    public void onSwiped() {
        if (DefaultLang) {
            isEnglish = true;
        } else {
            isEnglish = false;
        }
        AnimatorHelper.changeView(mContext, cardView);

    }

    public void setEnglishWord(WordInterface word) {
        mainWordTextView.setText(word.getEnWord());
        transcriptionTextView.setText("[" + word.getTranscription() + "]");
        exampleTextView.setText(word.getExample());
    }

    public void setRussianWord(WordInterface word) {
        mainWordTextView.setText(word.getTranslation());
        transcriptionTextView.setText("");
        exampleTextView.setText(word.getExampleTrans());
    }

    public void showWordOnCard(WordInterface word) {
        if (DefaultLang) setEnglishWord(word);
        else
            setRussianWord(word);
    }

    @Override
    public void onSwipedLeft() {
        onSwiped();
        current_word = (WordInterface) adapter.getPrevItem();
        if (current_word != null) showWordOnCard(current_word);
    }

    @Override
    public void onSwipedRight() {
        onSwiped();
        current_word = (WordInterface) adapter.getNextItem();
        if (current_word != null) showWordOnCard(current_word);
    }


    @Override
    public void onCardClicked() {
        isEnglish = isEnglish ? false : true;
        AnimatorHelper.flipView(mContext, cardView, this);

    }

    @Override
    public void onFlipAnimEnded() {
        if (isEnglish) {
            setEnglishWord(current_word);

        } else {
            setRussianWord(current_word);
        }
    }

    @Override
    public void onDataSetChanged() {
        current_word = (WordInterface) adapter.getRandomItem();
        if (current_word != null) showWordOnCard(current_word);
    }

    @Override
    public void onPositiveButtonClicked() {
        onSwipedLeft();
    }

    @Override
    public void onNegativeButtonClicked() {
        onSwipedRight();
    }


}