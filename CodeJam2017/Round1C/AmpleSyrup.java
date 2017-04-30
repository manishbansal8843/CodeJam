import java.util.Scanner;


public class AmpleSyrup {
	int total;
	int req;
	int[][] pancakes;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		AmpleSyrup ampleSyrup = new AmpleSyrup();
		String[] res = new String[tc];
		for(int i=0;i<tc;i++){
			ampleSyrup.total = sc.nextInt();
			ampleSyrup.req =  sc.nextInt();
			ampleSyrup.pancakes = new int[ampleSyrup.total][2];
			for(int j=0;j<ampleSyrup.total;j++){
				ampleSyrup.pancakes[j][0] =  sc.nextInt();
				ampleSyrup.pancakes[j][1] =  sc.nextInt();
				
			}
			res[i]=ampleSyrup.getMaxSurfaceArea();
		}
		printArray(res);
	}

	private static void printArray(String[] results) {
		int iLen = results.length;

		for (int i = 0; i < iLen; i++) {
			System.out.println("Case #"+(i+1)+": "+results[i]);
			
		}
	}
	private  String getMaxSurfaceArea() {
		int[][] radSortedPanCakes = createColumnSortedmatrix(0);
		int[][] prodSortedPanCakes = createProductSortedmatrix();
		
		double SA = 0.0;
		for(int i =0;i<radSortedPanCakes.length;i++){
			int tempRad = radSortedPanCakes[i][0];
			int tempht = radSortedPanCakes[i][1];
			for(int k=i+1;k<radSortedPanCakes.length;k++){
				int nextRad = radSortedPanCakes[k][0];
				int nextHt = radSortedPanCakes[k][1];
				if(nextRad==tempRad){
					i++;
					if(tempht<nextHt)
						tempht=nextHt;
				}
				else
					break;
			}
			double tempSA = calculateSA(tempRad,tempht,prodSortedPanCakes);
			if(SA < tempSA)
				SA = tempSA;
		}
		return String.format("%.9f", SA);
	}

	

	private double calculateSA(int tempRad, int tempht, int[][] prodSortedPanCakes) {
		double toparea = Math.PI*tempRad*tempRad;
		double bottomHtArea = 2*Math.PI*tempRad*tempht;
		double sideAreas=0.0;
		boolean found=false;
		int localCount = req;
		for(int i=0;i<localCount-1;i++){
			int rad = prodSortedPanCakes[i][0];
			int ht = prodSortedPanCakes[i][1];
			if(!found && req< total&& rad==tempRad && ht==tempht){
				found=true;
				localCount++;
				if(localCount>total+1)
					return toparea+bottomHtArea+sideAreas;
				continue;
			}
			if(rad>tempRad){
				localCount++;
				if(localCount>total+1)
					return toparea+bottomHtArea+sideAreas;
				continue;
			}
			sideAreas+=2*Math.PI*rad*ht;
		}
		
		return toparea+bottomHtArea+sideAreas;
	}

	private int[][] createColumnSortedmatrix(int i) {
		int[][] ret = new int[pancakes.length][pancakes[0].length];
		ret[0]=pancakes[0];
		int pos=1;
		for(int k =1;k<pancakes.length;k++){
			int temp = pancakes[k][i];
			boolean found=false;
			for(int j=0;j<pos;j++){
				if(ret[j][i]<temp){
					found=true;
					System.arraycopy(ret, j, ret, j+1, pos-j);
					int[][] tempnew = new int[1][2];
					tempnew[0][i]=temp;
					if(i==0){
						tempnew[0][i+1]=pancakes[k][i+1];
					}
					else
						tempnew[0][i-1]=pancakes[k][i-1];
					ret[j]=tempnew[0];
					break;
				}
			}
			if(!found){
				ret[pos][i]=temp;
				if(i==0){
					ret[pos][i+1]=pancakes[k][i+1];
				}
				else
					ret[pos][i-1]=pancakes[k][i-1];
			}
			pos++;
		}
		return ret;
	}
	
	private int[][] createProductSortedmatrix() {
		int[][] ret = new int[pancakes.length][pancakes[0].length];
		ret[0]=pancakes[0];
		int pos=1;
		for(int k =1;k<pancakes.length;k++){
			long temp = (long)pancakes[k][0]*(long)pancakes[k][1];
			boolean found=false;
			for(int j=0;j<pos;j++){
				if((long)ret[j][0]*(long)ret[j][1]<temp){
					found=true;
					System.arraycopy(ret, j, ret, j+1, pos-j);
					int[][] tempnew = new int[1][2];
				
						tempnew[0][0]=pancakes[k][0];
					
						tempnew[0][1]=pancakes[k][1];
					ret[j]=tempnew[0];
					break;
				}
			}
			if(!found){
				
					ret[pos][0]=pancakes[k][0];
				
					ret[pos][1]=pancakes[k][1];
			}
			pos++;
		}
		return ret;
	}

}
