import java.util.Arrays;

class InsertionSort {

public int[] insertVal(int[] inputArray, int sortLength){
	int key = inputArray[sortLength];
	while(key < inputArray[sortLength-1] && sortLength > 0){
		inputArray[sortLength] = inputArray[sortLength-1];
		inputArray[sortLength-1] = key;
		if(sortLength == 1)
			break;
		sortLength--;
	}
	return inputArray;
}
public void insertionSort(int[] inputArray){
	int length = inputArray.length;
	for(int i=1; i<length; i++){
		if(inputArray[i] < inputArray[i-1]){
			inputArray = insertVal(inputArray,i);
		}
	}
	
	System.out.println("Insertion Sort Result:\n");
	for(int i=0; i< length; i++){
		System.out.print(inputArray[i]+"\t");
	}
}
public static void main(String[] args){
	InsertionSort insertObj = new InsertionSort();
	int[] givenSeq = Arrays.asList(args).stream().mapToInt(Integer::parseInt).toArray();
	insertObj.insertionSort(givenSeq);
}
}