import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CacheSimulator {
	
	Hex2Dec h2d = new Hex2Dec();
	// 1) Single L1 cache with:
	//	16 blocks and 16 bytes / block
	//	16 blocks and 32 bytes / block
	// 2) L1 cache and L2 cache with:
	//  L1: 16 blocks and 16 bytes / block; L2: 64 blocks, 64 bytes / block
	//  L1: 8 blocks and 32 bytes / block; L2: 64 blocks, 64 bytes / block
	private static L1Cache l;
	private static L1Cache_2 l1;
	private static L2Cache l2;
	
	static ArrayList<String> cache = new ArrayList<>();
	private static boolean hasL2cache;
	
	public static void main(String[] args){

		String input = args[0];
		int l1blockSize = Integer.parseInt(args[1]);
		int l1bytesPerBlock = Integer.parseInt(args[2]);
		int l2blockSize = 0;
		try{
			l2blockSize = Integer.parseInt(args[3]);
		}catch(Exception e){
		}
		int l2bytesPerBlock = 0;
		try{
			l2bytesPerBlock = Integer.parseInt(args[4]);
		}catch(Exception e){	
		}
		
		hasL2cache = false;

		if (args.length == 3){ // only 3 arguments supplied
			l = new L1Cache(l1blockSize, l1bytesPerBlock);
		}
		else  if (args.length == 5){ // full 5 arguments supplied
			hasL2cache = true;
			l1 = new L1Cache_2(l1blockSize,l1bytesPerBlock);
			
			if (l2blockSize != 0 | l2bytesPerBlock !=0){
				l2 = new L2Cache(l2blockSize,l2bytesPerBlock);
			}	
		}
		else{
			System.out.print("Please make sure that you enter either 3 or 5 arguments!");
		}
		
		
		// firstly, we'll read each line of input from the memory test set and store it in an array
		try{
			BufferedReader br = new BufferedReader(new FileReader(input));
			String str = br.readLine();
			
			while (str != null){
				cache.add(str);
				str = br.readLine();
			}
			br.close();
		}
		catch (IOException e){
			System.out.println("IO error!");
		}
		// and then, we'll debug print out each and every line of our new array
//		try{
//			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
//			for	(int i=0; i<cache.size(); i++){
//				bw.write(cache.get(i) + "\n");
//			}
//			bw.close();
//		}
//		catch(IOException e){
//			System.out.println("IO error in debug print. . .");
//		}
		if (args.length == 3){ // only 3 arguments supplied
			loadl1();
		}
		else if (args.length == 5){
			loadl2();
		}
	}
	
	// now we need to populate our respective caches
	// 1) Single L1 cache with:
    //   	16 blocks and 16 bytes / block
    //    	16 blocks and 32 bytes / block
	public static void loadl1(){
		for (int i=0; i<cache.size(); i++){
			try{
				l.load(cache.get(i));
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		System.out.println("L1 hits: "+l.getHits());
		System.out.println("L1 misses: "+l.getMisses());
		System.out.println("cycles: "+ l.getCycles());
	}
	
	public static void loadl2(){
		for (int i=0; i<cache.size(); i++){
			try{
				l1.load(cache.get(i));
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		System.out.println("L1 hits: "+l1.getHits());
		System.out.println("L1 misses: "+l1.getMisses());
		System.out.println("L2 hits: "+l2.getHits());
		System.out.println("L2 misses: "+l2.getMisses());
		int cycles =l1.getCycles() + l2.getCycles();
		System.out.println("cycles: "+ cycles);
	}
	
	
    // 2) L1 cache and L2 cache with:
    //    	L1: 16 blocks and 16 bytes / block; L2: 64 blocks, 64 bytes / block
    //    	L1: 8 blocks and 32 bytes / block; L2: 64 blocks, 64 bytes / block
	public void load2(String hex){
			try{
				l2.load(hex);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		//System.out.println("L2 hits: "+l2.getHits());
		//System.out.println("L2 misses: "+l2.getMisses());
		//System.out.println("cycles: "+l2.getCycles());
	}
	
}
