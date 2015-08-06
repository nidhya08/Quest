import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.*;

import com.google.common.collect.*;

/**
 * Created by Nidhya on 03/08/2015.
 */
public class FrequentPhrase {

    public void findMostFreqPhrase(String path,int wordCount) throws IOException {

        File inputFile = new File(path);
        LineIterator lineIterator;
        Map<String,Integer> freqMap = new HashMap<String,Integer>();
        ValueComparator valueComp;
        Map<String,Integer> sortedFreqMap;
        int rank =1;
        try {

            lineIterator = FileUtils.lineIterator(inputFile,"UTF-8");
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine();

                for (String fragment:line.split("\\|")) {
                    fragment = fragment.trim();
                    if(freqMap.get(fragment)!=null){
                        freqMap.put(fragment,freqMap.get(fragment)+1);
                    } else {
                        freqMap.put(fragment,1);
                    }
                }
            }

            valueComp = new ValueComparator(freqMap);
            sortedFreqMap = new TreeMap<String,Integer>(valueComp);
            sortedFreqMap.putAll(freqMap);
            Iterator it = sortedFreqMap.entrySet().iterator();
            while (it.hasNext() && rank<=wordCount){
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(rank+": "+pair.getKey()+ " "+pair.getValue());
                rank++;
            }


            // note that Scanner suppresses exceptions
            LineIterator.closeQuietly(lineIterator);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputFile != null) {
                inputFile.exists();
            }
//            LineIterator.closeQuietly(lineIterator);
        }
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
