package org.example;

import java.util.*;
// Есть колода карт: 4 цвета R,G,B,W и 10 достоинств (от 1 до 10)
// Реализовать команду Start N,C для раздачи N случайных карт C игрокам. Если команда отработала без ошибок,
// ничего не выводить в консоль


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start(10, 3);
        System.out.println(main.getCards(3));
    }

    private List<String> cards = List.of("R1", "G2", "B3", "W4", "R5", "G6", "B7", "W8", "R9", "G10");
    private Map<Integer, List<String>> playersCards = new HashMap<>();
    private List<Integer> usedCards = new ArrayList<>();

    private void start(int n, int c) {
        if (n < 1) {
            throw new IllegalArgumentException("Кол-во раздаваемых карт должно быть в кол-ве от 1");
        }
        if (c < 1) {
            throw new IllegalArgumentException("Кол-во игроков должно быть в кол-ве от 1");
        }
        int currentPlayerNumber = 1;
        for (int i = 0; i < n; i++) {
            if (currentPlayerNumber > c) {
                currentPlayerNumber = 1;
            }
            List<String> currentPlayerCards = new ArrayList<>();

            int cardIndex = new Random().nextInt(cards.size() - 1 + 1);
            while (usedCards.contains(cardIndex)) {
                cardIndex = new Random().nextInt(cards.size() - 1 + 1);
            }
            usedCards.add(cardIndex);

            if (playersCards.get(currentPlayerNumber) == null) {
                currentPlayerCards.add(cards.get(cardIndex));
            } else {
                currentPlayerCards = playersCards.get(currentPlayerNumber);
                currentPlayerCards.add(cards.get(cardIndex));
            }
            playersCards.put(currentPlayerNumber, currentPlayerCards);

            currentPlayerNumber++;
        }
    }

    private String getCards(int c) {
        if (c < 1) {
            throw new IllegalArgumentException("Кол-во игроков должно быть в кол-ве от 1");
        }
        if (!playersCards.containsKey(c)) {
            throw new IllegalArgumentException("Игрок под номером " + c + " отсутствует");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c).append(" ").append(
                playersCards.get(c).toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "")
        );
        return sb.toString();
    }
}