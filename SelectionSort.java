import java.util.Arrays;

class SelectionSort
{
	public int findMinValIndex(int[] inputArray, int maxLimit){
		
		int minValue = inputArray[0];
		int minIndex = 0;
		
		for(int i=0; i<= maxLimit; i++){
			if(minValue >= inputArray[i]){
				minValue = inputArray[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public void selectionSort(int[] inputArray){
		
		for(int i=inputArray.length-1; i>0; i--){
			int minIndex = findMinValIndex(inputArray,i);
			
			//swapping the minimum value with the leftmost unsorted element
			int temp = inputArray[minIndex];
			inputArray[minIndex] = inputArray[i];
			inputArray[i] = temp;
		}
		System.out.println("Selection sort:\nSorted sequence:\n");
		for(int i =0; i<inputArray.length; i++)
			System.out.print(inputArray[i]+"\t");
	}
	
	public static void main(String[] args){
		SelectionSort sortObj = new SelectionSort();
		int[] givenSeq = Arrays.asList(args).stream().mapToInt(Integer::parseInt).toArray();
		sortObj.selectionSort(givenSeq);
	}
}