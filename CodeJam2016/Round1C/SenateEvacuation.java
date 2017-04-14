import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class SenateEvacuation {

	public static final String PARTIES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		SenateEvacuation evacuation = new SenateEvacuation();
		Scanner sc = new Scanner(System.in);
		int iNoOfTestCases = sc.nextInt();
		String[] results = new String[iNoOfTestCases];
		int[] partyMembers = null;
		for(int i=0;i<iNoOfTestCases;i++){
			int iNoOfParties = sc.nextInt();
			
			partyMembers = new int[iNoOfParties];
			for(int j=0;j<iNoOfParties;j++){
				partyMembers[j] = sc.nextInt();
				
			}
			results[i] = evacuation.getResults(partyMembers);
		}
		printArray(results);
	}

	private String getResults(int[] partyMembers) {
		ArrayList<String> seatingArrangement = new ArrayList<String>();
		int MembersRemainingParty=0;
		for(int i=1;i<partyMembers.length;i++){
			do{
				partyMembers[MembersRemainingParty]=partyMembers[MembersRemainingParty]-1;
				partyMembers[i]=partyMembers[i]-1;
				seatingArrangement.add(new StringBuilder().append(PARTIES.charAt(MembersRemainingParty)).append(PARTIES.charAt(i)).toString());
			}while(partyMembers[i]!=0 && partyMembers[MembersRemainingParty]!=0);
			
			if(partyMembers[i]!=0)
				MembersRemainingParty=i;
			else if(partyMembers[i]==0 && partyMembers[MembersRemainingParty]==0){
				
				if(i+2<partyMembers.length){
					MembersRemainingParty=++i;
					
				}
				else{
					break;
				}
			}
			
			
		}
		for(int i=0;i<partyMembers.length;i++){
			if(partyMembers[i]!=0){
				while(partyMembers[i]>=2){
					seatingArrangement.add(new StringBuilder().append(PARTIES.charAt(i)).append(PARTIES.charAt(i)).toString());
					partyMembers[i]=partyMembers[i]-2;
				}
				if(partyMembers[i]!=0){
					seatingArrangement.add(new StringBuilder().append(PARTIES.charAt(i)).toString());
				}
				break;
			}
		}
		
		Collections.reverse(seatingArrangement);
		return buildString(seatingArrangement);
	}

	private String buildString(ArrayList<String> seatingArrangement) {
		int len = seatingArrangement.size();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++){
			sb.append(seatingArrangement.get(i)+" ");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}

	
	private static void printArray(String[] results) {
		int iLen = results.length;

		for (int i = 0; i < iLen; i++) {
			System.out.println("Case #"+(i+1)+": "+results[i]);
			
		}
	}

}
