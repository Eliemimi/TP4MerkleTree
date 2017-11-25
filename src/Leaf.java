import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Leaf {

		String singleEventInLogFile;
		
		HashMap<String,byte[]> stringToHashValue=new HashMap<>();

		Leaf(String singleEventInLogFile) throws NoSuchAlgorithmException {
			this.singleEventInLogFile = singleEventInLogFile;
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashOfSingleEventInLogFileWithoutPrefix =null;
			if (digest != null) {
				hashOfSingleEventInLogFileWithoutPrefix =  digest.digest(singleEventInLogFile.getBytes(StandardCharsets.UTF_8));
			}
			byte prefix=0x00;
			byte[] finalHashOfSingleEventInLogFile=Util.prependAndHash(prefix,hashOfSingleEventInLogFileWithoutPrefix);
			stringToHashValue.put(singleEventInLogFile, finalHashOfSingleEventInLogFile);
		}
		
		public HashMap<String, byte[]> getStringToHashValue() {
			return stringToHashValue;
		}

		public void setStringToHashValue(HashMap<String, byte[]> stringToHashValue) {
			this.stringToHashValue = stringToHashValue;
		}
	}