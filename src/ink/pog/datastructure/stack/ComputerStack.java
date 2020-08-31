package ink.pog.datastructure.stack;

public class ComputerStack {

    private int maxSize;
    private int stack[];
    public int top = -1;

    public ComputerStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isOpera(int opera){
        return opera == '*' || opera == '+' || opera == '-' || opera == '/' || opera == '(' || opera == ')';
    }

    public int priority(int opera){
        if (opera == '+' || opera == '-'){
            return 1;
        }else if (opera == '*' || opera == '/'){
            return 2;
        }else if (opera == '(' || opera == ')'){
            return 3;
        }
        return -1;
    }

    public int computer(int num1,int num2,int opera){
        switch (opera){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    public void push(int opera){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        stack[++top] = opera;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    public int getTop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top];
    }

    public static void main(String[] args) {
        ComputerStack numStack = new ComputerStack(10);
        ComputerStack operaStack = new ComputerStack(10);
        String expression = "12+3*9*(1+3)/3";
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int opera = 0;
        int pop = 0;
        int priorityCount = 0;
        String keepNum = "";
        while (index < expression.length()){
               while (index < expression.length() && operaStack.isOpera(expression.charAt(index))){
                   opera = expression.charAt(index);
                   index++;
                   if(operaStack.isEmpty()){
                       operaStack.push(opera);
                   }else{
                       int top = operaStack.getTop();
                       int stackPriority = operaStack.priority(top);
                       int operaPriority = operaStack.priority(opera);
                       if(operaPriority == 3){
                           priorityCount++;
                           if (priorityCount == 2){
                               pop = operaStack.pop();
                               num2 = numStack.pop();
                               num1 = numStack.pop();
                               numStack.push(numStack.computer(num1,num2,pop));
                               priorityCount = 0;
                           }
                           continue;
                       }
                       if(operaPriority <= stackPriority){
                           if(priorityCount != 0){
                               operaStack.push(opera);
                           }else{
                               pop = operaStack.pop();
                               num2 = numStack.pop();
                               num1 = numStack.pop();
                               numStack.push(numStack.computer(num1,num2,pop));
                               operaStack.push(opera);
                           }
                       }else {
                           operaStack.push(opera);
                       }
                   }
               }

               while (index < expression.length() && !operaStack.isOpera(expression.charAt(index))){
                   keepNum += expression.charAt(index++);
                   if(index >= expression.length() || operaStack.isOpera(expression.charAt(index))){
                       numStack.push(Integer.parseInt(keepNum));
                       keepNum = "";
                   }
               }
           }

            if(index >= expression.length()){
                while (true){
                    if(operaStack.isEmpty()){
                        break;
                    }
                    pop = operaStack.pop();
                    num2 = numStack.pop();
                    num1 = numStack.pop();
                    numStack.push(numStack.computer(num1,num2,pop));
                }
            }
        System.out.println(numStack.pop());


    }


}
