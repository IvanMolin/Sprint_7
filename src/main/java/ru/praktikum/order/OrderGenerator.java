package ru.praktikum.order;

public class OrderGenerator {
    public static Order getOrderWithGrayScooter(){
        return new Order(
                "Alexei",
                "Fedorov",
                "Leskova, 25",
                "October",
                "+79993454545",
                2,
                "2022-11-25",
                "there is an intercom",
                new String[]{"GREY"});
    }

    public static Order getOrderWithBlackScooter(){
        return new Order(
                "Yuri",
                "Ivanov",
                "Shevchenko street, 25",
                "October",
                "+79993454545",
                2,
                "2022-11-22",
                "there is an intercom",
                new String[]{"BLACK"});
    }

    public static Order getOrderWithBlackAndGreyScooter(){
        return new Order(
                "Margarita",
                "Ivanova",
                "Shevchenko street, 25",
                "Dynamo",
                "+79993454545",
                2,
                "2022-11-23",
                "No comment",
                new String[]{"GREY", "BLACK"});
    }

    public static Order getOrderWithoutScooterColor(){
        return new Order(
                "Irina",
                "Petrova",
                "Lenina street, 15",
                "Sokolniki",
                "+79993454545",
                2,
                "2022-11-25",
                "No comment",
                new String[]{""});
    }
}
