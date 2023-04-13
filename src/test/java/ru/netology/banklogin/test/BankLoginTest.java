package ru.netology.banklogin.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.data.SQLHelper;
import ru.netology.banklogin.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.banklogin.data.SQLHelper.cleanDatabase;

public class BankLoginTest {


    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Should successfully login to dashboard with exist login and " +
            "password from sut test data")
    void shouldSuccessfullyLogin() {
        var LoginPage = open("http://localhost:9999",
                ru.netology.banklogin.page.LoginPage.class);
        var AuthInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = LoginPage.validLogin(AuthInfo);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = SQLHelper.getVerifycationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

}
