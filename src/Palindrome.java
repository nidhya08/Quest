/**
 * Created by Nidhya on 01/08/2015.
 */
public class Palindrome {

    public boolean isPalindrome(String testWord){
        boolean flag=true;
        char testWordArray[] = testWord.toLowerCase().toCharArray();
        for (int i = 0, j=testWordArray.length-1; i < testWordArray.length; i++,j--) {
            if(testWordArray[i]!=testWordArray[j]){
                flag=false;
                break;
            }
        }

        return flag;
    }
}
