package com.android.lee.hashtagproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <PRE>
 *     HashTag TextView
 * </PRE>
 * Created by Lee on 2017. 09. 08..
 */

public class HashTagTextView extends AppCompatTextView{

    private String mPrefix = "#";
    private HashTagClickListener mHashClickListener;

    public HashTagTextView(Context context) {
        super(context);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public HashTagTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public HashTagTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setHashClickListener(HashTagClickListener pClickListener){
        mHashClickListener = pClickListener;
    }

    public void setPrefixText(String pPrefix){
        mPrefix = pPrefix;
    }

    /**
     * TextView 에 글을 등록하기 전 HashTag 를 찾아 HashTagSpan 을 등록후 글을 셋팅
     * @param text TextView 에 등록할 전체 텍스트
     */
    public void setText(String text){
        ArrayList<int[]> spansList = getSpans(text, mPrefix);
        SpannableString spanTest = new SpannableString(text);

        for(int[] span : spansList){
            spanTest.setSpan(new HashSpan(getContext(), mHashClickListener), span[0], span[1], 0);
        }

        super.setText(spanTest);
    }

    /**
     * HashTag Span 을 등록할 위치를 찾아 List로 반환
     * @param body 전체 텍스트
     * @param prefix HashTag 로 등록할 특수문자 Default('#')
     * @return HashTag 로 등록할 위치가 담긴 int[]를 담고있는 ArrayList
     */
    private ArrayList<int[]> getSpans(String body, String prefix){
        ArrayList<int[]> spans = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        Pattern pattern = Pattern.compile(prefix + "([A-zㄱ-ㅎ가-힣])\\w*");
        Matcher matcher = pattern.matcher(body);

        while (matcher.find()) { //태그 글자를 찾아 반복
            int[] currentSpan = new int[2];
            currentSpan[0] = matcher.start();
            currentSpan[1] = matcher.end();

            boolean isEquals = true;

            if(temp.size() > 0){ //처음이 아닌 경우
                for(String msg : temp){ //같은 글자는 태그가 걸리지 않게 걸러내기 위한 반복문
                    if(msg.toUpperCase().equals(body.toUpperCase().substring(matcher.start(), matcher.end()))){
                        isEquals = false;
                        break;
                    }
                }
            }else{//처음인 경우
                temp.add(body.substring(matcher.start(), matcher.end()));
            }

            if(isEquals){//같은 글자가 없는 경우
                temp.add(body.substring(matcher.start(), matcher.end()));
                spans.add(currentSpan);
            }
        }

        return  spans;
    }
}
