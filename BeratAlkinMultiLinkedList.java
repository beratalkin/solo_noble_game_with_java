/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solonoblegame;

/**
 *
 * @author berat
 */
public class BeratAlkinMultiLinkedList {

    BeratAlkinNode head; //head of the multiLinkedList is always the first node vertically and horizontally
    BeratAlkinNode tail; //this and secondTail is used only when the gameboard gets created
    BeratAlkinNode secondTail;

    public BeratAlkinMultiLinkedList() {
        this.head = null;
        this.tail = null;
        this.secondTail = null;
    }

    void addLast(String data) {
        //this adds nodes vertically
        if (head == null && tail == null && secondTail == null) {
            BeratAlkinNode newNode = new BeratAlkinNode(data);
            head = newNode;
            tail = newNode;
            secondTail = newNode;
        } else {
            BeratAlkinNode newNode = new BeratAlkinNode(data);
            tail.secondNextNode = newNode;
            newNode.secondPreviousNode = tail;
            tail = newNode;
            secondTail = newNode;
        }
    }

    void addLastContinue(String data) {
        //this adds nodes horizontally
        BeratAlkinNode newNode = new BeratAlkinNode(data);
        secondTail.nextNode = newNode;
        newNode.previousNode = secondTail;
        secondTail = newNode;
    }
    //adding methods should be runned in the right order

    boolean isThere(String data) {
        //this checks the list to know if the clicked area has a peg
        BeratAlkinNode temp = head;
        BeratAlkinNode temp2 = temp;
        while (temp != null) {
            if (temp.column == data.charAt(0)) {
                while (temp2 != null) {
                    if (temp2.row == data.charAt(1)) {
                        return true;
                    }
                    temp2 = temp2.nextNode;
                }
            }
            temp = temp.secondNextNode;
            temp2 = temp;
        }
        return false;
    }

    void remove(String data) {
        //this removes the wanted node from the list
        BeratAlkinNode temp = head;
        while (true) {
            //goes vertically to find the data's column
            if (temp.column == data.charAt(0)) {
                //after finding the column, this if statements check that if it is an head node horizontally
                if (temp.data.equals(data) && temp.secondNextNode != null && temp.secondPreviousNode != null) {
                    if (temp.nextNode != null) {
                        temp.secondPreviousNode.secondNextNode = temp.nextNode;
                        temp.secondNextNode.secondPreviousNode = temp.nextNode;
                        temp.nextNode.secondNextNode = temp.secondNextNode;
                        temp.nextNode.secondPreviousNode = temp.secondPreviousNode;
                        temp.nextNode.previousNode = null;
                        temp.nextNode = null;
                        temp.secondNextNode = null;
                        temp.secondPreviousNode = null;
                    } else {
                        temp.secondPreviousNode.secondNextNode = temp.secondNextNode;
                        temp.secondNextNode.secondPreviousNode = temp.secondPreviousNode;
                        temp.secondNextNode = null;
                        temp.secondPreviousNode = null;
                    }
                    break;
                } else if (temp.data.equals(data) && temp.secondNextNode != null && temp.secondPreviousNode == null) {
                    if (temp.nextNode != null) {
                        temp.secondNextNode.secondPreviousNode = temp.nextNode;
                        temp.nextNode.secondNextNode = temp.secondNextNode;
                        this.head = temp.nextNode;
                        temp.nextNode.previousNode = null;
                        temp.nextNode = null;
                        temp.secondNextNode = null;
                    } else {
                        this.head = temp.secondNextNode;
                        temp.secondNextNode.secondPreviousNode = null;
                        temp.secondNextNode = null;
                    }
                    break;
                } else if (temp.data.equals(data) && temp.secondNextNode == null && temp.secondPreviousNode != null) {
                    if (temp.nextNode != null) {
                        temp.secondPreviousNode.secondNextNode = temp.nextNode;
                        temp.nextNode.secondPreviousNode = temp.secondPreviousNode;
                        temp.nextNode.previousNode = null;
                        temp.nextNode = null;
                        temp.secondPreviousNode = null;
                    } else {
                        temp.secondPreviousNode.secondNextNode = null;
                        temp.secondPreviousNode = null;
                    }
                    break;
                } else {
                    //if it is not an head node horizontally, then this while loop will find the node's row
                    while (true) {
                        if (temp.row == data.charAt(1)) {
                            if (temp.nextNode == null) {
                                temp.previousNode.nextNode = null;
                                temp.previousNode = null;
                                break;
                            } else {
                                temp.previousNode.nextNode = temp.nextNode;
                                temp.nextNode.previousNode = temp.previousNode;
                                temp.previousNode = null;
                                temp.nextNode = null;
                                break;
                            }
                        }
                        temp = temp.nextNode;
                    }
                    break;
                }
            }
            temp = temp.secondNextNode;
        }
    }

