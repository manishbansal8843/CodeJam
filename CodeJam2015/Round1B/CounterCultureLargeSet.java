import java.util.Scanner;


public class CounterProblemLargeSet {

	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int tc = sc.nextInt();
		long[] res=new long[tc];

		for(int i=0;i<tc;i++){
			long num = sc.nextLong();
			res[i]=getCounts(num);
		}
		printArray(res);
	}

	private static long getCounts(long num) {
		long count=0;
		long base=1;
		while(base*10<=num){
			base=base*10;
		}
			long x=1;
			count++;
			while(x<base){
				long subbase=x*10;
				int numDigit = countNoOfDigits(x);
				int modulo = (int)Math.pow(10,(numDigit - (numDigit/2)));
			

					while(x%modulo!=modulo-1){
						x++;
						count++;
					}
					long y=reverse(x);
					if(x!=y){
					count++;
					x=y;
					}
					while(x<subbase){
						x++;
						count++;
					}
				
				
			}
			if(base==num){
			return count;
			}
			else{
				int numDigit = countNoOfDigits(num);
				int modulo = (int)Math.pow(10,(numDigit - (numDigit/2)));
				
				if(num%modulo==0){
					num--;
					count++;
				}
				long initRev = reverse(num/modulo);
				while(x%modulo!=initRev){
					x++;
					count++;
				}
				long y=reverse(x);
				if(x!=y){
				count++;
				x=y;
				}
				while(x<num){
					x++;
					count++;
				}
				
			}
		return count;
	}
	
	private static int countNoOfDigits(long x) {
		int count=0;
		while(x>0){
			x=x/10;
			count++;
		}
		return count;
	}

	private static long reverse(long i) {
		long rev=0;
		while(i!=0){
			rev=rev*10+i%10;
			i=i/10;
		}
		return rev;
	}
	private static void printArray(long[] results) {
		int iLen = results.length;

		for (int i = 0; i < iLen; i++) {
			System.out.println("Case #"+(i+1)+": "+results[i]);
			
		}
	}

}
