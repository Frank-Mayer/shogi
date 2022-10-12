package de.hhn.shogi;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean test = RuleLogic.validMove(0, 0, 0, 0, Piece.BISHOP);
        System.out.println(test);
    }
}