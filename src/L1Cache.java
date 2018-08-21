import java.util.ArrayList;

public class L1Cache {
	// 1) Single L1 cache with:
	//	16 blocks and 16 bytes / block
	//	16 blocks and 32 bytes / block
	private static String[] data;
	private static String[] tags;
	private static String[] indices;
	private static Hex2Dec converter = new Hex2Dec();
	
	int noOfBlocks;
	int bytesPerBlock;
	
	int hitCounter = 0;
	int missCounter = 0;
	int cycles = 0;
	
	String offset;
	String index;
	String tag;
	
	public void load(String hex){
		int hashIndex = hashFunction(hex);
		//System.out.println("Hash index = " + hashIndex);
		if (tags[hashIndex] == null){ // 1st check: is hash index empty or full ?
			//System.out.println(hex + " Miss!");
			missCounter++;
			cycles += 1000;
			// load tag into given index
			String binary = converter.hex2Binary(hex)+"";
			tags[hashIndex] = tag;
			indices[hashIndex] = index;
			data[hashIndex] = binary;
		}
		else{ // ie. hash index contains a value . . .
			String binary = converter.hex2Binary(hex)+"";
			if (indices[hashIndex].equals(index)){ // 2nd check: are both tags equal ? 
				if (tags[hashIndex].equals(tag)){ //final check: does the data match ?
					//System.out.println(hex + " Hit!");
					hitCounter++;
					cycles += 10;
				}
				else{
					//System.out.println(hex + " Miss!");
					missCounter++;
					cycles += 1000;
				}
			}
			else{
				//System.out.println(hex + " Miss!");
				missCounter++;
				cycles += 1000;
			}
		}
		
	}
	
	public L1Cache(int noOfBlocks, int bytesPerBlock){
		this.noOfBlocks = noOfBlocks;
		this.bytesPerBlock = bytesPerBlock;	
		// initiate both data and tags arrays to string arrays of size noOfBlocks
		tags = new String[noOfBlocks];
		data = new String[noOfBlocks];
		indices = new String[noOfBlocks];
	}
	
	// hashIndex = (blockAddress/bytesPerBlock) % noOfBlocks
	public int hashFunction(String hex){
		String blockAddress = converter.hex2Binary(hex);

		int lengthOfOffset = (int) ( Math.log(bytesPerBlock)/ Math.log(2));
		
		int lengthOfIndex = (int) ( Math.log(noOfBlocks)/ Math.log(2));
		// 
		String blockAddress2 = blockAddress + "";
		
		offset = ( blockAddress2.substring(blockAddress2.length() - lengthOfOffset, blockAddress2.length()));
		//System.out.println("Offset: "+offset);
		index = ( blockAddress2.substring(blockAddress2.length() - (lengthOfOffset + lengthOfIndex), blockAddress2.length() - lengthOfOffset));
		//System.out.println("Index: "+index);
		tag = ( blockAddress2.substring(0, blockAddress2.length() - (lengthOfOffset + lengthOfIndex)));
		//System.out.println("Tag: "+tag);
		
		int val = converter.binary2Int(index);
		return val;
	}
	
	public int getHits(){
		return hitCounter;
	}
	
	public int getMisses(){
		return missCounter;
	}
	
	public int getCycles(){
		return cycles;
	}
	
	public static void load2(){
			try{
				//l2.load(cache.get(i));
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		

	}
	
}
