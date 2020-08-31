package ink.pog.datastructure.tree;


import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.util.*;

public class HuffmanDecode {

    public static void main(String[] args) {

        String content = "person";
        HuffmanEncode huffmanEncode = new HuffmanEncode();
        HuffmanDecode huffmanDecode = new HuffmanDecode();
        Map<Byte, String> characterEncode = huffmanEncode.getCharacterEncode();
        byte[] contentHuffmanEncode = huffmanEncode.getHuffmanEncode(content.getBytes());
        String contentDecode = huffmanDecode.getBinaryDecode(contentHuffmanEncode);
        byte[] contentBytes = huffmanDecode.getContentBytes(characterEncode, contentDecode);
        System.out.println(new String( contentBytes));


    }

    /**
     * 获取解码之后的二进制字符串
     * @param contentEncode 要解码的 Byte 数组
     * @return  解码之后的字符串
     */
    public String getBinaryDecode(byte[] contentEncode) {
        int binaryDecode = 0;
        String binaryString = "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < contentEncode.length; i++) {
            if (i != contentEncode.length - 1) {
                binaryDecode = contentEncode[i] | 256;
                binaryString = Integer.toBinaryString(binaryDecode);
            } else {
                binaryDecode = contentEncode[i];
                binaryString = Integer.toBinaryString(binaryDecode);
            }
            if (binaryString.length() >= 8) {
                binaryString = binaryString.substring(binaryString.length() - 8);
            }
            builder.append(binaryString);
        }
        return builder.toString();
    }

    /**
     * 获取解码之后的 Byte 数组
     * @param characterEncode   Huffman 编码后的字符编码
     * @param binaryDecode  要转换的二进制字符串
     * @return  返回解码后的 Byte 数组
     */
    public byte[] getContentBytes(Map<Byte, String> characterEncode, String binaryDecode) {
        Map<String, Byte> characterDecode = new HashMap<>();
        //将 Ascii 和 Huffman 调一下位置，以便可以获取 Ascii 编码
        characterEncode.forEach((key, value) -> {
            characterDecode.put(value, key);
        });

        int leftIndex = 0;
        int rightIndex = 1;

        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        List<Byte> contentBytes = new ArrayList<>();
        while (true) {
            if (rightIndex > binaryDecode.length()) {
                break;
            }
            String code = binaryDecode.substring(leftIndex, rightIndex++);
            Byte character = characterDecode.get(code);
            if (character != null) {
                contentBytes.add(character);
                byteArrayBuffer.write(character);
                leftIndex = rightIndex - 1;
            }
        }
        return byteArrayBuffer.toByteArray();

    }


}
