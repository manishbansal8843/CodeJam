import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class AlphabetCake {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][] results;
		AlphabetCake alphabetCake=new AlphabetCake();
		
		try {
			String strNoOfTestCases = br.readLine().trim();
			int iNoOfTestCases = Integer.parseInt(strNoOfTestCases);
			results = new String[iNoOfTestCases][];
			for (int i = 0; i < iNoOfTestCases; i++) {
				

				StringTokenizer tokenizer = new StringTokenizer(br.readLine()
						.trim());
				int R = Integer.parseInt(tokenizer.nextToken().trim());
				//int C = Integer.parseInt(tokenizer.nextToken().trim());
				String[] inp= new String[R];
				
				for(int k=0;k<R;k++){
					inp[k]=br.readLine().trim();
				}
				
				results[i] = alphabetCake.getCake(inp);
				
		
			}
			printArray(results);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	
	}
	
	private static void printArray(String[][] results) {
		int iLen = results.length;

		for (int i = 0; i < iLen; i++) {
			System.out.println("Case #"+(i+1)+":");
			for (int k = 0; k < results[i].length; k++) {

			System.out.println(results[i][k]);
			}
		}
	}

	private  String[] getCake(String[] inp) {
		char[][] ca=new char[inp.length][inp[0].length()];
		char filler='0';
		for(int i=0;i<inp.length;i++){
			ca[i] = inp[i].toCharArray();
		}
		for(int i=0;i<inp.length;i++){
			for(int k=0;k<ca[i].length;k++){
				if(ca[i][k]=='?' ){
					if(filler=='0')
					continue;
					else
						ca[i][k]=filler;
				}
				else{
					filler=ca[i][k];
				}
			}
			filler='0';
		}
		
		for(int i=0;i<inp.length;i++){
			for(int k=ca[i].length-1;k>=0;k--){
				if(ca[i][k]=='?' ){
					if(filler=='0')
					continue;
					else
						ca[i][k]=filler;
				}
				else{
					filler=ca[i][k];
				}
			}
			filler='0';
		}
		int iFillerRow=-1;
		
		for(int i=0;i<inp.length;i++){
			if(ca[i][0]=='?' ){
				if(iFillerRow==-1)
				continue;
				else{
					ca[i]=ca[iFillerRow];
					
				}
			}
			else{
				iFillerRow=i;
			}
				
		}
		iFillerRow=-1;
		for(int i=inp.length-1;i>=0;i--){
			if(ca[i][0]=='?' ){
				if(iFillerRow==-1)
				continue;
				else{
					ca[i]=ca[iFillerRow];
					
				}
			}
			else{
				iFillerRow=i;
			}
				
		}
		
		return buildStringArray(ca);
	}

	private String[] buildStringArray(char[][] ca) {
		String[] res=new String[ca.length];
		for(int i=0;i<ca.length;i++){
			StringBuilder sb=new StringBuilder();

			for(int k=0;k<ca[i].length;k++){
				sb.append(ca[i][k]);
			}
			res[i]=sb.toString();
		}
		return res;
	}
}
