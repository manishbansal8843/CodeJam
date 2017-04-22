import java.util.Scanner;


public class CounterProblemSmallSet {

	public static void main(String[] args) {
		
		int[] counts = new int[1000001];
		calculateAllCounts(counts);
		
		Scanner sc=new Scanner(System.in);
		int tc = sc.nextInt();
		int[] res=new int[tc];

		for(int i=0;i<tc;i++){
			int num = sc.nextInt();
			res[i]=counts[num];
		}
		
		printArray(res);
	}
	
	private static void calculateAllCounts(int[] counts) {

		counts[1]=1;
		for(int i=2;i<counts.length;i++){
			int y = reverse(i);
			
			
			if(counts[i]==0){
				counts[i]=counts[i-1]+1;
			}
			else{
				counts[i]=Math.min(counts[i], counts[i-1]+1);
			}
			if(counts[y]==0){
				counts[y]=counts[i]+1;
			}
			else{
				counts[y]=Math.min(counts[y], counts[i]+1);
			}
		}
	}

	private static int reverse(int i) {
		int rev=0;
		while(i!=0){
			rev=rev*10+i%10;
			i=i/10;
		}
		return rev;
	}

	private static void printArray(int[] results) {
		int iLen = results.length;

		for (int i = 0; i < iLen; i++) {
			System.out.println("Case #"+(i+1)+": "+results[i]);
			
		}
	}

}
