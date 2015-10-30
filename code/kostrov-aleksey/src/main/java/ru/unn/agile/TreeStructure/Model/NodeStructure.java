package ru.unn.agile.TreeStructure.Model;


public class NodeStructure<T extends Comparable> {
    private T data;
    private NodeStructure left, right;

    public NodeStructure()
    {
        left = null;
        right = null;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }
}
