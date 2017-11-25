

// Auditor class calls the Log Server and verifies its behavior on various inputs
public class Auditor {
	
	public Auditor (){
		
	}
	
	// takes an instance of the Log Server and retrieves the current size and root and hash for the log
	public Auditor(int i){
		
	}
	
	/*verifies that an event exists in the current log by calling genPath to obtain an audit path and 
	 * verifying the hashes on the audit path: the path should begin with a hash of the event and end 
	 * with the root hash, and all hashes on the path should be correctly computed. Note that this method 
	 * does not use any knowledge of the full Merkle
	 */
	public void isMember(){
		
	}

}
