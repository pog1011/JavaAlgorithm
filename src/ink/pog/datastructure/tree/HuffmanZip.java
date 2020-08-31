package ink.pog.datastructure.tree;

import java.io.*;
import java.util.Map;

public class HuffmanZip {

    public static void main(String[] args) {
        HuffmanEncode huffmanEncode = new HuffmanEncode();
        HuffmanDecode huffmanDecode = new HuffmanDecode();
        String target = "C:\\Users\\Pog\\Desktop\\photo\\ss.png";
        String origin = "C:\\Users\\Pog\\Desktop\\photo\\ss.zip";
        FileInputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;

        //解压
        try {
            is = new FileInputStream(origin);
            ois = new ObjectInputStream(is);
            byte[] huffmanCode = (byte[]) ois.readObject();
            Map<Byte, String> characterCode = (Map<Byte, String>) ois.readObject();
            String binaryDecode = huffmanDecode.getBinaryDecode(huffmanCode);
            byte[] contentBytes = huffmanDecode.getContentBytes( characterCode,binaryDecode);
            os = new FileOutputStream(target);
            os.write(contentBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //压缩
//        try {
//            is = new FileInputStream(origin);
//            byte[] bytes = new byte[is.available()];
//            is.read(bytes);
//            byte[] bytesEncode = huffmanEncode.getHuffmanEncode(bytes);
//            os = new FileOutputStream(target);
//            oos = new ObjectOutputStream(os);
//            oos.writeObject(bytesEncode);
//            oos.writeObject(huffmanEncode.getCharacterEncode());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }


}
