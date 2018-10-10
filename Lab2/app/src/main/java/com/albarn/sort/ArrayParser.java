package com.albarn.sort;

import android.util.Log;
import android.util.Pair;
import com.albarn.ParseException;

import java.util.ArrayList;

public class ArrayParser {
    private static final String TAG="ArrayParser";

    public static Pair<String,double[]> parseNamedArray(String text) throws ParseException {
        int equalIndex=text.indexOf('=');
        return new Pair<>(
                parseName(text,0,equalIndex),
                parseArray(text,equalIndex+1,text.length())
        );
    }

    private static String parseName(String text, int l, int r) throws ParseException {
        String ans="";
        if(Character.isLetter(text.charAt(l))){
            ans+=text.charAt(l);
            for(int i=l+1;i<r;i++){
                char a=text.charAt(i);
                if(Character.isLetterOrDigit(a)){
                    ans+=a;
                }
                else {
                    throw new ParseException(i,"letter or digit");
                }
            }
            Log.i(TAG,"name parsed: "+ans);
            return ans;
        }
        else{
            throw new ParseException(l,"letter");
        }
    }

    private static double[] parseArray(String text, int l, int r) throws ParseException {
        int openGapIndex=text.indexOf('(');
        int size=parseInt(text,l,openGapIndex);

        double[] ans=new double[size];

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

        Log.i(TAG,"array parsed: "+size);
        return ans;
    }

    private static int parseInt(String text, int l, int r) throws ParseException {
        try{
            int ans=Integer.parseInt(text.substring(l,r));
            Log.i(TAG,"integer parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }

    private static double parseDouble(String text, int l, int r) throws ParseException {
        try{
            double ans=Double.parseDouble(text.substring(l,r));
            Log.i(TAG,"double parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }
}
