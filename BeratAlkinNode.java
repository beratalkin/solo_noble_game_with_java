/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solonoblegame;

/**
 *
 * @author berat
 */
public class BeratAlkinNode {
    char column; //this is the letter of the peg's place
    char row; //this is the number of the peg's place
    String data; //this is column+row
    BeratAlkinNode nextNode; //this is the next node horizontally A1-->A2
    BeratAlkinNode previousNode; //this is the previous node horizontally B2-->B1
    BeratAlkinNode secondNextNode; //this is the next node vertically A1-->B1 only the head node of each letter has this
    BeratAlkinNode secondPreviousNode; ////this is the previous node vertically D1-->C1 only the head node of each letter has this
    
    BeratAlkinNode(String data){
        this.data=data;
        this.column=data.charAt(0);
        this.row=data.charAt(1);
        this.nextNode=null;
        this.previousNode=null;
        this.secondNextNode=null;
        this.previousNode=null;
    }
}
