import java.util.ArrayList;
import java.util.List;

public class ReverseWords {
    public static void main(String []args ){
        String sentence = "She is naughty an stubborn.";
        String [] wordList = sentence.split(" ");
        //List<String> wordArr = new ArrayList<>();
        String word = "";
        //String lastWord = null;
        for(int i=wordList.length-1;i>=0;i--){
            //wordArr.add(wordList[i]);

            word = word+wordList[i] +" ";
            //lastWord=wordList[i];
        }
        //String lastWord = word.(0,word)
         int dot = word.indexOf(".");
        String lastWord = "."+word.substring(0,dot);
        System.out.println(lastWord);
        word.replace(word.substring(0,dot+1),lastWord);
        System.out.println((word.replace(word.substring(0,dot+1),lastWord)));
        //}
    }
}
