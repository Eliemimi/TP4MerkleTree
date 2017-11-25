public class Util {

	static byte[] prependAndHash(byte prefix, byte[] hash) {
		byte[] prefixTab = new byte[] { prefix };
		byte[] finalHash = new byte[hash.length + 1];
		System.arraycopy(prefixTab, 0, finalHash, 0, prefixTab.length);
		System.arraycopy(hash, 0, finalHash, prefixTab.length, hash.length);
		return finalHash;
	}
	
}
