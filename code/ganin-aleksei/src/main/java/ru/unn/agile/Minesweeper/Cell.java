package ru.unn.agile.Minesweeper;

/**
 * Created by aleksei on 01.11.15.
 */
public class Cell {
    private boolean mine = false;
    private boolean issue = false;
    private boolean flag = false;
    private char value = 0;

    public void setMine(){
        mine = true;
    }
    public boolean isMine(){
        return  mine;
    }

    public void setValue(char val){
        value = val;
    }

    public char getValue(){
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
}
