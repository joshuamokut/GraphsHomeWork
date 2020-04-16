package main;

import java.util.List;

public class Stack {
    private ArrayList stackArray; //Это линейный массив через который будем реализовать стек
    private int actualSize; //в этой переменной будем хранить актуальный размер массива
    private int stackPtr; // в этой переменной будем хранить размер стека

    public Stack() {
        this.actualSize=0;
        this.stackPtr=0;
        this.stackArray = new ArrayList();
    }



    //процедура для добавления элемент в стек.
    public void push(GraphNode t){
        if (stackPtr==actualSize){
            actualSize++;
            stackPtr++;
            stackArray.add(t);
        }
        else{
            stackArray.set(stackPtr++, t);
        }
    }

    //процедура для удаления элемента из стека
    public GraphNode pop() throws EmptyStackException {
        if (stackPtr==0)
            throw new EmptyStackException("Стек пустой!");
        else
        {
            GraphNode result=stackArray.get(stackPtr-1);
            stackPtr--;
            return result;
        }
    }

    //процедура для посмотра головного элемента стека
    public GraphNode peek() throws EmptyStackException{
        if (stackPtr==0)
            throw new EmptyStackException("стек путсой");
        else
            return stackArray.get(stackPtr-1);
    }

    public boolean empty() {
        return stackPtr==0;
    }
}
