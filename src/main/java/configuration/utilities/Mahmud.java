package configuration.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Mahmud {

    @DataProvider
    public Object[][] testData() {
        Object[][] data = new Object[2][2];
        data[0][0] = "mahmud";
        data[0][1] = "m1234";
        data[1][0] = "Junaid";
        data[1][1] = "j1234";

        return data;
    }

    @DataProvider
    public Object[][] testData1() {
        Object[][] data = new Object[2][2];
        data[0][0] = 12;
        data[0][1] = 13;
        data[1][0] = 14;
        data[1][1] = 15;

        return data;
    }

    @DataProvider
    public Object[][] testData2() {
        Object[][] data = new Object[4][4];

        data[0][0] = "mahmud";
        data[0][1] = "m0";
        data[0][2] = "Queens,NY";
        data[0][3] = 133554466;
        data[1][0] = "mahmud1";
        data[1][1] = "m1";
        data[1][2] = "Queens1,NY";
        data[1][3] = 133554467;
        data[2][0] = "mahmud2";
        data[2][1] = "m2";
        data[2][2] = "Queens2,NY";
        data[2][3] = 133554468;
        data[3][0] = "mahmud3";
        data[3][1] = "m3";
        data[3][2] = "Queens3,NY";
        data[3][3] = 133554469;

        return data;
    }

    @Test(dataProvider = "testData")
    public void getData(String userName, String password) {
        System.out.println("User Name :" + userName + " " + "Password :" + password);
    }

    @Test(dataProvider = "testData1")
    public void getData(int a, int b) {
        System.out.println("User Name :" + a + " " + "Password :" + b);
    }

    @Test(dataProvider = "testData2")
    public void getData(String userName, String password, String address,int phoneNumber) {
        System.out.println("UserName :"+userName+" "+"Password "+password+" "+"Address "+address+" "+"PhoneNumber"+phoneNumber);
    }



}
