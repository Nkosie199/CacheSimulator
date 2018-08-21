
public class L1Cache_2 {
	// 1) Single L1 cache with:
	//	16 blocks and 16 bytes / block
	//	16 blocks and 32 bytes / block
	private static String[] data;
	private static String[] tags;
	private static String[] indices;
	private static Hex2Dec converter = new Hex2Dec();
	private static CacheSimulator cs = new CacheSimulator();
	
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
		boolean isHit = false;
		//System.out.println("Hash index = " + hashIndex);
		if (tags[hashIndex] == null){ // 1st check: is hash index empty or full ?
			//System.out.println(hex + " Miss!");
			missCounter++;
			//cycles += 1000;
			// load tag into given index
			String binary = converter.hex2Binary(hex)+"";
			tags[hashIndex] = tag;
			indices[hashIndex] = index;
			data[hashIndex] = binary;
			// AND THIS MUST GET LOADED INTO L2 CACHE IN COMPLIANCE WITH THE INCLUSIVE POLICY
			L2Cache.tags[hashIndex] = tag;
			L2Cache.indices[hashIndex] = index;
			L2Cache.data[hashIndex] = binary;
			
		}
		else{ // ie. hash index contains a value . . .
			String binary = converter.hex2Binary(hex)+"";
			if (indices[hashIndex].equals(index)){ // 2nd check: are both tags equal ? 
				if (tags[hashIndex].equals(tag)){ //final check: does the data match ?
					//System.out.println(hex + " Hit!");
					isHit = true;
					hitCounter++;
					cycles += 10;
				}
				else{
					//System.out.println(hex + " Miss!");
					missCounter++;
					//cycles += 1000;
				}
			}
			else{
				//System.out.println(hex + " Miss!");
				missCounter++;
				//cycles += 1000;
			}
		}
		
		if (isHit == false){ // if not a hit (ie. is a miss), load into the L2Cache
			load2(hex);
		}
		
	}
	
	public L1Cache_2(int noOfBlocks, int bytesPerBlock){
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
		//System.out.println("");
		//System.out.println("Full Binary Address = "+ blockAddress);
		
		//int lengthOfOffset = log2( bytesPerBlock) = 5 (if bytesPerBlock = 32)
		int lengthOfOffset = (int) ( Math.log(bytesPerBlock)/ Math.log(2));
		//int lengthOfIndex = log2 ( noOfBlocks) = 4
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
	
	public static void load2(String hex){
			try{
				cs.load2(hex);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		//System.out.println("L2 hits: "+l2.getHits());
		//System.out.println("L2 misses: "+l2.getMisses());
		//System.out.println("cycles: "+l2.getCycles());
	}
	
}
