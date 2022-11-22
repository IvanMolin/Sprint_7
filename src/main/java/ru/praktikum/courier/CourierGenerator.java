package ru.praktikum.courier;

public class CourierGenerator {
    public static Courier getDefaultCourier(){
        return new Courier("Fedosov","1q2w3e$R","Fedya");
    }

    public static Courier getOnlyLoginCourier(){
        return new Courier("Fedorov","","");
    }

    public static Courier getOnlyPasswordCourier(){
        return new Courier("","1q2w3e$R","");
    }

    public static Courier getOnlyLastNameCourier(){
        return new Courier("","","Ivan");
    }

    public static Credentials enterIncorrectLoginCourier(){
        return new Credentials("Fedo$ov", "1q2w3e$R");
    }
    public static Credentials enterIncorrectPasswordCourier(){
        return new Credentials("Fedosov", "123qwe$$");
    }
    public static Credentials enterOnlyLoginCourier(){
        return new Credentials("Fedosov", "");
    }

    public static Credentials enterOnlyPasswordCourier(){
        return new Credentials("", "1q2w3e$R");
    }

    public static Credentials getNonExistentCourier(){
        return new Credentials("Problema", "123qwe$$");
    }


}
