package com.albarn.equation;

import android.util.Log;
import android.util.Pair;
import com.albarn.ParseException;

class EquationParser {
    private static final String TAG="equation";

    static String parseEquation(String text) throws ParseException {

        //find range for a, from first index to first coma
        int bIndex=text.indexOf(',');
        Pair<String,Double> vara=parseEquals(text,0,bIndex);
        if(!vara.first.equals("a")){
            throw new ParseException(0,"a");
        }
        Log.i(TAG,"a parsed");

        //range for b, from from first coma to second coma
        int cIndex=text.indexOf(',',bIndex+1);
        Pair<String,Double> varb=parseEquals(text,bIndex+1,cIndex);
        if(!varb.first.equals("b")){
            throw new ParseException(bIndex+1,"b");
        }
        Log.i(TAG,"b parsed");

        //range for c, from second coma to the end of expression
        Pair<String,Double> varc=parseEquals(text,cIndex+1,text.length());
        if(!varc.first.equals("c")){
            throw new ParseException(cIndex+1,"c");
        }
        Log.i(TAG,"c parsed");

        //get double values
        double a=vara.second,b=varb.second,c=varc.second;

        //calculate discriminant in 2 power
        double d2=b*b-4*a*c;

        //then, return the result
        if(d2<0){
            return "no real solution";
        }else if(d2==0){
            return "answer: "+(-b/(2.0*a));
        }else{
            double d=Math.sqrt(d2);
            return "answer: "+(d-b)/(2.0*a)+"; "+(-d-b)/(2.0*a);
        }
    }

    private static Pair<String,Double> parseEquals(String text, int l, int r) throws ParseException {
        //find separator for name & value
        int equalsIndex=text.indexOf('=',l);

        //parse name & double
        Pair<String,Double> ans = new Pair<>(
                parseName(text,l,equalsIndex),
                parseDouble(text,equalsIndex+1,r));
        Log.i(TAG,"variable parsed: "+ans.first+"="+ans.second);
        return ans;
    }

    private static String parseName(String text, int l, int r) throws ParseException {
        String ans="";

        //first character in name must be letter
        if(Character.isLetter(text.charAt(l))){
            ans+=text.charAt(l);

            //the other ones must be letter or digit
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

    private static double parseDouble(String text, int l, int r) throws ParseException {
        //parse double with Double.parseDouble() method
        try{
            double ans=Double.parseDouble(text.substring(l,r));
            Log.i(TAG,"double parsed: "+ans);
            return ans;
        }catch(Exception e) {
            throw new ParseException(l,"decimal number");
        }
    }
}
