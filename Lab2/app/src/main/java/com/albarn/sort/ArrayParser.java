package com.albarn.sort;

import android.util.Log;
import android.util.Pair;
import com.albarn.ParseException;

import java.util.ArrayList;

public class ArrayParser {
    private static final String TAG="com.albarn.calculation";

    //parse named array from string NAME "=" ARRAY
    public static Pair<String,double[]> parseNamedArray(String text) throws ParseException {
        //find separator index
        int equalIndex=text.indexOf('=');
        //convert first & second part into name & array
        return new Pair<>(
                parseName(text,0,equalIndex),
                parseArray(text,equalIndex+1,text.length())
        );
    }

    //parse name that starts with letter and contains letters or digits
    private static String parseName(String text, int l, int r) throws ParseException {
        String ans="";
        //check first char
        //it must be letter
        if(Character.isLetter(text.charAt(l))){
            ans+=text.charAt(l);

            //check rest
            //it must be letter or digit
            for(int i=l+1;i<r;i++){
                char a=text.charAt(i);
                if(Character.isLetterOrDigit(a)){
                    ans+=a;
                }
                else {
                    throw new ParseException(i,"letter or digit");
                }
            }
            Log.d(TAG,"name parsed: "+ans);
            return ans;
        }
        else{
            throw new ParseException(l,"letter");
        }
    }

    //parse array from string SIZE "(" [DOUBLE [{,DOUBLE}] ")"
    private static double[] parseArray(String text, int l, int r) throws ParseException {
        int openGapIndex=text.indexOf('(');

        //get size & make ans array with it
        int size=parseInt(text,l,openGapIndex);
        double[] ans=new double[size];

        //parse numbers inside the gaps
        int num_l=0,num_r=0;
        for(int i=0;i<size;i++){
            if(i==0){
                num_l=openGapIndex+1;
            }else{
                num_l=num_r+1;
            }
            if(i<size-1){
                num_r=text.indexOf(',',num_l+1);
            }else{
                num_r=text.indexOf(')',num_l+1);
            }
            ans[i]=parseDouble(text,num_l,num_r);
        }

        Log.d(TAG,"array parsed: "+size);
        return ans;
    }

    //parse integer using Integer.parseInt() method
    private static int parseInt(String text, int l, int r) throws ParseException {
        try{
            int ans=Integer.parseInt(text.substring(l,r));
            Log.d(TAG,"integer parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }

    //parse decimal using Double.parseDouble() method
    private static double parseDouble(String text, int l, int r) throws ParseException {
        try{
            double ans=Double.parseDouble(text.substring(l,r));
            Log.d(TAG,"double parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }
}
