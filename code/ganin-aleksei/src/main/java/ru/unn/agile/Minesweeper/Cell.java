package ru.unn.agile.Minesweeper;

public class Cell {
    private boolean mine = false;
    private boolean issue = false;
    private boolean flag = false;
    private boolean _open = false;
    private int value = 0;

    public void setMine(){
        mine = true;
    }
    public boolean isMine(){
        return  mine;
    }

    public void setValue(int val){
        value = val;
    }

    public int getValue(){
        return value;
    }

    public void setIssue(){
        issue = true;
    }

    public boolean isIssue(){
        return issue;
    }

    public void unsetIssue() {
        issue = false;
    }

    public void setFlag(){
        flag = true;
    }

    public boolean isFlag(){
        return  flag;
    }

    public void unsetFlag(){
        flag = false;
    }

    public void open(){
        _open = true;
    }

    public boolean isOpen(){
        return  _open;
    }
}