    void add(String data) {
        BeratAlkinNode temp = head;
        BeratAlkinNode newNode = new BeratAlkinNode(data);
        while (temp.secondNextNode != null) {
            //this loop is for finding the column for the node that will be added
            //if there is no node in the list with the same column as data's, then it will reach to the last node before null
            if (temp.column == data.charAt(0)) {
                break;
            }
            temp = temp.secondNextNode;
        }
        //this if will run only if the data's column is the same with temp's column
        if (temp.column == data.charAt(0)) {
            if (temp.row > data.charAt(1)) {
                newNode.secondNextNode = temp.secondNextNode;
                newNode.secondPreviousNode = temp.secondPreviousNode;
                temp.secondNextNode.secondPreviousNode = newNode;
                temp.secondPreviousNode.secondNextNode = newNode;
                temp.secondNextNode = null;
                temp.secondPreviousNode = null;
                temp.previousNode = newNode;
                newNode.nextNode = temp;
            } else {
                while (true) {
                    if (temp.nextNode == null) {
                        temp.nextNode = newNode;
                        newNode.previousNode = temp;
                        break;
                    } else if (temp.row < data.charAt(1) && temp.nextNode.row > data.charAt(1)) {
                        newNode.nextNode = temp.nextNode;
                        newNode.previousNode = temp;
                        temp.nextNode.previousNode = newNode;
                        temp.nextNode = newNode;
                        break;
                    }
                    temp = temp.nextNode;
                }
            }
        } //this if will run only when the data's column is bigger than the temp's column
        //this means that data's column is something like 'H' and the last horizontal head node in the list is E
        else if (temp.column < data.charAt(0)) {
            temp.secondNextNode = newNode;
            newNode.secondPreviousNode = temp;
        }//this will run if the data's column is nowhere to be found and also it is not after the last horizontal head node.
        //for example there is no nodes in the 'B' column and the last horizontal head node is 'F'. If the data's column is 'B' then this will run, 
        else {
            temp = head;
            while (true) {
                if (temp.column < data.charAt(0) && temp.secondNextNode.column > data.charAt(0)) {
                    temp.secondNextNode.secondPreviousNode = newNode;
                    newNode.secondNextNode = temp.secondNextNode;
                    temp.secondNextNode = newNode;
                    newNode.secondPreviousNode = temp;
                    break;
                }
                temp = temp.secondNextNode;
            }
        }

    }

    int[] Search(String data) {
        BeratAlkinNode temp = head;
        BeratAlkinNode temp2 = temp;
        int[] returnCode = {0, 0, 0, 0};
        //Checks if there are any moves to up or down 
        while (true) {
            if (temp.column == data.charAt(0)) {
                while (true) {
                    if (temp.row == data.charAt(1)) {
                        if (temp.nextNode != null && temp.nextNode.row == temp.row + 1 && data.charAt(1) < '7')/*it is out of bounds after 8*/ {
                            if (temp.nextNode.nextNode == null || temp.nextNode.nextNode.row != (char) temp.row + 2) {
                                returnCode[0] = 1;
                            }
                        } else if (temp.previousNode != null && temp.previousNode.row == temp.row - 1 && data.charAt(1) > '2')/*it is out of bounds before 1*/ {
                            if (temp.previousNode.previousNode == null || temp.previousNode.previousNode.row != temp.row - 2) {
                                returnCode[1] = 1;
                            }
                        }
                        break;
                    }
                    temp = temp.nextNode;
                }
                break;
            }
            temp = temp.secondNextNode;
            temp2 = temp2.secondNextNode;
        }
        //Checks if there is any move to the right
        if (temp2.secondNextNode != null && data.charAt(0) < 'G') {
            temp = temp2.secondNextNode;
            boolean kontrol = false;
            while (temp != null) {
                if (data.charAt(1) == temp.row) {
                    kontrol = true;
                }
                temp = temp.nextNode;
            }
            if (kontrol) {
                if (temp2.secondNextNode.secondNextNode == null) {
                    returnCode[2] = 1;
                } else {
                    temp = temp2.secondNextNode.secondNextNode;
                    returnCode[2] = 1;
                    while (temp != null) {
                        if (data.charAt(1) == temp.row) {
                            returnCode[2] = 0;
                        }
                        temp = temp.nextNode;
                    }
                }
            }
        }
        //Checks if there is any move to the left
        if (temp2.secondPreviousNode != null && data.charAt(0) > 'B') {
            temp = temp2.secondPreviousNode;
            boolean kontrol = false;
            while (temp != null) {
                if (data.charAt(1) == temp.row) {
                    kontrol = true;
                }
                temp = temp.nextNode;
            }
            if (kontrol) {
                if (temp2.secondPreviousNode.secondPreviousNode == null) {
                    returnCode[3] = 1;
                } else {
                    temp = temp2.secondPreviousNode.secondPreviousNode;
                    returnCode[3] = 1;
                    while (temp != null) {
                        if (data.charAt(1) == temp.row) {
                            returnCode[3] = 0;
                        }
                        temp = temp.nextNode;
                    }
                }
            }
        }
        return returnCode;
    }

    String print() {
        //this returns a string for the TextArea
        BeratAlkinNode temp = head;
        BeratAlkinNode temp2 = temp;
        String returnString = "";
        while (temp.secondNextNode != null) {
            while (temp.nextNode != null) {
                returnString += (temp.data + "--->");
                temp = temp.nextNode;
            }
            returnString += "\n";
            temp = temp2.secondNextNode;
            temp2 = temp2.secondNextNode;
        }
        return returnString;
    }

    int getSize() {
        int counter = 0;
        BeratAlkinNode temp = head;
        BeratAlkinNode temp2 = temp;
        while (temp.secondNextNode != null) {
            while (temp.nextNode != null) {
                counter++;
                temp = temp.nextNode;
            }
            temp = temp2.secondNextNode;
            temp2 = temp2.secondNextNode;
        }
        return counter;
    }
}
