package com.albarn.equation;

import android.util.Log;
import android.util.Pair;
import com.albarn.ParseException;

class EquationParser {
    static String parseEquation(String text) throws ParseException {
        int bIndex=text.indexOf(',');
        Pair<String,Double> vara=parseEquals(text,0,bIndex);
        if(!vara.first.equals("a")){
            throw new ParseException(0,"a");
        }
        Log.i("albarn","a parsed");
        int cIndex=text.indexOf(',',bIndex+1);
        Pair<String,Double> varb=parseEquals(text,bIndex+1,cIndex);
        if(!varb.first.equals("b")){
            throw new ParseException(bIndex+1,"b");
        }
        Log.i("albarn","b parsed");
        Pair<String,Double> varc=parseEquals(text,cIndex+1,text.length());
        if(!varc.first.equals("c")){
            throw new ParseException(cIndex+1,"c");
        }
        Log.i("albarn","c parsed");
        double a=vara.second,b=varb.second,c=varc.second;

        double d2=b*b-4*a*c;
        if(d2<0){
            return "no solution";
        }else if(d2==0){
            return "answer: "+(-b/(2.0*a));
        }else{
            double d=Math.sqrt(d2);
            return "answer: "+(d-b)/(2.0*a)+"; "+(-d-b)/(2.0*a);
        }
    }

    private static Pair<String,Double> parseEquals(String text, int l, int r) throws ParseException {
        int equalsIndex=text.indexOf('=',l);
        Pair<String,Double> ans = new Pair<>(
                parseName(text,l,equalsIndex),
                parseDouble(text,equalsIndex+1,r));
        Log.i("albarn","variable parsed: "+ans.first+"="+ans.second);
        return ans;
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
            Log.i("albarn","name parsed: "+ans);
            return ans;
        }
        else{
            throw new ParseException(l,"letter");
        }
    }

    private static double parseDouble(String text, int l, int r) throws ParseException {
        try{
            double ans=Double.parseDouble(text.substring(l,r));
            Log.i("albarn","double parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }
}
