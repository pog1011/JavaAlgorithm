package ink.pog.datastructure.stack;

import javax.crypto.Cipher;
import java.util.Stack;

public class SuffixComputer {

    public static boolean isOpera(String opera) {
        return "-".equals(opera) || "+".equals(opera) || "*".equals(opera) || "/".equals(opera) || "(".equals(opera) || ")".equals(opera);
    }

    public static int priority(String opera) {
        if ("+".equals(opera) || "-".equals(opera)) {
            return 1;
        } else if ("*".equals(opera) || "/".equals(opera)) {
            return 2;
        } else if ("(".equals(opera) || ")".equals(opera)) {
            return 3;
        }
        return -1;
    }

    public static int computer(int num1, int num2, String opera) {
        switch (opera) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return 0;
    }

    public static void main(String[] args) {
        ;
        Stack<String> numStack = new Stack<>();
        Stack<String> operaStack = new Stack<>();
        String expression = "1+((2+3)*4)-5";
        int num1 = 0;
        int num2 = 0;
        int priorityCount = 0;
        String keepNum = "";
        String[] expressionList = expression.split("");
        for (String opera : expressionList) {
            if (isOpera(opera)) {
                if(!keepNum.equals("")){
                    numStack.push(keepNum);
                    keepNum = "";
                }
                if (operaStack.isEmpty()) {
                    operaStack.push(opera);
                } else {
                    int topOperaPriority = priority(operaStack.peek());
                    int currentOperaPriority = priority(opera);
                    if (currentOperaPriority == 3) {
                        if (opera.equals("(")) {
                            operaStack.push(opera);
                            priorityCount++;
                            continue;
                        }
                        if (opera.equals(")")) {
                            while (true) {
                                if (operaStack.peek().equals("(")) {
                                    priorityCount--;
                                    operaStack.pop();
                                    break;
                                }
                                opera = operaStack.pop();
                                numStack.push(opera);
                            }
                            continue;
                        }
                    }
                    while (currentOperaPriority <= topOperaPriority && priorityCount == 0) {
                        numStack.push(operaStack.pop());
                        if (!operaStack.isEmpty()) {
                            topOperaPriority = priority(operaStack.peek());
                        } else {
                            operaStack.push(opera);
                            break;
                        }
                    }
                    if (currentOperaPriority > topOperaPriority || priorityCount != 0) {
                        operaStack.push(opera);
                    }
                }
            }else{
                keepNum += opera;
            }
        }
//        while (index < expression.length()) {
//            while (index < expression.length() && isOpera(expression.substring(index, index + 1))) {
//                opera = expression.substring(index, ++index);
//                if (operaStack.isEmpty()) {
//                    operaStack.push(opera + "");
//                } else {
//                    //40(   41)
//                    int topOperaPriority = priority(operaStack.peek());
//                    int currentOperaPriority = priority(opera);
//                    if (currentOperaPriority == 3) {
//                        if (opera.equals("(")) {
//                            operaStack.push(opera);
//                            priorityCount++;
//                            continue;
//                        }
//                        if (opera.equals(")")) {
//                            while (true) {
//                                if (operaStack.peek().equals("(")) {
//                                    priorityCount--;
//                                    operaStack.pop();
//                                    break;
//                                }
//                                opera = operaStack.pop();
//                                numStack.push(opera);
//                            }
//                            continue;
//                        }
//                    }
//                    while (currentOperaPriority <= topOperaPriority && priorityCount == 0) {
//                        numStack.push(operaStack.pop());
//                        if (!operaStack.isEmpty()) {
//                            topOperaPriority = priority(operaStack.peek());
//                        } else {
//                            operaStack.push(opera);
//                            break;
//                        }
//                    }
//                    if (currentOperaPriority > topOperaPriority || priorityCount != 0) {
//                        operaStack.push(opera);
//                    }
//                }
//            }
//
//            while (index < expression.length() && !isOpera(expression.substring(index, index + 1))) {
//                keepNum += expression.substring(index, ++index);
//                if (index >= expression.length() || isOpera(expression.substring(index, index + 1))) {
//                    numStack.push(keepNum);
//                    keepNum = "";
//                }
//            }
//        }
        if(!keepNum.equals("")){
            numStack.push(keepNum);
            keepNum = "";
        }
        while (!operaStack.isEmpty()) {
            numStack.push(operaStack.pop());
        }
        Object[] nums = numStack.toArray();
        Stack<Integer> result = new Stack<>();
        for (Object object :nums) {
            String opera = String.valueOf(object);
            if(opera.matches("\\d+")){
                result.push(Integer.parseInt(opera));
            }else{
                num2 = result.pop();
                num1 = result.pop();
                result.push(computer(num1,num2,opera));
            }
        }
        System.out.println(result.pop());
        System.out.println(numStack);

    }


}
