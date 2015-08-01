import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nidhya on 01/08/2015.
 */
public class KCompliment {
public void kComp(int[] inputArray, int k){
    int[] kArray = new int[k/2 +1];
    for (int i = 0; i < inputArray.length; i++) {
        if(inputArray[i]>k)
            continue;
        else if(inputArray[i]<=k/2)
            kArray[inputArray[i]]+=1;
        else
            kArray[k-inputArray[i]]+=1;
    }
    for (int i = 0; i < kArray.length; i++) {
        if(kArray[i]>=2)
            System.out.println("unique pair..("+i+","+(k-i)+")");
    }
}

}
