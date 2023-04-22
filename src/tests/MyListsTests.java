package tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Est-test-test-est",
            password = "qweasd234";

    @Test
    public void testSaveArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncForm();
        }
        if (Platform.getInstance().isMW()) {
            AuthtorizationPageObject Auth = new AuthtorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.openNavigation();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickViewList();
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesThanDeleteOne() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncForm();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncForm();
            AuthtorizationPageObject Auth = new AuthtorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            String url = driver.getCurrentUrl();
            String new_url = url.substring(0,11) + "m." + url.substring(11);
            driver.get(new_url);

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
            if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS()))
            {
                ArticlePageObject.addArticlesToMySaved();
            }
        }

        SearchPageObject.searchIcon();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Set of computer software and specifications");

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            NavigationUI.clickViewList();
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS()))
        {
            MyListsPageObject.swipeArticleToDelete(article_title);
            MyListsPageObject.waitForArticleToDisappearByTitle(article_title);
            MyListsPageObject.waitForArticleToAppearByTitle("Java (software platform)");
        } else {
            NavigationUI.openNavigation();
            NavigationUI.clickViewList();
            MyListsPageObject.clickStarToDelete();
            MyListsPageObject.waitForArticleToDisappearByTitle(article_title);
            MyListsPageObject.waitForArticleToAppearByTitle("Java (software platform)");
        }
    }
}
