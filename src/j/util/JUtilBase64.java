package j.util;

import java.util.Base64;

public final class JUtilBase64{
	/**
	 * 
	 * @param binaryData
	 * @return
	 */
	public static String encode(byte[] binaryData){
		return encode(binaryData, true);
	}

	/**
	 * 
	 * @param encoded
	 * @return
	 */
	public static byte[] decode(String encoded){
		return decode(encoded, true);
	}
	
	/**
	 * 
	 * @param binaryData
	 * @param useMimeEncoder
	 * @return
	 */
	public static String encode(byte[] binaryData, boolean useMimeEncoder){
		if(useMimeEncoder) return Base64.getMimeEncoder().encodeToString(binaryData);
		else return Base64.getEncoder().encodeToString(binaryData);
	}

	/**
	 * 
	 * @param encoded
	 * @param useMimeDecoder
	 * @return
	 */
	public static byte[] decode(String encoded, boolean useMimeDecoder){
		if(useMimeDecoder) return Base64.getMimeDecoder().decode(encoded);
		else return Base64.getDecoder().decode(encoded);
	}
}
