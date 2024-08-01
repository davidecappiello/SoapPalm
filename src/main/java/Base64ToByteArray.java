import java.util.Base64;

public class Base64ToByteArray {

    public static void main(String[] args) {
        String base64String = "TGluZ3VhZ2dpbyBKYXZhIGUgcHJvZ3JhbW1hemlvbmUgT2JqZWN0Ck9yaWVudGVkKERhdGkgcHJpbWl0aXZpIGUgY29tcGxlc3NpLCBTaW50YXNzaSBiYXNlLApFcmVkaXRhcmlldMOgLCBQb2xpbW9yZmlzbW8sIERhdGEgSGlkaW5nLCBGdW56aW9uaSBlCnBhcmFtZXRyaSwgQ2ljbGkgaXRlcmF0aXZpIGZpbml0aSBlIG5vbiBmaW5pdGksIENsYXNzaSwKQ29zdHJ1dHRvcmkpCkxpbmd1YWdnaSBkaSBNYXJrdXAgcGVyIFdlYiAtIEhUTUwgQ1NTIEJvb3RzdHJhcApEYXRhYmFzZSBlIGxpbmd1YWdnaW8gU1FMKERETCxETUwsRENMKQpTdmlsdXBwbyBhcHBsaWNhdGl2aSB3ZWIgdHJhbWl0ZSBKMkVFIGNvbiBsb2dpY2EKTVZDKEpTUCxTZXJ2bGV0LCBKU1RMLCBFeHByZXNzaW9uIExhbmd1YWdlKQpMaW5ndWFnZ2lvIEphdmFTY3JpcHQgVmFuaWxsYSBlCmF2YW56YXRvKFNpbnRhc3NpLEpTT04sQUpBWCkKU2Vydml6aSBXZWIgdHJhbWl0ZSBTcHJpbmdCb290IGUgUXVhcmt1cyBjb24KdGVjbm9sb2dpYSBSRVNUCkFuZ3VsYXIgKENvbXBvbmVudGksIE1ldG9kaSkK";
        byte[] byteArray = Base64.getDecoder().decode(base64String);
        System.out.println("Lunghezza dell'array di byte: " + byteArray.length);
        System.out.println("Contenuto dell'array di byte:");
        for (byte b : byteArray) {
            System.out.printf("%02x ", b);
        }
    }
}
