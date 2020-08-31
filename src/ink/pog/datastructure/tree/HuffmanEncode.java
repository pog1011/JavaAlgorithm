package ink.pog.datastructure.tree;

import java.nio.charset.CharsetEncoder;
import java.util.*;

public class HuffmanEncode {
    //把原来的字符的 Ascii 转换为 HuffmanTree 的编码，Byte 为 Ascii 编码，string 为 HuffmanTree 编码
    private  Map<Byte, String> characterCode = new HashMap<>();
    // HuffmanTree 的 root 节点
    private CharNode huffmanTree;
    //获取每个字符的个数
    public Map<Byte, Integer> getCharacterCount(byte[] content) {
        HashMap<Byte, Integer> characterCount = new HashMap<>();
        for (byte character : content) {
            Integer count = characterCount.get(character);
            if (count == null) {
                characterCount.put(character, 1);
            } else {
                characterCount.put(character, count + 1);
            }
        }
        return characterCount;
    }
    //根据每个字符的个数创建一个存在字符的 Ascii 编码和个数
    public List<CharNode> createNodes(Map<Byte, Integer> characterCount) {
        List<CharNode> nodes = new ArrayList<>();
        characterCount.forEach((key, value) -> {
            nodes.add(new CharNode(key, value));
        });
        return nodes;
    }

    //创建一个 HuffmanTree 的 root 节点
    public void setHuffmanTree(List<CharNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CharNode leftNode = nodes.get(0);
            CharNode rightNode = nodes.get(1);
            CharNode parent = new CharNode(null, leftNode.count + rightNode.count);
            parent.leftNode = leftNode;
            parent.rightNode = rightNode;
            nodes.add(parent);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        huffmanTree = nodes.get(0);
    }



    /**
     * 转换 Huffman 编码
     * @param content   要转换的 byte 数组
     * @param characterEncode   转换的字符编码
     * @return  转换之后的 byte 数组
     */
    public byte[] setHuffmanEncode(byte[] content, Map<Byte, String> characterEncode) {
        StringBuilder builder = new StringBuilder();
        for (byte character : content) {
            //获取要转换的字符然后转换为 Huffman 编码
            String characterCode = characterEncode.get(character);
            builder.append(characterCode);
        }
        String contentEncode = builder.toString();
        //因为要存在的 byte 数组为八位二进制的
        int len = (contentEncode.length() + 7) / 8;
        int index = 0;
        byte[] huffmanEncode = new byte[len];
        for (int i = 0; i < contentEncode.length(); i += 8) {
            String substring = null;
            if(i + 8 > contentEncode.length()){
                substring = contentEncode.substring(i);
            }else{
                 substring = contentEncode.substring(i, i + 8);
            }
            huffmanEncode[index++] = (byte)Integer.parseInt(substring,2);
        }
        return huffmanEncode;
    }

    //获取转为 Huffman 编码的 byte 数组
    public byte[] getHuffmanEncode(byte[] content){
        Map<Byte, Integer> characterCount = getCharacterCount(content);
        List<CharNode> nodes = createNodes(characterCount);
        setHuffmanTree(nodes);
        Map<Byte, String> characterEncode = getCharacterEncode();
        byte[] huffmanEncode = setHuffmanEncode(content, characterEncode);
        return huffmanEncode;
    }

    public Map<Byte, String> getCharacterEncode(){
        this.setCharacterEnCode(this.huffmanTree,"",new StringBuilder());
        return this.characterCode;
    }

    /**
     *将字符转换为 Huffman 编码
     * @param root  HuffmanTree 节点
     * @param direction 转换为 Huffman 编码的规则为 左节点为0 右节点为1
     * @param stringBuilder 存储节点路径上的 0 或者 1
     */
    public void setCharacterEnCode(CharNode root, String direction, StringBuilder stringBuilder) {
        stringBuilder = new StringBuilder(stringBuilder);
        stringBuilder.append(direction);
        if (root != null) {
            if (root.character == null) {
                setCharacterEnCode(root.leftNode, "0", stringBuilder );
                setCharacterEnCode(root.rightNode, "1", stringBuilder );
            } else {
                characterCode.put(root.character, stringBuilder.toString());
            }
        }
    }

}


class CharNode implements Comparable<CharNode> {

    Byte character;
    Integer count;
    CharNode leftNode;
    CharNode rightNode;

    public CharNode(Byte character, Integer count) {
        this.character = character;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CharNode{" +
                "character=" + character +
                ", count=" + count +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }

    @Override
    public int compareTo(CharNode o) {
        return this.count - o.count;
    }
}